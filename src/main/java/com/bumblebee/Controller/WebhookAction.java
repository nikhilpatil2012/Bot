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


            }break;
        }

        return createShowPageResult(response);
    }
}

