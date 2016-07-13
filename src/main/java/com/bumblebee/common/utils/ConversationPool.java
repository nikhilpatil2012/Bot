package com.bumblebee.common.utils;

import com.bumblebee.ConverstationFiles.Conversation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by deadcode on 27/06/2016.
 */
public class ConversationPool {

    public static enum ClientStateType{

        StartPool, FoodPool, LocationPool

    }

    public static HashMap<Integer, String> codeList = new HashMap<>();
    static {
        codeList.put(101, "Hey %s");
        codeList.put(102, "I'm bobo, i can help you find awesome places for hangout");
        codeList.put(103, "So, what would you like to do ?");
        codeList.put(104, "Sweet..");
        codeList.put(105, "Please share your location or place where you would like to hangout");
    }

    public static final int SHOW_HANGOUT_OPTIONS = 301;
    public static final int SHOW_FOURSQUARE_OPTIONS = 302;

    public static ArrayList<Conversation> StartPool = new ArrayList<>();
    static {

        StartPool.add(new Conversation(Const.ClientMessageType.Text, "Hey %s", true));
        StartPool.add(new Conversation(Const.ClientMessageType.Text, "I'm bobo, i can help you find awesome places for hangout", true));
        StartPool.add(new Conversation(Const.ClientMessageType.Text, "So, what would you like to do ?", true));
        StartPool.add(new Conversation(Const.ClientMessageType.Postback, ConversationPool.SHOW_HANGOUT_OPTIONS, false)); // Expecting a postback
        StartPool.add(new Conversation(Const.ClientMessageType.Text, "Sweet..", true));
        StartPool.add(new Conversation(Const.ClientMessageType.Text, "Please share your location or place where you would like to hangout", false)); // Expecting a location as an attachment
        StartPool.add(new Conversation(Const.ClientMessageType.Text, "Thanks for sharing your location,", true));
        StartPool.add(new Conversation(Const.ClientMessageType.Postback, ConversationPool.SHOW_FOURSQUARE_OPTIONS, true));

    }

    public static ArrayList<Conversation> FoodPool = new ArrayList<>();
    static {
        FoodPool.add(new Conversation(Const.ClientMessageType.Text, "So what food you like ?", true));
        FoodPool.add(new Conversation(Const.ClientMessageType.Text, "Is it mexican", true));
        FoodPool.add(new Conversation(Const.ClientMessageType.Text, "or is it indian", true));
    }

    public static HashMap<ConversationPool.ClientStateType, ArrayList<Conversation>> poolList = new HashMap<>();
    static {
        poolList.put(ClientStateType.StartPool, StartPool);
        poolList.put(ClientStateType.FoodPool, FoodPool);
    }



}
