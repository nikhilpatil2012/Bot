package com.bumblebee.ConverstationFiles;

import com.bumblebee.common.utils.Const;

/**
 * Created by deadcode on 09/07/2016.
 */
public class QuickReply {

    private String title;
    private Const.ClientMessageType clientMessageType;

    public QuickReply(Const.ClientMessageType clientMessageType, String title) {
        this.title = title;
        this.clientMessageType = clientMessageType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Const.ClientMessageType getClientMessageType() {
        return clientMessageType;
    }

    public void setClientMessageType(Const.ClientMessageType clientMessageType) {
        this.clientMessageType = clientMessageType;
    }

}
