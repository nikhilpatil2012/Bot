package com.bumblebee.common.utils;

import com.bumblebee.ConverstationFiles.PostbackButton;
import com.bumblebee.ConverstationFiles.QuickReply;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by deadcode on 30/06/2016.
 */
public class ConversationCodes {

    // Hangout Options

    public static final int SHOW_HANGOUT_OPTIONS = 301;

    public static final int Cafe = 3011;
    public static final int Bar = 3022;
    public static final int Dine = 3033;
    public static final int Attraction = 3044;

    public static final HashMap<Integer, String> conversationNames = new HashMap<>();
    static {
        conversationNames.put(Cafe, "Cafe");
        conversationNames.put(Bar, "Bar");
        conversationNames.put(Dine, "Dine");
        conversationNames.put(Attraction, "Attractions");
    }

/*    public static ArrayList<Postback> HangoutOptions = new ArrayList<>();
    static {
        HangoutOptions.add(new Postback(Cafe, SHOW_HANGOUT_OPTIONS, "Cafe"));
        HangoutOptions.add(new Postback(Bar, SHOW_HANGOUT_OPTIONS, "Bar"));
        HangoutOptions.add(new Postback(Dine, SHOW_HANGOUT_OPTIONS, "Dine"));
        HangoutOptions.add(new Postback(Attraction, SHOW_HANGOUT_OPTIONS, "Attractions"));
    }*/

    public static final int SHARE_LOCATION_HINT = 303;
    public static final int SHARE_LOCATION_GIF = 304;

    // CLIENT TO SERVER CODES
    public static final int SHOW_CAFE = 401;
    public static final int SHOW_RESTRO = 402;
    public static final int SHOW_PUBS = 403;

    public static final int SEND_TEXT_TO_CLIENT = 501;

    public static enum PostbackType {
        Food_Options, Hangout_Options
    }

    public static int HangoutOptions = 101;
    public static ArrayList<QuickReply> HangoutQuickReply = new ArrayList<>();
    static {
        HangoutQuickReply.add(new QuickReply(Const.ClientMessageType.Text, "Cafe"));
        HangoutQuickReply.add(new QuickReply(Const.ClientMessageType.Text, "Bar"));
        HangoutQuickReply.add(new QuickReply(Const.ClientMessageType.Text, "Dine"));
        HangoutQuickReply.add(new QuickReply(Const.ClientMessageType.Text, "Attraction"));
    }


    public static HashMap<PostbackType, PostbackButton> postbackButtonList = new HashMap<>();
    static {
        postbackButtonList.put(PostbackType.Hangout_Options, new PostbackButton("Cafe", 3011));
        postbackButtonList.put(PostbackType.Hangout_Options, new PostbackButton("Bar", 3012));
        postbackButtonList.put(PostbackType.Hangout_Options, new PostbackButton("Dine", 3013));
        postbackButtonList.put(PostbackType.Hangout_Options, new PostbackButton("Attraction", 3014));

        postbackButtonList.put(PostbackType.Food_Options, new PostbackButton("Chinese", 1011));
        postbackButtonList.put(PostbackType.Food_Options, new PostbackButton("Indian", 1012));
        postbackButtonList.put(PostbackType.Food_Options, new PostbackButton("Thai", 1013));
        postbackButtonList.put(PostbackType.Food_Options, new PostbackButton("Mexican", 1014));
        postbackButtonList.put(PostbackType.Food_Options, new PostbackButton("Italian", 1015));

    }

    public static ArrayList<PostbackButton> getPostbackBtnList(ConversationCodes.PostbackType type) {

        ArrayList<PostbackButton> list = new ArrayList<>();

        for(ConversationCodes.PostbackType postbackType : ConversationCodes.postbackButtonList.keySet()){

            if(postbackType.compareTo(type) == 0){
                list.add(ConversationCodes.postbackButtonList.get(postbackType));
            }
        }

        return list;
    }

    public static PostbackButton getButtonFromCode(int code){

        for(PostbackType postbackType : postbackButtonList.keySet()){

             if(postbackButtonList.get(postbackType).getCode() == code){

                 return postbackButtonList.get(postbackType);
             }
        }

        return null;
    }

    }

