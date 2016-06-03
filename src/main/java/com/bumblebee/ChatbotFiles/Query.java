package com.bumblebee.ChatbotFiles;

/**
 * Created by deadcode on 02/06/2016.
 */
public class Query {

    private String what, where, statusCode, senderId;

    public String getWhere() {
        return where;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
