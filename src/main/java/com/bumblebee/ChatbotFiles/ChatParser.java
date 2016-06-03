package com.bumblebee.ChatbotFiles;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Created by deadcode on 02/06/2016.
 */
@Service
public class ChatParser {

    private String senderId, recipientId, message, queryMessage;

    public void parseText(String text){

        JSONObject jsonObject = new JSONObject(text);

         if(!jsonObject.isNull("entry")){

             jsonObject = jsonObject.getJSONArray("entry").getJSONObject(0);

             if(!jsonObject.isNull("messaging")){

                 jsonObject = jsonObject.getJSONArray("messaging").getJSONObject(0);

                 if(!jsonObject.isNull("sender") && !jsonObject.isNull("recipient")){
                    senderId = jsonObject.getJSONObject("sender").getString("id");
                    recipientId = jsonObject.getJSONObject("recipient").getString("id");
                 }

                 if(!jsonObject.isNull("message")){
                    message = jsonObject.getJSONObject("message").getString("text");

                    queryMessage = message.replace(" ", "+");
                 }

             }
         }
    }

    public String getMessage() {
        return message;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getQueryMessage() {
        return queryMessage;
    }
}
