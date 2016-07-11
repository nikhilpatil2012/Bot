package com.bumblebee.ConverstationFiles;

import com.bumblebee.common.utils.ConversationCodes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by deadcode on 03/07/2016.
 */
public class Postback {

    private ConversationCodes.PostbackType postbackType;
    private PostbackButton replyButton;
    private ArrayList<PostbackButton> buttonsList;

    public Postback(ConversationCodes.PostbackType postbackType, ArrayList<PostbackButton> buttonsList) {
        this.buttonsList = buttonsList;
        this.postbackType = postbackType;
    }

    public PostbackButton getReplyButton() {
        return replyButton;
    }

    public void setReplyButton(PostbackButton replyButton) {
        this.replyButton = replyButton;
    }

    public ArrayList<PostbackButton> getButtonsList() {
        return buttonsList;
    }

    public boolean isButtonPresent(int code){

         for(PostbackButton button : getButtonsList()){

              if(button.getCode() == code){
                  return true;
              }
         }

        return false;
    }
}
