package com.bumblebee.MessageFromClient;

import com.bumblebee.ResponseToClient.ResponseActionFactory;

/**
 * Created by deadcode on 10/07/2016.
 */
public class TextHandler extends MessageFromClientHandler {


    @Override
    public ResponseActionFactory execute() {

        return new ResponseActionFactory(getConversationCntrl());
    }
}
