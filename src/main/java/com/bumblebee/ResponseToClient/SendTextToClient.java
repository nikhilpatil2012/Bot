package com.bumblebee.ResponseToClient;

import com.bumblebee.ConverstationFiles.Conversation;
import com.bumblebee.ConverstationFiles.ConversationCntrl;
import com.bumblebee.JSONCreator.Attachment;
import com.bumblebee.JSONCreator.ElementJSONCntlr;
import com.bumblebee.JSONCreator.MasterJSON;
import com.bumblebee.JSONCreator.Payload;
import com.bumblebee.common.utils.Const;
import com.bumblebee.common.utils.ConversationPool;
import com.bumblebee.common.utils.FinalCallback;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by deadcode on 01/07/2016.
 */
public class SendTextToClient extends ResponseAction {

    @Override
    public void execute(FinalCallback finalCallback) {

        Conversation conversation = getConversation();
        ConversationCntrl conversationCntrl = getConversationCntrl();

        String text = String.format(conversation.getText(), conversationCntrl.getFirstName());

        MasterJSON masterJSON = new MasterJSON(conversationCntrl.getUserId(), text); //931411386981115

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                finalCallback.masterJsonCallback(masterJSON);

            }
        }, 2000);

    }


}
