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

        // Update conversation cntrl with postback
        updatePostback(ConversationCodes.PostbackType.Hangout_Options);

        //Create JSON

        int masterCode = getConversation().getCode();


        ArrayList<Element> elementArrayList = new ArrayList<>();
        elementArrayList.add(new Element("Cafe", "Coffee", "http://i.giphy.com/dGhlifOCTtSdW.gif", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Cafe", masterCode+"_Cafe", null)})));
        elementArrayList.add(new Element("Bar", "Party", "http://i.giphy.com/DlGaTfcMeDmz6.gif", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Bars", masterCode+"_Bar", null)})));
        elementArrayList.add(new Element("Dine", "Food", "http://i.giphy.com/3o85xBgXf4rAt5H7RS.gif", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Dine", masterCode+"_Dine", null)})));
        elementArrayList.add(new Element("Attractions", "Fun", "http://i.giphy.com/11MKLWSDvMVSp2.gif", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Attractions", masterCode+"_Attraction", null)})));

        Payload payload = new Payload(Const.PayloadType.generic, new ElementJSONCntlr(elementArrayList));

        Attachment attachment = new Attachment(Const.AttachmentType.template, payload);

        MasterJSON masterJSON = new MasterJSON("931411386981115", attachment);

      //  System.out.println(masterJSON.getMasterJSON());

        return new ResponseActionResult(masterJSON);
    }

    public void createElement(){

    }
}
