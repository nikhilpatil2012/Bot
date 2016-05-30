/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bumblebee.model;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 *
 * @author deadcode
 */

@Service
public class ResponseMessage {
    
    private HashMap<String,String> responseMessage = new HashMap<>();

    public ResponseMessage(){
    }

    public ResponseMessage( HashMap<String,String> responseMessage) {
        this.responseMessage = responseMessage;
    }

    public HashMap<String, String> getResponseMessage() {
        return responseMessage;
    }
}
