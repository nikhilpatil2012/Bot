package com.bumblebee.ClientMessage;

import com.bumblebee.ConverstationFiles.Conversation;
import com.bumblebee.ConverstationFiles.ConversationCntrl;
import com.bumblebee.common.utils.Const;
import com.bumblebee.common.utils.ConversationPool;

/**
 * Created by deadcode on 30/06/2016.
 */
public abstract class HandlerAction {

    private ClientMessage clientMessage;
    private boolean userStatus = false;
    private String text;

    public abstract void execute();

    public HandlerAction(ClientMessage clientMessage){
        this.clientMessage = clientMessage;

        // Process User Status
        processUserStatus();
     }

    private void processUserStatus(){

    }

    public boolean getUserStatus(){
        return userStatus;
    }


    public String getText() {
        return text;
    }
}
