package com.bumblebee.ConverstationFiles;

import com.bumblebee.common.utils.Const;

/**
 * Created by deadcode on 27/06/2016.
 */
public class Conversation {

    private Const.ClientMessageType type;
    private int code;

    public Conversation(Const.ClientMessageType type, int code){
        this.type = type;
        this.code = code;
    }

    public Const.ClientMessageType getType() {
        return type;
    }

    public int getCode() {
        return code;
    }
}
