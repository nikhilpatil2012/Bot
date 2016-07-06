package com.bumblebee.ResponseToClient;


import com.bumblebee.ConverstationFiles.Conversation;
import com.bumblebee.ConverstationFiles.ConversationCntrl;
import com.bumblebee.common.utils.ConversationPool;

/**
 * Created by deadcode on 28/06/2016.
 */
public abstract class ResponseAction {

    private String text;
    private int code;

    public abstract ResponseActionResult execute();

    public void init(ConversationCntrl conversationCntrl){

        code = conversationCntrl.getConversation().getCode();

        if(ConversationPool.codeList.containsKey(code)){
            text = String.format(ConversationPool.codeList.get(code), conversationCntrl.getFirstName());
            System.out.println("Text "+text);
        }

        System.out.println("Code "+code);
    }

    public String getText() {
        return text;
    }

    public int getCode() {
        return code;
    }
}
