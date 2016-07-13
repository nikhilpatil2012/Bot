package com.bumblebee.ConverstationFiles;

import com.bumblebee.MessageFromClient.State;
import com.bumblebee.common.utils.Const;
import com.bumblebee.common.utils.ConversationPool;
import com.bumblebee.model.User;

import java.util.ArrayList;

/**
 * Created by deadcode on 26/06/2016.
 */
public class ConversationCntrl {

    private Const.ClientMessageType clientMessageType;
    private ConversationPool.ClientStateType clientStateType;
    private int step;
    private boolean mvNext = false;
    private double lat = 0.0;
    private double lng = 0.0;
    private String userId, firstName, lastName, gender, postbackId;
    private Conversation conversation;
    private ArrayList<Postback> postbacksList = new ArrayList<>();
    private User user;
    private String hangoutOption;
    private State previousState;

    public User getUser() {
        return user;
    }

    public String getHangoutOption() {
        return hangoutOption;
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public void setHangoutOption(String hangoutOption) {
        this.hangoutOption = hangoutOption;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Const.ClientMessageType getClientMessageType() {
        return clientMessageType;
    }

    public void setClientMessageType(Const.ClientMessageType clientMessageType) {
        this.clientMessageType = clientMessageType;
    }

    public ConversationPool.ClientStateType getClientStateType() {
        return clientStateType;
    }

    public void setClientStateType(ConversationPool.ClientStateType clientStateType) {
        this.clientStateType = clientStateType;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public boolean isMvNext() {
        return mvNext;
    }

    public void setMvNext(boolean mvNext) {
        this.mvNext = mvNext;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getPostbackId() {
        return postbackId;
    }

    public void setPostbackId(String postbackId) {
        this.postbackId = postbackId;
    }

    public ArrayList<Postback> getPostbacksList() {
        return postbacksList;
    }

    public void addPostBack(Postback postback){
        getPostbacksList().add(postback);
    }

}

