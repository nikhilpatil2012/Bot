package com.bumblebee.JSONCreator;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by deadcode on 28/06/2016.
 */
public class ElementJSONCntlr {

    private ArrayList<Element> elementArrayList;

    public ElementJSONCntlr(ArrayList<Element> elementArrayList) {
        this.elementArrayList = elementArrayList;
    }

    public JSONArray getElementsJSON(){

        JSONArray elementsArray = new JSONArray();

         for(Element element : elementArrayList){

             JSONObject jsonObject = new JSONObject();

             jsonObject.put("title", element.getTitle());
             jsonObject.put("image_url", element.getImageUrl());
             jsonObject.put("subtitle", element.getSubtitle());
             jsonObject.put("buttons", element.getButtonJSONCntlr().getButtonsJsonFromArray());

             // Add the JSON object to the JSONArray
             elementsArray.put(jsonObject);

         }

        return elementsArray;
    }

}
