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

                // Get & Store Sender Id
                if(!(jsonObject = jsonArray.getJSONObject(0)).isNull("sender") && !(jsonObject = jsonObject.getJSONObject("sender")).isNull("id")){
                    clientMessage.setSenderId(jsonObject.getString("id"));
                    System.out.println("Sender present "+clientMessage.getSenderId());
                }

                // Get & Store Recipient Id
                if(!(jsonObject = jsonArray.getJSONObject(0)).isNull("recipient") && !(jsonObject = jsonObject.getJSONObject("recipient")).isNull("id")){
                    clientMessage.setRecipientId(jsonObject.getString("id"));
                    System.out.println("Recipient present "+clientMessage.getRecipientId());
                }

                if(!(jsonObject = jsonArray.getJSONObject(0)).isNull("message")){

                    jsonObject = jsonObject.getJSONObject("message");

                    if(!jsonObject.isNull("text") && jsonObject.getString("text").length() > 0){
                        clientMessage.setMessageText(jsonObject.getString("text"));
                    }

                    clientMessage.setMessageType(Const.ClientMessageType.Text);

                    if(!jsonObject.isNull("attachments") && (jsonArray = jsonObject.getJSONArray("attachments")).length() > 0){

                        clientMessage.setMessageType(Const.ClientMessageType.TextWithAttach);

                        if(!(jsonObject = jsonArray.getJSONObject(0)).isNull("type")){
                            clientMessage.setAttachmentType(jsonObject.getString("type"));
                        }

                           // Payload is present
                        if(!jsonObject.isNull("payload")){

                            // Get Payload
                            jsonObject = jsonObject.getJSONObject("payload");

                            // Payload is image
                            if(clientMessage.getAttachmentType().equals(Const.AttachmentType.image.name()) && !jsonObject.isNull("url")){

                                clientMessage.setAttachmentUrl(jsonObject.getString("url"));

                            }
                              // Payload is location
                            else if(clientMessage.getAttachmentType().equals(Const.AttachmentType.location.name()) && !jsonObject.isNull("coordinates")){

                                  // Get coordinates object
                                jsonObject = jsonObject.getJSONObject("coordinates");

                                if(!jsonObject.isNull("lat") && !jsonObject.isNull("long")){

                                    clientMessage.setLat(jsonObject.getDouble("lat"));
                                    clientMessage.setLng(jsonObject.getDouble("long"));

                                  }
                            }
                        }

                    }
                }

                // Parse Delivery Message

                if(!(jsonObject = jsonArray.getJSONObject(0)).isNull("delivery") && !(jsonObject = jsonObject.getJSONObject("delivery")).isNull("watermark")){

                    clientMessage.setMessageType(Const.ClientMessageType.Delivery);

                    clientMessage.setWatermark(jsonObject.getLong("watermark"));

                    System.out.println(clientMessage.getWatermark());
                }

                // Parse Postback
                if(!(jsonObject = jsonArray.getJSONObject(0)).isNull("postback") && !(jsonObject = jsonObject.getJSONObject("postback")).isNull("payload")){

                    clientMessage.setMessageType(Const.ClientMessageType.Postback);
                    clientMessage.setPostBackId(jsonObject.getString("payload"));

                    System.out.println(clientMessage.getMessageType());
                    System.out.println(clientMessage.getPostBackId());
                }

            }
        }

        return clientMessage;
    }

    public abstract ActionResult execute();
}
