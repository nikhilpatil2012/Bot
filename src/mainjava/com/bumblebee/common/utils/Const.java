package com.bumblebee.common.utils;

import com.bumblebee.ConverstationFiles.ConversationCntrl;

import java.util.HashMap;

/**
 * Created by deadcode on 26/04/16.
 */
public class Const {

    public class ActionURIs{
        public static final String WEB_HOOK = "/webhook";
    }

    public static enum ClientMessageType{

        Text, Delivery, Postback, TextWithAttach

    }

    public static enum ClientStateType{

        StartPool, ReturnPool, LocationPool

    }

    public static enum ClientAttachmentType{

      image

    }

    public static enum ButtonType{
        web_url, postback
    }

    public static enum AttachmentType{
        template, image
    }

    public static enum PayloadType{
        basic, button, generic
    }



    public static HashMap<String, ConversationCntrl> activeSessions = new HashMap<>();

}
