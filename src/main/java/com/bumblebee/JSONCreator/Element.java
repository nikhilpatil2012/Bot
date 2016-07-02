package com.bumblebee.JSONCreator;

import com.bumblebee.JSONCreator.ButtonJSONCntlr;

/**
 * Created by deadcode on 28/06/2016.
 */
public class Element {

    private String title, subtitle, imageUrl;
    private ButtonJSONCntlr buttonJSONCntlr;

    public Element(String title, String subtitle, String imageUrl, ButtonJSONCntlr buttonJSONCntlr) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageUrl = imageUrl;
        this.buttonJSONCntlr = buttonJSONCntlr;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ButtonJSONCntlr getButtonJSONCntlr() {
        return buttonJSONCntlr;
    }
}
