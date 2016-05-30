package com.bumblebee.Controller;

import java.util.HashMap;

/**
 * Created by deadcode on 29/05/2016.
 */
public class WebhookAction extends Action {


    @Override
    public ActionResult execute() {

        String response = "";

        switch (getRequestMethod()){

            case "GET":{

                HashMap<String, String> paramMap;

                if(!(paramMap = getUrlParameter(new String[]{"hub.verify_token","hub.challenge"})).isEmpty()){

                    if(paramMap.get("hub.verify_token").equals("Charlie123")){

                        response = paramMap.get("hub.challenge");
                    }

                }

            }break;

            case "POST":{



            }break;
        }

        return createShowPageResult(response);
    }
}
