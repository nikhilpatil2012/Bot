package com.bumblebee.MessageFromClient;

import com.bumblebee.ClientMessage.ClientMessage;
import com.bumblebee.common.utils.Const;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by deadcode on 10/07/2016.
 */
public class ClientMessageCreator {

    public  ClientMessage getClientMessagePOJO(String json){

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

                    System.out.println("This is delievery");

                    // Parse Delivery Message
                    parseDeliveryConfirmation(clientMessage, jsonObject);
                }
                else if(!(jsonObject = jsonArray.getJSONObject(0)).isNull("read")){

                    System.out.println("This is Read Message");

                    // Parse Delivery Message
                    parseReadConfirmation(clientMessage, jsonObject);
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

       JSONObject message = jsonObject.getJSONObject("message");

        if(!message.isNull("is_echo") && message.getBoolean("is_echo")){

            System.out.println("Message is Echo");

            parseMessageEcho(clientMessage, jsonObject);
        }
          else {

            System.out.println("Message is Normal");

            if(!message.isNull("text")){

                parseText(clientMessage, message);
            }
            else if(!message.isNull("attachments")){

                parseAttachment(clientMessage, message);
            }
        }
    }

    private  void parseMessageEcho(ClientMessage clientMessage, JSONObject jsonObject){

        jsonObject = jsonObject.getJSONObject("message");

        if(!jsonObject.isNull("text")){

            parseText(clientMessage, jsonObject);

            // Set Message Type
            clientMessage.setMessageType(Const.ClientMessageType.EchoText);
        }
        else if(!jsonObject.isNull("attachments")){

            parseAttachment(clientMessage, jsonObject);

            // Set Message Type
            clientMessage.setMessageType(Const.ClientMessageType.EchoTextWithAttach);
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

    private  void parseReadConfirmation(ClientMessage clientMessage, JSONObject jsonObject){

        JSONObject deliveryJSON = jsonObject.getJSONObject("read");
        clientMessage.setMessageType(Const.ClientMessageType.Read);
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


        System.out.println(clientMessage.getLat()+"--"+clientMessage.getLng());
    }

}
