package com.bumblebee.ConverstationFiles;

import com.bumblebee.ClientMessage.ClientMessage;
import com.bumblebee.common.utils.Const;
import com.bumblebee.common.utils.ConversationPool;

/**
 * Created by deadcode on 27/06/2016.
 */
public class ConversationHandler {

    private ClientMessage clientMessage;
    private Conversation conversation;

    public ConversationHandler(ClientMessage clientMessage){
        this.clientMessage = clientMessage;

        processConversation();
    }

    public void processConversation(){

        // Returning User
        if(Const.activeSessions.keySet().contains(clientMessage.getSenderId())){

            ConversationCntrl conversationCntrl = Const.activeSessions.get(clientMessage.getSenderId());

            // Check next step
            int nextStep = conversationCntrl.getStep() + 1;

            // Check if more conversation is left
            if(ConversationPool.poolList.get(conversationCntrl.getClientStateType()).size() > nextStep){

                // More steps are left
                conversation = ConversationPool.poolList.get(conversationCntrl.getClientStateType()).get(nextStep);

            }

/*            // Process Postback
            if(clientMessage.getMessageType().compareTo(Const.ClientMessageType.Postback) == 0){



            }*/

            // Process Delivery
    /*        if(clientMessage.getMessageType().compareTo(Const.ClientMessageType.Delivery) == 0){

                ConversationCntrl conversationCntrl = Const.activeSessions.get(clientMessage.getSenderId());

                // Check next step
                int nextStep = conversationCntrl.getStep() + 1;

                // Check if more conversation is left
                if(ConversationPool.poolList.get(conversationCntrl.getClientStateType()).size() > nextStep){

                    // More steps are left
                    conversation = ConversationPool.poolList.get(conversationCntrl.getClientStateType()).get(nextStep);

                }

                // Process the conversation

            }*/

        }
          else { // New User

            // Add to the converstation
            ConversationCntrl conversationCntrl = new ConversationCntrl();
            conversationCntrl.setClientMessageType(Const.ClientMessageType.Text);
            conversationCntrl.setClientStateType(Const.ClientStateType.StartPool);
            conversationCntrl.setStep(0);

            Const.activeSessions.put(clientMessage.getSenderId(), conversationCntrl);

            // Get the first conversation to send
            conversation = ConversationPool.StartPool.get(0);


        }
    }

    public Conversation getConversation() {
        return conversation;
    }
}
