package com.bumblebee.ResponseToClient;


import com.bumblebee.ConverstationFiles.*;
import com.bumblebee.common.utils.ConversationCodes;

/**
 * Created by deadcode on 28/06/2016.
 */
public abstract class ResponseAction {

    private ConversationCntrl conversationCntrl = null;
    private Conversation conversation;

    public abstract ResponseActionResult execute();

    public void init(ConversationCntrl conversationCntrl, Conversation conversation){

        this.conversationCntrl = conversationCntrl;
        this.conversation = conversation;

    }


    public void updatePostback(ConversationCodes.PostbackType postbackType){


        if(conversationCntrl != null){
          conversationCntrl.addPostBack(new Postback(postbackType, ConversationCodes.getPostbackBtnList(postbackType)));
        }

    }

    public ConversationCntrl getConversationCntrl() {
        return conversationCntrl;
    }

    public Conversation getConversation() {
        return conversation;
    }
}
