package com.bumblebee.common.utils;

import com.bumblebee.ConverstationFiles.Conversation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by deadcode on 27/06/2016.
 */
public class ConversationPool {


    public static HashMap<Integer, String> codeList = new HashMap<>();
    static {
        codeList.put(101, "Hey");
        codeList.put(102, "I'm bobo, i can help you find awesome places for hangout");
        codeList.put(103, "So, what would you like to do ?");
        codeList.put(104, "Please share your location or place where you would like to hangout");
    }

    public static ArrayList<Conversation> StartPool = new ArrayList<>();
    static {
        StartPool.add(new Conversation(Const.ClientMessageType.Text, 101));
        StartPool.add(new Conversation(Const.ClientMessageType.Text, 102));
        StartPool.add(new Conversation(Const.ClientMessageType.Text, 103));
        StartPool.add(new Conversation(Const.ClientMessageType.Text, ConversationCodes.SHOW_HANGOUT_OPTIONS));
    }

    public static ArrayList<Conversation> ReturnPool = new ArrayList<>();
    static {
        ReturnPool.add(new Conversation(Const.ClientMessageType.Text, 101));
        ReturnPool.add(new Conversation(Const.ClientMessageType.Text, 103));
        ReturnPool.add(new Conversation(Const.ClientMessageType.Text, ConversationCodes.SHOW_HANGOUT_OPTIONS));
    }

    public static HashMap<Const.ClientStateType, ArrayList<Conversation>> poolList = new HashMap<>();
    static {
        poolList.put(Const.ClientStateType.StartPool, StartPool);
    }



}
