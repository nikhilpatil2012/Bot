package com.bumblebee.common.utils;

import com.bumblebee.ConverstationFiles.ConversationCntrl;
import com.bumblebee.ResponseToClient.ResponseActionResult;

import java.util.HashMap;

/**
 * Created by deadcode on 26/04/16.
 */
public class Const {

    public class ActionURIs{
        public static final String WEB_HOOK = "/webhook";
    }

    public static enum ClientMessageType{

        Text, Delivery, Postback, TextWithAttach, Read

    }



    public static enum ClientAttachmentType{

      image

    }

    public static enum ButtonType{
        web_url, postback
    }


    public  static enum AttachmentType{
        template, image, location
    }

    public static enum PayloadType{
        basic, button, generic
    }

    public static String[] foodTypes =  {"Chinese", "Indian", "Thai", "Italian", "Mexican"};



    public static HashMap<String, ConversationCntrl> activeSessions = new HashMap<>();

}
