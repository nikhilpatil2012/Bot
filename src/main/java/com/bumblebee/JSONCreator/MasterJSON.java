package com.bumblebee.JSONCreator;

import org.json.JSONObject;

/**
 * Created by deadcode on 28/06/2016.
 */
public class MasterJSON {

    private String userId;
    private String text;
    private Attachment attachment;

    public MasterJSON(String userId, String text) {
        this.userId = userId;
        this.text = text;
    }

    public MasterJSON(String userId, Attachment attachment) {
        this.userId = userId;
        this.attachment = attachment;
    }

    public JSONObject getRecipientJSON(){

        return new JSONObject().put("id", userId);
    }

    public JSONObject getMessageJSON(){

        JSONObject jsonObject = new JSONObject();
        if(text != null){
            jsonObject.put("text", text);
        }

        if(attachment != null){

            jsonObject.put("attachment", attachment.getAttachmentJSON());
        }

        return jsonObject;
    }

    public JSONObject getMasterJSON(){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("recipient", new JSONObject().put("id", userId));

         if(text != null){
             jsonObject.put("message", new JSONObject().put("text", text));
         }

         if(attachment != null){

             jsonObject.put("message", new JSONObject().put("attachment", attachment.getAttachmentJSON()));
         }

        return jsonObject;
    }
}
