package com.bumblebee.common.utils;

import com.bumblebee.ConverstationFiles.Conversation;
import com.bumblebee.ConverstationFiles.Postback;

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

    public static ArrayList<Postback> HangoutOptions = new ArrayList<>();
    static {
        HangoutOptions.add(new Postback(Cafe, SHOW_HANGOUT_OPTIONS, "Cafe"));
        HangoutOptions.add(new Postback(Bar, SHOW_HANGOUT_OPTIONS, "Bar"));
        HangoutOptions.add(new Postback(Dine, SHOW_HANGOUT_OPTIONS, "Dine"));
        HangoutOptions.add(new Postback(Attraction, SHOW_HANGOUT_OPTIONS, "Attractions"));
    }

    public static final int SHARE_LOCATION_HINT = 303;
    public static final int SHARE_LOCATION_GIF = 304;

    // CLIENT TO SERVER CODES
    public static final int SHOW_CAFE = 401;
    public static final int SHOW_RESTRO = 402;
    public static final int SHOW_PUBS = 403;

    public static final int SEND_TEXT_TO_CLIENT = 501;

}
