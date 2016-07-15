package com.bumblebee.MessageFromClient;

import com.bumblebee.ResponseToClient.ResponseActionFactory;

/**
 * Created by deadcode on 10/07/2016.
 */
public class TextHandler extends MessageFromClientHandler {


    @Override
    public ResponseActionFactory execute() {

        if(getClientMessage().getMessageText().equals("Hey")){

             getConversationCntrl().setStep(-1);
        }

        return new ResponseActionFactory(getConversationCntrl());
    }
}
