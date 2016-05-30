package com.bumblebee.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by deadcode on 24/04/16.
 */
public abstract class ActionResult {

  public String data;

  public ActionResult(String data){
    this.data = data;
  }


  public ResponseEntity<String> createResponse(){
    return new ResponseEntity<String>(data, HttpStatus.OK);
  }

}
