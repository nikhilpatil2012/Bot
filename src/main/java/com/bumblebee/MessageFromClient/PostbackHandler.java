package com.bumblebee.MessageFromClient;

import com.bumblebee.ResponseToClient.ResponseActionFactory;
import com.bumblebee.common.utils.ConversationPool;

/**
 * Created by deadcode on 10/07/2016.
 */
public class PostbackHandler extends MessageFromClientHandler {


    @Override
    public ResponseActionFactory execute() {

        String postbackCode = getClientMessage().getPostBackId();

        int masterCode = Integer.parseInt(postbackCode.split("_")[0]);
        String text = postbackCode.split("_")[1];

        System.out.println("MasterCode "+masterCode);
        System.out.println("Text "+text);

        getConversationCntrl().setMvNext(true);

        switch (masterCode){

            case ConversationPool.SHOW_HANGOUT_OPTIONS: {

                getConversationCntrl().setHangoutOption(text);

            } break;
        }

        return new ResponseActionFactory(getConversationCntrl());
    }
}
