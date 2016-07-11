package com.bumblebee.ResponseToClient;

import com.bumblebee.ConverstationFiles.Conversation;
import com.bumblebee.ConverstationFiles.ConversationCntrl;
import com.bumblebee.JSONCreator.Attachment;
import com.bumblebee.JSONCreator.ElementJSONCntlr;
import com.bumblebee.JSONCreator.MasterJSON;
import com.bumblebee.JSONCreator.Payload;
import com.bumblebee.common.utils.Const;
import com.bumblebee.common.utils.ConversationPool;

/**
 * Created by deadcode on 01/07/2016.
 */
public class SendTextToClient extends ResponseAction {

    @Override
    public ResponseActionResult execute() {

        Conversation conversation = getConversation();
        ConversationCntrl conversationCntrl = getConversationCntrl();

        String text = String.format(conversation.getText(), conversationCntrl.getFirstName());

        MasterJSON masterJSON = new MasterJSON(conversationCntrl.getUserId(), text); //931411386981115

        return new ResponseActionResult(masterJSON);
    }


}
