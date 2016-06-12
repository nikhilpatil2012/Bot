package com.bumblebee.ClientMessage;

import com.bumblebee.ChatbotFiles.Element;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by deadcode on 06/06/2016.
 */
public class MultiPartBodyWithPayload extends MultiPartCustom{

    public MultiPartBodyWithPayload(List<Element> placesList){
        super(placesList);
    }

    @Override
    public void createMessage() {

        for(Element element : getPlacesList()){

          System.out.println(element.getTitle());
          System.out.println(element.getSubtitle());


        }

        // JSON 1 :- Key -- "recipient"
        JSONObject recipient = new JSONObject();
        recipient.put("id", "931411386981115");

        // Create Elements Array
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;

        for(Element element : getPlacesList()){

            jsonObject = new JSONObject();

            jsonObject.put("title", element.getTitle());
            jsonObject.put("subtitle", element.getSubtitle());
            jsonObject.put("image_url", element.getImageUrl());

            if(element.getDetailUrl() != null){

                JSONArray buttons = new JSONArray();

                JSONObject buttonObj = new JSONObject();
                buttonObj.put("type", "web_url");
                buttonObj.put("url", element.getDetailUrl());
                buttonObj.put("title", "View Detail");

                buttons.put(buttonObj);

                jsonObject.put("buttons", buttons);
            }

            jsonArray.put(jsonObject);
        }

        // Element's Object
        JSONObject elementObject = new JSONObject();
        elementObject.put("elements", jsonArray);

        //Payload
        JSONObject payload = new JSONObject();
        payload.put("template_type", "generic");
        payload.put("elements", jsonArray);

        JSONObject attachment = new JSONObject();
        attachment.put("type", "template");
        attachment.put("payload", payload);

        // JSON 2 :- Key -- "atachment"
        JSONObject json2 = new JSONObject();
        json2.put("attachment", attachment);

        // Now, add message
        addMessage("recipient", recipient);
        addMessage("message", json2);

        System.out.println(recipient);
        System.out.println(json2);
    }
}
