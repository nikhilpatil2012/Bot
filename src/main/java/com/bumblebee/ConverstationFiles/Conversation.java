package com.bumblebee.ConverstationFiles;

import com.bumblebee.common.utils.Const;

/**
 * Created by deadcode on 27/06/2016.
 */
public class Conversation {

    private Const.ClientMessageType type;
    private int code;
    private double lat = 0.0;
    private double lng = 0.0;
    private boolean mvNext = false;


    public Conversation(Const.ClientMessageType type, int code, boolean mvNext){
        this.type = type;
        this.code = code;
        this.mvNext = mvNext;
    }

    public Const.ClientMessageType getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public boolean isMvNext() {
        return mvNext;
    }

    public void setMvNext(boolean mvNext) {
        this.mvNext = mvNext;
    }
}
