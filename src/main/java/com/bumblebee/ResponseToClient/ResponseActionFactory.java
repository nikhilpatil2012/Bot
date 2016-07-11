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

    private ConversationCntrl conversationCntrl;

    public ResponseActionFactory(ConversationCntrl conversationCntrl) {
        this.conversationCntrl = conversationCntrl;
    }

    private static HashMap<String, Class<? extends ResponseAction>> responseActionMappings = new HashMap<>();

    static {

        responseActionMappings.put(Const.ClientMessageType.Text.name(), SendTextToClient.class);
        responseActionMappings.put(Const.ClientMessageType.Postback.name(), HangoutOptions.class);

    }

    public ResponseAction getAction() {

        Conversation conversation = getNextConversation(conversationCntrl);

        ResponseAction action = getAction(conversation.getType());
        action.init(conversationCntrl, conversation);

        return action;
    }

    private static ResponseAction getAction(Const.ClientMessageType clientMessageType) {

        Class<? extends ResponseAction> controllerClass = responseActionMappings.get(clientMessageType.name());

        try {
            return controllerClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not create the response action for :" + clientMessageType.name());
        }
    }

    private Conversation getNextConversation(ConversationCntrl conversationCntrl) {

        Conversation conversation = null;

        // Check next step
        int nextStep = conversationCntrl.getStep() + 1;

        System.out.println("Next Step " + nextStep);
        System.out.println("Pool Size " + ConversationPool.poolList.get(conversationCntrl.getClientStateType()).size());


        // Check if more conversation is left and Move Next is true
        if (ConversationPool.poolList.get(conversationCntrl.getClientStateType()).size() > nextStep && conversationCntrl.isMvNext()) {

            // More steps are left

            //Conversation temp = ConversationPool.poolList.get(conversationCntrl.getClientStateType()).get(nextStep);
            conversation = ConversationPool.poolList.get(conversationCntrl.getClientStateType()).get(nextStep);

            conversationCntrl.setStep(nextStep);
            conversationCntrl.setMvNext(conversation.isMvNext());


            System.out.println("Move Next " + conversationCntrl.isMvNext());
        }

        return conversation;
    }
}