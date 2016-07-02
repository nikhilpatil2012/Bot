package com.bumblebee.ClientMessage;

import com.bumblebee.ChatbotFiles.Message;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by deadcode on 06/06/2016.
 */
public class MultiPartBodyWithMessage extends MultiPartCustom{

    public MultiPartBodyWithMessage(Message message){
     super(message);
    }

    @Override
    public void createMessage() {

        HashMap<String, String> keyValueList = new HashMap<>();

        // Add the recipient object
        keyValueList.put("id", getMessage().getId());
        addMessage("recipient", getJsonObject(keyValueList));

        // Add the message object
        keyValueList.clear();
        keyValueList.put("text", getMessage().getMessage());
        addMessage("message", getJsonObject(keyValueList));
    }
}
