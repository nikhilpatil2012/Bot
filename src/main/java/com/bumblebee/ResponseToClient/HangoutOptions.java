package com.bumblebee.ResponseToClient;

import com.bumblebee.JSONCreator.*;
import com.bumblebee.common.utils.Const;
import com.bumblebee.common.utils.ConversationCodes;
import com.bumblebee.common.utils.ConversationPool;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by deadcode on 28/06/2016.
 */
public class HangoutOptions extends ResponseAction{

    private HashMap<String, String> list = new HashMap<>();


    @Override
    public ResponseActionResult execute() {

        //Create JSON

        ArrayList<Element> elementArrayList = new ArrayList<>();
        elementArrayList.add(new Element("Cafe", "Coffee", "http://www.clker.com/cliparts/d/4/d/9/1237562201214390563pitr_Coffee_cup_icon.svg", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Cafe", ConversationCodes.Cafe)})));
        elementArrayList.add(new Element("Bar", "Party", "https://image.freepik.com/free-icon/wine-glasses_318-80863.png", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Bars", ConversationCodes.Bar)})));
        elementArrayList.add(new Element("Dine", "Food", "http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons-256/magic-marker-icons-food-beverage/115502-magic-marker-icon-food-beverage-knife-fork-sc44.png", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Dine", ConversationCodes.Dine)})));
        elementArrayList.add(new Element("Attractions", "Fun", "http://i.giphy.com/l4HodBpDmoMA5p9bG.gif", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Attractions",ConversationCodes.Attraction)})));

        Payload payload = new Payload(Const.PayloadType.generic, new ElementJSONCntlr(elementArrayList));

        Attachment attachment = new Attachment(Const.AttachmentType.template, payload);

        MasterJSON masterJSON = new MasterJSON("931411386981115", attachment);

      //  System.out.println(masterJSON.getMasterJSON());

        return new ResponseActionResult(masterJSON);
    }

    public void createElement(){

    }
}
