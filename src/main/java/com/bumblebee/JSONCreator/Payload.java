package com.bumblebee.JSONCreator;

import com.bumblebee.common.utils.Const;
import org.json.JSONObject;

/**
 * Created by deadcode on 28/06/2016.
 */
public class Payload {

    private Const.PayloadType payloadType;
    private ButtonJSONCntlr buttonJSONCntlr;
    private ElementJSONCntlr elementJSONCntlr;
    private String title;
    private String fileUrl;

    public Payload(Const.PayloadType payloadType, String title, ButtonJSONCntlr buttonJSONCntlr) {
        this.payloadType = payloadType;
        this.title = title;
        this.buttonJSONCntlr = buttonJSONCntlr;
    }

    public Payload(Const.PayloadType payloadType, ElementJSONCntlr elementJSONCntlr) {
        this.payloadType = payloadType;
        this.elementJSONCntlr = elementJSONCntlr;
    }

    public Payload(Const.PayloadType payloadType, String fileUrl) {
        this.payloadType = payloadType;
        this.fileUrl = fileUrl;
    }

    public JSONObject getPayloadJSON(){

        JSONObject payloadJson = new JSONObject();

        if(payloadType.compareTo(Const.PayloadType.basic) == 0){
            payloadJson.put("url", fileUrl);
        }
         else if(payloadType.compareTo(Const.PayloadType.button) == 0){

             payloadJson.put("template_type", payloadType.name());
             payloadJson.put("text", title);
             payloadJson.put("buttons", buttonJSONCntlr.getButtonsJson());

        }
         else if(payloadType.compareTo(Const.PayloadType.generic) == 0){

            payloadJson.put("template_type", payloadType.name());
            payloadJson.put("elements", elementJSONCntlr.getElementsJSON());
        }

        return payloadJson;
    }
}
