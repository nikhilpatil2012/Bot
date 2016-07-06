package com.bumblebee.ConverstationFiles;

import com.bumblebee.ClientMessage.ClientMessage;
import com.bumblebee.common.utils.Const;
import com.bumblebee.common.utils.ConversationPool;
import com.bumblebee.common.utils.UserProfileCallback;
import com.bumblebee.model.User;

import java.util.concurrent.TimeUnit;

/**
 * Created by deadcode on 27/06/2016.
 */
public class ConversationHandler {

    private ClientMessage clientMessage;
    private Conversation conversation;
    private ConversationCntrl conversationCntrl;

    public ConversationHandler(ClientMessage clientMessage){
        this.clientMessage = clientMessage;

        processConversation();
    }

    public void processConversation(){

        // Returning User
        if(Const.activeSessions.keySet().contains(clientMessage.getSenderId())){

            conversationCntrl = Const.activeSessions.get(clientMessage.getSenderId());
            conversationCntrl.setUserId(clientMessage.getSenderId());

            // Client sends TextWithAttach
            if(clientMessage.getMessageType().compareTo(Const.ClientMessageType.TextWithAttach) == 0){

                if(clientMessage.getAttachmentType().equals(Const.AttachmentType.location.name())){

                    conversationCntrl.setLat(clientMessage.getLat());
                    conversationCntrl.setLat(clientMessage.getLng());

                    conversationCntrl.setMvNext(true);

                    moveConversation(conversationCntrl);

                }
            }

            // Client sends Postback
            if(clientMessage.getMessageType().compareTo(Const.ClientMessageType.Postback) == 0){

                conversationCntrl.setMvNext(true);
                conversationCntrl.setPostbackId(clientMessage.getPostBackId());

                moveConversation(conversationCntrl);
            }

            // Fb sends Delivery
            if(clientMessage.getMessageType().compareTo(Const.ClientMessageType.Delivery) == 0){


                moveConversation(conversationCntrl);

            }

        }
          else { // New User

            // Add to the converstation

            conversationCntrl = new ConversationCntrl();
            conversationCntrl.setClientMessageType(Const.ClientMessageType.Text);
            conversationCntrl.setClientStateType(Const.ClientStateType.StartPool);
            conversationCntrl.setStep(0);
            conversationCntrl.setMvNext(true);

            conversationCntrl.setUserId(clientMessage.getSenderId());

            Const.activeSessions.put(clientMessage.getSenderId(), conversationCntrl);

            // Get the first conversation to send
            conversation = ConversationPool.StartPool.get(0);
            conversationCntrl.setConversation(conversation);

            GetUserProfile getUserProfile = new GetUserProfile(conversationCntrl);
            getUserProfile.getUserProfile(new UserProfileCallback() {
                @Override
                public void getUserCallback(User user) {

                    conversationCntrl.setFirstName(user.getFirstName());
                    conversationCntrl.setLastName(user.getLastName());
                    conversationCntrl.setGender(user.getGender());

                    System.out.println(conversationCntrl.getFirstName()+ conversationCntrl.getLastName()+ conversationCntrl.getGender());
                }
            });



        }
    }

    private void moveConversation(ConversationCntrl conversationCntrl){

        // Check next step
        int nextStep = conversationCntrl.getStep() + 1;

        System.out.println("Next Step "+nextStep);
        System.out.println("Pool Size "+ConversationPool.poolList.get(conversationCntrl.getClientStateType()).size());


        // Check if more conversation is left and Move Next is true
        if(ConversationPool.poolList.get(conversationCntrl.getClientStateType()).size() > nextStep && conversationCntrl.isMvNext()){

            // More steps are left

            //Conversation temp = ConversationPool.poolList.get(conversationCntrl.getClientStateType()).get(nextStep);
            conversation = ConversationPool.poolList.get(conversationCntrl.getClientStateType()).get(nextStep);

            conversationCntrl.setClientMessageType(conversation.getType());
            conversationCntrl.setStep(nextStep);
            conversationCntrl.setMvNext(conversation.isMvNext());

            conversationCntrl.setConversation(conversation);

            System.out.println("Move Next "+conversationCntrl.isMvNext());
        }
    }

    public Conversation getConversation() {
        return conversation;
    }

    public ConversationCntrl getConversationCntrl() {
        return conversationCntrl;
    }
}
