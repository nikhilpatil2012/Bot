package com.bumblebee.ConverstationFiles;

import com.bumblebee.common.utils.Const;

/**
 * Created by deadcode on 26/06/2016.
 */
public class ConversationCntrl {

    private Const.ClientMessageType clientMessageType;
    private Const.ClientStateType clientStateType;
    private int step;
    private boolean mvNext = false;
    private double lat = 0.0;
    private double lng = 0.0;
    private String userId, firstName, lastName, gender, postbackId;
    private Conversation conversation;


    public Const.ClientMessageType getClientMessageType() {
        return clientMessageType;
    }

    public void setClientMessageType(Const.ClientMessageType clientMessageType) {
        this.clientMessageType = clientMessageType;
    }

    public Const.ClientStateType getClientStateType() {
        return clientStateType;
    }

    public void setClientStateType(Const.ClientStateType clientStateType) {
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
}

