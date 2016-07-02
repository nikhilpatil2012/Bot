package com.bumblebee.ConverstationFiles;

import com.bumblebee.JSONCreator.MasterJSON;
import com.bumblebee.common.utils.Const;
import com.bumblebee.common.utils.ConversationPool;
import org.json.JSONObject;

/**
 * Created by deadcode on 28/06/2016.
 */
public class ConversationToJSON {

    private Conversation conversation;

    public ConversationToJSON(Conversation conversation) {
        this.conversation = conversation;
    }

    public void processConversation(){

        if(conversation.getType().compareTo(Const.ClientMessageType.Text) == 0){

            String text = ConversationPool.codeList.get(conversation.getCode());

            MasterJSON masterJSON = new MasterJSON("sssk33k3k3", text);
            JSONObject jsonObject = masterJSON.getMasterJSON();
        }
          else if(conversation.getType().compareTo(Const.ClientMessageType.Postback) == 0){

            // Get class from the Code
            // call getMasterJson() on the class
            //


        }


    }

}
