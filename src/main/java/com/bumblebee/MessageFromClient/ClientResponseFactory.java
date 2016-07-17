package com.bumblebee.MessageFromClient;

import com.bumblebee.ClientMessage.ClientMessage;
import com.bumblebee.Controller.Action;
import com.bumblebee.Controller.WebhookAction;
import com.bumblebee.ConverstationFiles.ConversationCntrl;
import com.bumblebee.common.utils.Const;

import java.util.HashMap;

/**
 * Created by deadcode on 10/07/2016.
 */
public class ClientResponseFactory {

    private static HashMap<String, Class<? extends MessageFromClientHandler>> actionMappings = new HashMap<>();
    static {
        actionMappings.put(Const.ClientMessageType.Delivery.name(), DeliveryHandler.class);
        actionMappings.put(Const.ClientMessageType.Text.name(), TextHandler.class);
        actionMappings.put(Const.ClientMessageType.Postback.name(), PostbackHandler.class);
        actionMappings.put(Const.ClientMessageType.TextWithAttach.name(), LocationHandler.class);
    }

    private static MessageFromClientHandler getHandlerInstance(Const.ClientMessageType clientMessageType){

        System.out.println("CHeck this "+clientMessageType.name());

        Class<? extends MessageFromClientHandler> controllerClass = null;
        MessageFromClientHandler newInstance = null;

        if(actionMappings.containsKey(clientMessageType.name())){

            controllerClass = actionMappings.get(clientMessageType.name());
            try {
                newInstance = controllerClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Could not create the action for :" + clientMessageType.name());
            }
        }

        return newInstance;

    }


    public MessageFromClientHandler getHandler(String clientJson){

       // Create Client Message
       ClientMessage clientMessage = new ClientMessageCreator().getClientMessagePOJO(clientJson);

        MessageFromClientHandler messageFromClientHandler = null;

        if(clientMessage.getMessageType().compareTo(Const.ClientMessageType.Delivery) != 0){

            // Get or Create Conversation Controller
            ConversationCntrl conversationCntrl = new ConversationCntrlCreator().getConversationCntrl(clientMessage);

            // Create MessageFromClientHandler and initialize it with client message and conversation controller
             messageFromClientHandler = getHandlerInstance(clientMessage.getMessageType());

            if(messageFromClientHandler != null){

                messageFromClientHandler.init(clientMessage, conversationCntrl);
            }

        }

        return messageFromClientHandler;
   }



}
