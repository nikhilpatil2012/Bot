package com.bumblebee.ConverstationFiles;

/**
 * Created by deadcode on 07/07/2016.
 */
public class PostbackButton {
    private String text;
    private int code;

    public PostbackButton(String text, int code) {
        this.text = text;
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public int getCode() {
        return code;
    }
}
