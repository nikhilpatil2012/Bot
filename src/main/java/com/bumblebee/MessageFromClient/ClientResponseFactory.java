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

    private static HashMap<Const.ClientMessageType, Class<? extends MessageFromClientHandler>> actionMappings = new HashMap<>();
    static {
        actionMappings.put(Const.ClientMessageType.Delivery, DeliveryHandler.class);
        actionMappings.put(Const.ClientMessageType.Text, TextHandler.class);
    }

    private static MessageFromClientHandler getHandlerInstance(Const.ClientMessageType clientMessageType){

        Class<? extends MessageFromClientHandler> controllerClass = actionMappings.get(clientMessageType);

        try {
            return controllerClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not create the action for :" + clientMessageType.name());
        }
    }


    public MessageFromClientHandler getHandler(String clientJson){

       // Create Client Message
       ClientMessage clientMessage = new ClientMessageCreator().getClientMessagePOJO(clientJson);

       // Get or Create Conversation Controller
        ConversationCntrl conversationCntrl = new ConversationCntrlCreator().getConversationCntrl(clientMessage);

       // Create MessageFromClientHandler and initialize it with client message and conversation controller
       MessageFromClientHandler messageFromClientHandler = getHandlerInstance(clientMessage.getMessageType());
       messageFromClientHandler.init(clientMessage, conversationCntrl);

        return messageFromClientHandler;
   }



}
