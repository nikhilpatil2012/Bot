package com.bumblebee.JSONCreator;

import com.bumblebee.common.utils.Const;

/**
 * Created by deadcode on 28/06/2016.
 */
public class Button {

    private Const.ButtonType buttonType;
    private String webUrl;
    private String title;
    private String payloadCode;

    public Button(Const.ButtonType buttonType, String title, String payloadCode, String webUrl) {
        this.buttonType = buttonType;
        this.webUrl = webUrl;
        this.title = title;
        this.payloadCode = payloadCode;
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

    public String getPayloadCode() {
        return payloadCode;
    }
}
