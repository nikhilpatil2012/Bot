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
        elementArrayList.add(new Element("Cafe", "Coffee", "http://petersapparel.parseapp.com/img/item100-thumb.png", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Cafe", ConversationCodes.Cafe)})));
        elementArrayList.add(new Element("Bar", "Party", "http://petersapparel.parseapp.com/img/item100-thumb.png", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Bars", ConversationCodes.Bar)})));
        elementArrayList.add(new Element("Dine", "Food", "http://petersapparel.parseapp.com/img/item100-thumb.png", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Dine", ConversationCodes.Dine)})));
        elementArrayList.add(new Element("Attractions", "Fun", "http://petersapparel.parseapp.com/img/item100-thumb.png", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Attractions",ConversationCodes.Attraction)})));

        Payload payload = new Payload(Const.PayloadType.generic, new ElementJSONCntlr(elementArrayList));

        Attachment attachment = new Attachment(Const.AttachmentType.template, payload);

        MasterJSON masterJSON = new MasterJSON("931411386981115", attachment);

      //  System.out.println(masterJSON.getMasterJSON());

        return new ResponseActionResult(masterJSON);
    }

    public void createElement(){

    }
}
