package com.bumblebee.MessageFromClient;

import com.bumblebee.ClientMessage.ClientMessage;
import com.bumblebee.ConverstationFiles.ConversationCntrl;
import com.bumblebee.ResponseToClient.ResponseActionFactory;
import com.bumblebee.common.utils.Const;
import com.bumblebee.common.utils.ConversationPool;

/**
 * Created by deadcode on 10/07/2016.
 */
public class LocationHandler extends MessageFromClientHandler {


    @Override
    public ResponseActionFactory execute() {

        ClientMessage clientMessage = getClientMessage();
        ConversationCntrl conversationCntrl = getConversationCntrl();

        if(clientMessage.getAttachmentType().equals(Const.AttachmentType.location.name())){

            conversationCntrl.setLat(clientMessage.getLat());
            conversationCntrl.setLat(clientMessage.getLng());
            conversationCntrl.setMvNext(true);

        }

        return new ResponseActionFactory(getConversationCntrl());
    }
}
