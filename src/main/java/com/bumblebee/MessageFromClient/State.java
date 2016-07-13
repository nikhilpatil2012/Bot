package com.bumblebee.MessageFromClient;

import com.bumblebee.common.utils.ConversationPool;

/**
 * Created by deadcode on 11/07/2016.
 */
public class State {

    private ConversationPool.ClientStateType clientStateType;
    private int position;

    public State(ConversationPool.ClientStateType clientStateType, int position) {
        this.clientStateType = clientStateType;
        this.position = position;
    }

    public ConversationPool.ClientStateType getClientStateType() {
        return clientStateType;
    }

    public void setClientStateType(ConversationPool.ClientStateType clientStateType) {
        this.clientStateType = clientStateType;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
