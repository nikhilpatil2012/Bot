package com.bumblebee.ResponseToClient;

import com.bumblebee.Controller.Action;
import com.bumblebee.ConverstationFiles.Conversation;
import com.bumblebee.ConverstationFiles.ConversationCntrl;
import com.bumblebee.ConverstationFiles.ConversationHandler;
import com.bumblebee.ResponseToClient.HangoutOptions;
import com.bumblebee.ResponseToClient.ResponseAction;
import com.bumblebee.common.utils.Const;
import com.bumblebee.common.utils.ConversationCodes;
import com.bumblebee.common.utils.ConversationPool;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by deadcode on 26/04/16.
 */
public class ResponseActionFactory {

    private static HashMap<Integer, Class<? extends ResponseAction>> responseActionMappings = new HashMap<>();

    static {
        map(ConversationCodes.SHOW_HANGOUT_OPTIONS, HangoutOptions.class);
        map(ConversationCodes.SEND_TEXT_TO_CLIENT, SendTextToClient.class);

    }

    public ResponseAction getAction(ConversationCntrl conversationCntrl){

        Conversation conversation = conversationCntrl.getConversation();

        int nextConversationCode = conversation.getCode();

        if(ConversationPool.codeList.containsKey(nextConversationCode)){
            nextConversationCode = ConversationCodes.SEND_TEXT_TO_CLIENT;
        }

        ResponseAction action = getAction(nextConversationCode);
        action.init(conversationCntrl);

        return action;
    }

    private static ResponseAction getAction(int code){

        Class<? extends ResponseAction> controllerClass = responseActionMappings.get(code);

        try {
            return controllerClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not create the response action for :" + code);
        }
    }


    private static void map(Integer code, Class<? extends ResponseAction> responseActionClass) {
        responseActionMappings.put(code, responseActionClass);
    }

}
