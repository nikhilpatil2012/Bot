package com.bumblebee.MessageFromClient;

import com.bumblebee.ClientMessage.ClientMessage;
import com.bumblebee.ConverstationFiles.Conversation;
import com.bumblebee.ConverstationFiles.ConversationCntrl;
import com.bumblebee.ConverstationFiles.GetUserProfile;
import com.bumblebee.common.utils.Const;
import com.bumblebee.common.utils.ConversationPool;
import com.bumblebee.common.utils.UserProfileCallback;
import com.bumblebee.model.User;

/**
 * Created by deadcode on 10/07/2016.
 */
public class ConversationCntrlCreator {


    public ConversationCntrl getConversationCntrl(ClientMessage clientMessage){

        ConversationCntrl conversationCntrl;

        // Returning User
        if(Const.activeSessions.keySet().contains(clientMessage.getSenderId())){

             conversationCntrl = Const.activeSessions.get(clientMessage.getSenderId());

        }
         else {

             conversationCntrl = getNewConversationCntrl(clientMessage.getSenderId());

        }

        return conversationCntrl;
    }

    private ConversationCntrl getNewConversationCntrl(String senderId){

        final ConversationCntrl conversationCntrl = new ConversationCntrl();
        conversationCntrl.setClientMessageType(Const.ClientMessageType.Text);
        conversationCntrl.setClientStateType(ConversationPool.ClientStateType.StartPool);
        conversationCntrl.setStep(-1);
        conversationCntrl.setMvNext(true);
        conversationCntrl.setUserId(senderId);
        conversationCntrl.setFirstName("Nikhil");

        GetUserProfile getUserProfile = new GetUserProfile(conversationCntrl);
        getUserProfile.getUserProfile(user -> {

            conversationCntrl.setFirstName(user.getFirstName());

            System.out.println("User Name "+user.getFirstName());
            System.out.println("CC is "+conversationCntrl.getFirstName());
            System.out.println(conversationCntrl.getClientMessageType());


        });

        Const.activeSessions.put(senderId, conversationCntrl);

        return conversationCntrl;
    }
}
