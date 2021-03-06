package com.bumblebee.Controller;

import com.bumblebee.ChatbotFiles.ChatParser;
import com.bumblebee.ChatbotFiles.MessageMiner;
import com.bumblebee.ChatbotFiles.ResponseExecuter;
import com.bumblebee.ClientMessage.ClientMessage;
import com.bumblebee.ConverstationFiles.Conversation;
import com.bumblebee.ConverstationFiles.ConversationCntrl;
import com.bumblebee.ConverstationFiles.ConversationHandler;
import com.bumblebee.JSONCreator.MasterJSON;
import com.bumblebee.MessageFromClient.ClientResponseFactory;
import com.bumblebee.MessageFromClient.MessageFromClientHandler;
import com.bumblebee.ResponseToClient.ResponseAction;
import com.bumblebee.ResponseToClient.ResponseActionFactory;
import com.bumblebee.ResponseToClient.ResponseActionResult;
import com.bumblebee.common.utils.FinalCallback;

import java.io.BufferedReader;
import java.util.*;


/**
 * Created by deadcode on 29/05/2016.
 */
public class WebhookAction extends Action {

    private ChatParser chatParser = new ChatParser();

    private MessageMiner messageMiner = new MessageMiner();

    private ResponseExecuter responseExecuter = new ResponseExecuter();

    @Override
    public ActionResult execute() {

        String response = "";

        switch (getRequestMethod()){

            case "GET":{

                HashMap<String, String> paramMap;

                if(!(paramMap = getUrlParameter(new String[]{"hub.verify_token","hub.challenge"})).isEmpty()){

                    if(paramMap.get("hub.verify_token").equals("Charlie123")){

                        response = paramMap.get("hub.challenge");

                    }
                }

            }break;

            case "POST":{


                StringBuffer jb = new StringBuffer();
                String line = null;
                try {
                    BufferedReader reader = request.getReader();
                    while ((line = reader.readLine()) != null)
                        jb.append(line);
                } catch (Exception e) { /*report an error*/ }

                response = jb.toString();

                System.err.println("Response From Client -- "+response);


                MessageFromClientHandler messageFromClientHandler = new ClientResponseFactory().getHandler(response);

                if(messageFromClientHandler != null){

                    ResponseActionFactory responseActionFactory = messageFromClientHandler.execute();

                    if(responseActionFactory != null){

                        ResponseAction responseAction = responseActionFactory.getAction();

                        if(responseAction != null){

                            responseAction.execute(new FinalCallback() {
                                @Override
                                public void masterJsonCallback(MasterJSON masterJSON) {

                                    ResponseActionResult responseActionResult = new ResponseActionResult(masterJSON);
                                    responseActionResult.sendMessage();

                                }
                            });
                        }
                    }

                }


/*               ClientMessage clientMessage = parseMessageFromClient(response);

                System.out.println("SenderId "+clientMessage.getSenderId());
                System.out.println("Message Type "+clientMessage.getMessageType());

                if(clientMessage.getMessageType().compareTo(Const.ClientMessageType.TextWithAttach) == 0){

                    System.out.println("Message Type "+clientMessage.getAttachmentType());

                    if(clientMessage.getLat() != 0){
                        System.out.println("Message Lat "+clientMessage.getLat());
                        System.out.println("Message Lng "+clientMessage.getLng());
                    }
                }

                if(clientMessage.getMessageText() != null){

                    System.out.println("Message Text "+clientMessage.getMessageText());
                }

                ConversationHandler conversationHandler = new ConversationHandler(clientMessage);

                if(conversationHandler.getConversation() != null){

                    Conversation nextConversation = conversationHandler.getConversation();

                    System.out.println("Cov. Code "+nextConversation.getCode());



                    ResponseAction responseAction = new ResponseActionFactory().getAction(conversationHandler.getConversationCntrl());

                    ResponseActionResult responseActionResult = responseAction.execute();

                    responseActionResult.sendMessage();

                }

                System.out.println("Thread Count "+Thread.activeCount());*/


         /*       if(clientMessage.getMessageText().equals("Hey")){

                    ConversationHandler conversationHandler = new ConversationHandler(clientMessage);

                    Conversation nextConversation = conversationHandler.getConversation();

                    System.out.println("Cov. Code "+nextConversation.getCode());

                    ResponseAction responseAction = new ResponseActionFactory().getAction(nextConversation);

                    ResponseActionResult responseActionResult = responseAction.execute();

                    responseActionResult.sendMessage();
                }*/


/**/

                /*chatParser.parseText(response.toString());

                if(chatParser.getMessageType() != null){
                    System.err.println("Message Type "+chatParser.getMessageType());

                    if(chatParser.getMessageType().equals(StatusCodes.CLIENT_MESSAGE)){
                        messageMiner.sendMessageToWatson(chatParser, new WatsonCallback() {
                            @Override
                            public void GetQuery(Query query) {

                                System.err.println("QUery Message "+query.getWhere()+query.getWhat());

                                // Execute the response
                                responseExecuter.execute(query);

                            }
                        });
                    }
                }*/


            }break;
        }

        return createShowPageResult(response);
    }
}

