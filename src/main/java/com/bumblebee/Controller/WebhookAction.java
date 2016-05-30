package com.bumblebee.Controller;

import java.io.BufferedReader;
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


                StringBuffer jb = new StringBuffer();
                String line = null;
                try {
                    BufferedReader reader = request.getReader();
                    while ((line = reader.readLine()) != null)
                        jb.append(line);
                } catch (Exception e) { /*report an error*/ }

                response = jb.toString();

                System.err.println(response);


            }break;
        }

        return createShowPageResult(response);
    }
}
