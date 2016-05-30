package com.bumblebee.Controller;


import com.bumblebee.model.Response;

import java.util.HashMap;
import java.util.List;

/**
 * Created by deadcode on 24/04/16.
 */
public abstract class ActionResult {

  public List<HashMap<String,String>> data;

  public ActionResult(List<HashMap<String,String>> data){
    this.data = data;
  }


  public Response createResponse(){
    return new Response(data);
  }

}
