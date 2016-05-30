package com.bumblebee.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by deadcode on 24/04/16.
 */
public class Response {

    private List<HashMap<String,String>> data;

    public Response(List<HashMap<String,String>> data){
        this.data = data;
    }

    public List<HashMap<String, String>> getData() {
        return data;
    }

}
