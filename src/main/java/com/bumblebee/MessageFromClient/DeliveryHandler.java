package com.bumblebee.MessageFromClient;

import com.bumblebee.ResponseToClient.ResponseActionFactory;

/**
 * Created by deadcode on 10/07/2016.
 */
public class DeliveryHandler extends MessageFromClientHandler {


    @Override
    public ResponseActionFactory execute() {

        ResponseActionFactory responseActionFactory = null;

        if(getConversationCntrl().getStep() >= 0){
            responseActionFactory = new ResponseActionFactory(getConversationCntrl());
        }

        return responseActionFactory;
    }
}
