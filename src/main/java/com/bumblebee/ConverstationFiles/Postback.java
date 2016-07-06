package com.bumblebee.ConverstationFiles;

/**
 * Created by deadcode on 03/07/2016.
 */
public class Postback {
    private int code;
    private int poolCode;
    private String title;

    public Postback(int code, int poolCode, String title) {
        this.code = code;
        this.poolCode = poolCode;
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPoolCode() {
        return poolCode;
    }

    public void setPoolCode(int poolCode) {
        this.poolCode = poolCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
