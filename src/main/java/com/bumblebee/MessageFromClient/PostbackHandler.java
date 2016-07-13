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

                if(text.equals("Dine")){

                    // Save Current State
                    getConversationCntrl().setPreviousState(new State(getConversationCntrl().getClientStateType(), getConversationCntrl().getStep()));

                    // Jump to new state
                    jumpToNewState(new State(ConversationPool.ClientStateType.FoodPool, -1));
                }

                getConversationCntrl().setHangoutOption(text);

            } break;
        }

        return new ResponseActionFactory(getConversationCntrl());
    }
}
