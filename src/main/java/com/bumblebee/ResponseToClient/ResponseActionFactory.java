package com.bumblebee.ResponseToClient;

import com.bumblebee.ResponseToClient.HangoutOptions;
import com.bumblebee.ResponseToClient.ResponseAction;
import com.bumblebee.common.utils.Const;
import com.bumblebee.common.utils.ConversationCodes;
import com.bumblebee.common.utils.ConversationPool;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by deadcode on 26/04/16.
 */
public class ResponseActionFactory {

    private static HashMap<Integer, Class<? extends ResponseAction>> responseActionMappings = new HashMap<>();

    static {

        map(ConversationCodes.SHOW_HANGOUT_OPTIONS, HangoutOptions.class);

    }

    public ResponseAction getAction(int code){

        Class<? extends ResponseAction> controllerClass;

           if(ConversationPool.codeList.containsKey(code)){
               controllerClass = HangoutOptions.class;
           }
             else {
               controllerClass = responseActionMappings.get(code);
           }

        try {
            return controllerClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not create the response action for :" + code);
        }
    }


    private static void map(Integer code, Class<? extends ResponseAction> responseActionClass) {
        responseActionMappings.put(code, responseActionClass);
    }

}
