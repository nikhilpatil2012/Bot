package com.bumblebee.Controller;

import com.bumblebee.model.ShowPageResult;
import com.bumblebee.model.User;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.HashMap;

/**
 * Created by deadcode on 23/04/16.
 */

public abstract class Action  {

    protected HttpServletRequest request;
    private String requestMethod;
    private String requestBody;
    private boolean isQueryStringPresent = false;


    private static HashMap<String, Class<?>> map = new HashMap<String, Class<?>>();

    static {
        map.put("User_Model", User.class);

    }

    public ShowPageResult createShowPageResult(String data){

        return new ShowPageResult(data);
    }

    public void init(HttpServletRequest request){
     this.request = request;
     initialiseAttributes(request);
    }

    public String getRequestMethod(){
        return requestMethod;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public boolean isQueryStringPresent(){
        return isQueryStringPresent;
    }

    protected void initialiseAttributes(HttpServletRequest req) {
        requestMethod = req.getMethod();
    }



    // Retrieve the parameters from the url's query string
    public HashMap<String, String> getUrlParameter(String[] params){

        HashMap<String, String> paramMap = new HashMap<>();

        for(String p : params){

            if(request.getParameter(p) != null){
                paramMap.put(p,request.getParameter(p));
            }
        }
        return paramMap;
    }


    public abstract ActionResult execute();
}
