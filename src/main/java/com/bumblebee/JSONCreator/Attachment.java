package com.bumblebee.JSONCreator;

import com.bumblebee.common.utils.Const;
import org.json.JSONObject;

/**
 * Created by deadcode on 28/06/2016.
 */
public class Attachment {

    private Const.AttachmentType attachmentType;
    private Payload payload;

    public Attachment(Const.AttachmentType attachmentType, Payload payload) {
        this.attachmentType = attachmentType;
        this.payload = payload;
    }

    public JSONObject getAttachmentJSON(){

        JSONObject attachmentJson = new JSONObject();

        attachmentJson.put("type", attachmentType.name());
        attachmentJson.put("payload", payload.getPayloadJSON());

        return attachmentJson;
    }

}
