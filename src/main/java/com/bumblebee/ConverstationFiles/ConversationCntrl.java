package com.bumblebee.ConverstationFiles;

import com.bumblebee.common.utils.Const;

/**
 * Created by deadcode on 26/06/2016.
 */
public class ConversationCntrl {

    private Const.ClientMessageType clientMessageType;
    private Const.ClientStateType clientStateType;
    private int step;


    public Const.ClientMessageType getClientMessageType() {
        return clientMessageType;
    }

    public void setClientMessageType(Const.ClientMessageType clientMessageType) {
        this.clientMessageType = clientMessageType;
    }

    public Const.ClientStateType getClientStateType() {
        return clientStateType;
    }

    public void setClientStateType(Const.ClientStateType clientStateType) {
        this.clientStateType = clientStateType;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
