package com.bumblebee.Controller;

import com.bumblebee.common.utils.Const;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by deadcode on 26/04/16.
 */
public class ActionFactory {

    private static HashMap<String, Class<? extends Action>> actionMappings = new HashMap<String, Class<? extends Action>>();

    static {

        map(Const.ActionURIs.WEB_HOOK, WebhookAction.class);

    }

    public Action getAction(HttpServletRequest request){

        String uri = request.getRequestURI();

        if(uri.contains("?")){
            uri = uri.split("/?")[0];
        }

        Action c = getAction(uri);
        c.init(request);
        return c;
    }

    private static Action getAction(String uri){

        Class<? extends Action> controllerClass = actionMappings.get(uri);

        try {
            return controllerClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not create the action for :" + uri);
        }
    }

    private static void map(String actionUri, Class<? extends Action> actionClass) {
        actionMappings.put(actionUri, actionClass);
    }

}
