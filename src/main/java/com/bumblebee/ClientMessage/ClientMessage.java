package com.bumblebee.ClientMessage;

import com.bumblebee.common.utils.Const;

/**
 * Created by deadcode on 25/06/2016.
 */
public class ClientMessage {

    private String pageID, timestamp, senderId, recipientId,
    messageText, attachmentType, attachmentUrl, postBackId;

    private long watermark;
    private double lat=0.0, lng=0.0;

    private Const.ClientMessageType messageType;

    public String getPageID() {
        return pageID;
    }

    public void setPageID(String pageID) {
        this.pageID = pageID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }


    public Const.ClientMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(Const.ClientMessageType messageType) {
        this.messageType = messageType;
    }

    public long getWatermark() {
        return watermark;
    }

    public void setWatermark(long watermark) {
        this.watermark = watermark;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getPostBackId() {
        return postBackId;
    }

    public void setPostBackId(String postBackId) {
        this.postBackId = postBackId;
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
}
