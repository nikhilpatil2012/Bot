package com.bumblebee.ResponseToClient;


import com.bumblebee.ConverstationFiles.Conversation;
import com.bumblebee.common.utils.ConversationPool;

/**
 * Created by deadcode on 28/06/2016.
 */
public abstract class ResponseAction {

    private String text;
    private int code;

    public abstract ResponseActionResult execute();

    public void init(Conversation conversation){

        code = conversation.getCode();

        if(ConversationPool.codeList.containsKey(conversation.getCode())){
            text = ConversationPool.codeList.get(conversation.getCode());
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
