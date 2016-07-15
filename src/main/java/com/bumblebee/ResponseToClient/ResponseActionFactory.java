package com.bumblebee.ResponseToClient;

import com.bumblebee.Controller.Action;
import com.bumblebee.ConverstationFiles.Conversation;
import com.bumblebee.ConverstationFiles.ConversationCntrl;
import com.bumblebee.ConverstationFiles.ConversationHandler;
import com.bumblebee.MessageFromClient.State;
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

        if(conversation == null){
            return null;
        }

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

        // Move Forward
        if(conversationCntrl.isMvNext()){

            // Check if more conversation is left and Move Next is true
            if (ConversationPool.poolList.get(conversationCntrl.getClientStateType()).size() > nextStep) {

                // More steps are left
                conversationCntrl.setStep(nextStep);

                conversation = ConversationPool.poolList.get(conversationCntrl.getClientStateType()).get(conversationCntrl.getStep());

                conversationCntrl.setMvNext(conversation.isMvNext());

                System.out.println("Move Next " + conversationCntrl.isMvNext());
            }
              else if(conversationCntrl.getPreviousState() != null) {

                State previousState = conversationCntrl.getPreviousState();

                // Check next step
                nextStep = previousState.getPosition() + 1;

                if(ConversationPool.poolList.get(previousState.getClientStateType()).size() > nextStep){

                    // Update previous state
                    conversationCntrl.setStep(previousState.getPosition());
                    conversationCntrl.setClientStateType(previousState.getClientStateType());


                    // Get conversation from previous state
                    conversation = ConversationPool.poolList.get(conversationCntrl.getClientStateType()).get(nextStep);
                    conversationCntrl.setMvNext(conversation.isMvNext());


                    // Clear the previous state
                    conversationCntrl.setPreviousState(null);
                }
            }
/*             // Refresh the list
             else if(conversationCntrl.getClientStateType().compareTo(ConversationPool.ClientStateType.StartPool) == 0) {

                conversationCntrl.setStep(-1);

            }*/


        }

        return conversation;
    }

    public void loadPreviousState(){



    }

    public ConversationCntrl getConversationCntrl() {
        return conversationCntrl;
    }
}