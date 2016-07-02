package com.bumblebee.JSONCreator;

import com.bumblebee.common.utils.Const;

/**
 * Created by deadcode on 28/06/2016.
 */
public class Button {

    private Const.ButtonType buttonType;
    private String webUrl;
    private String title;
    private int payloadCode;

    public Button(Const.ButtonType buttonType, String title, int payloadCode) {
        this.buttonType = buttonType;
        this.title = title;
        this.payloadCode = payloadCode;
    }

    public Button(Const.ButtonType buttonType, String webUrl, String title) {
        this.buttonType = buttonType;
        this.webUrl = webUrl;
        this.title = title;
    }

    public Const.ButtonType getButtonType() {
        return buttonType;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getPayloadCode() {
        return payloadCode;
    }
}
