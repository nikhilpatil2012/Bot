package com.bumblebee.JSONCreator;

import com.bumblebee.common.utils.Const;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by deadcode on 28/06/2016.
 */
public class ButtonJSONCntlr {

    private ArrayList<Button> buttonArrayList;
    private Button[] buttonsArray;

    public ButtonJSONCntlr(ArrayList<Button> buttonArrayList) {
        this.buttonArrayList = buttonArrayList;
    }

    public ButtonJSONCntlr(Button[] buttonsArray) {
        this.buttonsArray = buttonsArray;
    }

    public JSONArray getButtonsJson(){

        JSONArray buttonArray = new JSONArray();

        for(Button button : buttonArrayList){

            // Add the JSON object to the array
            buttonArray.put(getJsonObject(button));
        }

        return buttonArray;

    }

    public JSONArray getButtonsJsonFromArray(){

        JSONArray buttonArray = new JSONArray();

        for(Button button : buttonsArray){

            // Add the JSON object to the array
            buttonArray.put(getJsonObject(button));
        }

        return buttonArray;

    }

    public JSONObject getJsonObject(Button button){

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("type", button.getButtonType().name());
        jsonObject.put("title", button.getTitle());

        if(button.getButtonType().compareTo(Const.ButtonType.web_url) == 0){

            jsonObject.put("url", button.getWebUrl());
        }
        else if(button.getButtonType().compareTo(Const.ButtonType.postback) == 0){

            jsonObject.put("payload", button.getPayloadCode());
        }

        return jsonObject;
    }


}
