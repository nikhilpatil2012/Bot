package com.bumblebee.ResponseToClient;

import com.bumblebee.ChatbotFiles.*;
import com.bumblebee.JSONCreator.*;
import com.bumblebee.JSONCreator.Element;
import com.bumblebee.common.utils.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by deadcode on 28/06/2016.
 */
public class HangoutOptions extends ResponseAction{

    private HashMap<String, String> list = new HashMap<>();


    @Override
    public void execute(FinalCallback finalCallback) {

        // Update conversation cntrl with postback
        updatePostback(ConversationCodes.PostbackType.Hangout_Options);

        //Create JSON

        int masterCode = getConversation().getCode();

        switch (masterCode) {

            case ConversationPool.SHOW_HANGOUT_OPTIONS :{

                ArrayList<Element> elementArrayList = new ArrayList<>();

                elementArrayList.add(new Element("Cafe", "Coffee", "http://i.giphy.com/dGhlifOCTtSdW.gif", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Cafe", masterCode+"_Cafe", null)})));
                elementArrayList.add(new Element("Bar", "Party", "http://i.giphy.com/DlGaTfcMeDmz6.gif", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Bars", masterCode+"_Bar", null)})));
                elementArrayList.add(new Element("Dine", "Food", "http://i.giphy.com/3o85xBgXf4rAt5H7RS.gif", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Dine", masterCode+"_Dine", null)})));
                elementArrayList.add(new Element("Attractions", "Fun", "http://i.giphy.com/11MKLWSDvMVSp2.gif", new ButtonJSONCntlr(new Button[]{new Button(Const.ButtonType.postback,"Show Attractions", masterCode+"_Attraction", null)})));

                Payload payload = new Payload(Const.PayloadType.generic, new ElementJSONCntlr(elementArrayList));

                Attachment attachment = new Attachment(Const.AttachmentType.template, payload);

                MasterJSON  masterJSON = new MasterJSON("931411386981115", attachment);

                finalCallback.masterJsonCallback(masterJSON);
            }break;

            case ConversationPool.SHOW_FOURSQUARE_OPTIONS: {

                FetchDataFromFourSq fetchDataFromFourSq = new FetchDataFromFourSq();

                Query query = new Query();


                if(getConversationCntrl().getHangoutOption() != null){

                    query.setWhat(getConversationCntrl().getHangoutOption());
                }


                if(getConversationCntrl().getLat() != 0.0 && getConversationCntrl().getLng() != 0.0){

                    query.setWhere(getConversationCntrl().getLat()+","+getConversationCntrl().getLng());
                }

                System.out.println("Where = "+query.getWhere()+"--"+query.getWhat());

                ArrayList<Element> elementArrayList = new ArrayList<>();

                fetchDataFromFourSq.sendQueryToFourSq(query, new PlaceListCallback() {
                    @Override
                    public void getPlaceList(ArrayList<com.bumblebee.ChatbotFiles.Element> placesList) {

                        for(com.bumblebee.ChatbotFiles.Element element : placesList){

                            String url = element.getDetailUrl();

                            if(url == null){
                              url = "https://foursquare.com/";
                            }

                            Button button = new Button(Const.ButtonType.web_url, "Show Details", null, url);

                            ButtonJSONCntlr buttonJSONCntlr = new ButtonJSONCntlr(new Button[]{button});

                            Element e = new Element(element.getTitle(), element.getSubtitle(), element.getImageUrl(), buttonJSONCntlr);

                            elementArrayList.add(e);

                            System.out.println(element.getTitle());

                        }


                        Payload payload = new Payload(Const.PayloadType.generic, new ElementJSONCntlr(elementArrayList));

                        Attachment attachment = new Attachment(Const.AttachmentType.template, payload);

                        MasterJSON masterJSON = new MasterJSON("931411386981115", attachment);

                        finalCallback.masterJsonCallback(masterJSON);

                    }
                });

            }break;
        }


      //  System.out.println(masterJSON.getMasterJSON());

    }

    public void createElement(){

    }
}
