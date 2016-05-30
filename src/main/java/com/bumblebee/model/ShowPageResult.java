package com.bumblebee.model;

import com.bumblebee.Controller.ActionResult;

import java.util.HashMap;
import java.util.List;

/**
 * Created by deadcode on 25/04/16.
 */
public class ShowPageResult extends ActionResult {

    public ShowPageResult(List<HashMap<String,String>> data){
        super(data);
    }

}
