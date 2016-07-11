package com.bumblebee.MessageFromClient;

import com.bumblebee.ClientMessage.ClientMessage;
import com.bumblebee.ConverstationFiles.Conversation;
import com.bumblebee.ConverstationFiles.ConversationCntrl;
import com.bumblebee.ResponseToClient.ResponseActionFactory;

/**
 * Created by deadcode on 10/07/2016.
 */
public abstract class MessageFromClientHandler {

    private ClientMessage clientMessage;
    private ConversationCntrl conversationCntrl;

    public void init(ClientMessage clientMessage, ConversationCntrl conversationCntrl){
        this.clientMessage = clientMessage;
        this.conversationCntrl = conversationCntrl;
    }

    public abstract ResponseActionFactory execute();

    public ConversationCntrl getConversationCntrl() {
        return conversationCntrl;
    }
}
