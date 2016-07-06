package com.bumblebee.Controller;

import com.bumblebee.ClientMessage.ClientMessage;
import com.bumblebee.common.utils.Const;
import com.bumblebee.model.ShowPageResult;
import com.bumblebee.model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.HashMap;

/**
 * Created by deadcode on 23/04/16.
 */

public abstract class Action  {


    protected HttpServletRequest request;
    private String requestMethod;
    private String requestBody;
    private boolean isQueryStringPresent = false;
    private static HashMap<String, Class<?>> map = new HashMap<String, Class<?>>();

    static {
        map.put("webhook", WebhookAction.class);
    }

    public ShowPageResult createShowPageResult(String data){

        return new ShowPageResult(data);
    }

    public void init(HttpServletRequest request){
     this.request = request;
     initialiseAttributes(request);
    }

    public String getRequestMethod(){
        return requestMethod;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public boolean isQueryStringPresent(){
        return isQueryStringPresent;
    }

    protected void initialiseAttributes(HttpServletRequest req) {
        requestMethod = req.getMethod();
    }

    // Retrieve the parameters from the url's query string
    public HashMap<String, String> getUrlParameter(String[] params){

        HashMap<String, String> paramMap = new HashMap<>();

        for(String p : params){

            if(request.getParameter(p) != null){
                paramMap.put(p,request.getParameter(p));
            }
        }
        return paramMap;
    }

    public  ClientMessage parseMessageFromClient(String json){

        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray;
        ClientMessage clientMessage = new ClientMessage();

        // Check entry JsonArray exist and is not empty
        if(!jsonObject.isNull("entry") && (jsonArray = jsonObject.getJSONArray("entry")).length() > 0){

            // Check messaging JsonArray exist and is not empty
            if(!(jsonObject = jsonArray.getJSONObject(0)).isNull("messaging") && (jsonArray = jsonObject.getJSONArray("messaging")).length() > 0){

                // Parse Authentication -- Get & Store Sender Id
                parseAuthentication(clientMessage, jsonArray.getJSONObject(0));


                if(!(jsonObject = jsonArray.getJSONObject(0)).isNull("message")){

                    parseMessage(clientMessage, jsonObject);

                }
                  else if(!(jsonObject = jsonArray.getJSONObject(0)).isNull("delivery")){

                    // Parse Delivery Message
                    parseDeliveryConfirmation(clientMessage, jsonObject);
                }
                 else if(!(jsonObject = jsonArray.getJSONObject(0)).isNull("postback")){

                    // Parse Postback Message
                    parsePostback(clientMessage, jsonObject);
                }

            }
        }

        return clientMessage;
    }

    private  void parseAuthentication(ClientMessage clientMessage, JSONObject jsonObject){

       JSONObject master = jsonObject.getJSONObject("sender");
        clientMessage.setSenderId(master.getString("id"));
        System.out.println("Sender present "+clientMessage.getSenderId());

        master = jsonObject.getJSONObject("recipient");

        clientMessage.setRecipientId(master.getString("id"));
        System.out.println("Recipient present "+clientMessage.getRecipientId());
    }

    private  void parseMessage(ClientMessage clientMessage, JSONObject jsonObject){

        jsonObject = jsonObject.getJSONObject("message");

        if(!jsonObject.isNull("text")){

            parseText(clientMessage, jsonObject);
        }
        else if(!jsonObject.isNull("attachments")){

            parseAttachment(clientMessage, jsonObject);
        }

    }

    private  void parseText(ClientMessage clientMessage, JSONObject jsonObject){
        clientMessage.setMessageType(Const.ClientMessageType.Text);
        clientMessage.setMessageText(jsonObject.getString("text"));
    }

    private  void parseDeliveryConfirmation(ClientMessage clientMessage, JSONObject jsonObject){

        JSONObject deliveryJSON = jsonObject.getJSONObject("delivery");
        clientMessage.setMessageType(Const.ClientMessageType.Delivery);
        clientMessage.setWatermark(deliveryJSON.getLong("watermark"));

        System.out.println("Watermark :- "+clientMessage.getWatermark());
    }

    private  void parsePostback(ClientMessage clientMessage, JSONObject jsonObject){

        JSONObject postbackJson = jsonObject.getJSONObject("postback");
        clientMessage.setMessageType(Const.ClientMessageType.Postback);
        clientMessage.setPostBackId(postbackJson.getString("payload"));

        System.out.println(clientMessage.getMessageType());
        System.out.println(clientMessage.getPostBackId());
    }

    private  void parseAttachment(ClientMessage clientMessage, JSONObject jsonObject){

        JSONObject attachmentJson = jsonObject.getJSONArray("attachments").getJSONObject(0);

        clientMessage.setMessageType(Const.ClientMessageType.TextWithAttach);
        clientMessage.setAttachmentType(attachmentJson.getString("type"));

        // Payload is present parse it
        JSONObject payloadJson = attachmentJson.getJSONObject("payload");

        // Payload is an Image
        if(clientMessage.getAttachmentType().equals(Const.AttachmentType.image.name())){
            clientMessage.setAttachmentUrl(payloadJson.getString("url"));
        }
           // Payload is user Location
          else if(clientMessage.getAttachmentType().equals(Const.AttachmentType.location.name())){

            // Get coordinates object
            JSONObject coordinateJson = payloadJson.getJSONObject("coordinates");

            clientMessage.setLat(coordinateJson.getDouble("lat"));
            clientMessage.setLng(coordinateJson.getDouble("long"));
        }
    }

    public abstract ActionResult execute();
}
