package com.bumblebee.ClientMessage;

import com.bumblebee.ChatbotFiles.Element;
import com.bumblebee.ChatbotFiles.Message;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by deadcode on 06/06/2016.
 */
public abstract class MultiPartCustom {

    public Message message;
    public List<Element> placesList;
    public HashMap<String, JSONObject> jsonObjectList = new HashMap<>();

    public MultiPartCustom(Message message){
        this.message = message;
    }

    public MultiPartCustom(List<Element> placesList){
        this.placesList = placesList;
    }

    public abstract void createMessage();

    public JSONObject getJsonObject(HashMap<String, String> list){

        JSONObject jsonObject = new JSONObject();

        for(String key : list.keySet()){
            jsonObject.put(key, list.get(key));
        }

        System.out.println(jsonObject.toString());

        return jsonObject;
    }

    public void addMessage(String key, JSONObject value){
       jsonObjectList.put(key,value);
    }

    public HashMap<String, JSONObject> getObjectList() {
        return jsonObjectList;
    }

    public Message getMessage() {
        return message;
    }

    public List<Element> getPlacesList() {
        return placesList;
    }
}
