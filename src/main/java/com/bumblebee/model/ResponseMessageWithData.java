/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bumblebee.model;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author deadcode
 */

@Service
public class ResponseMessageWithData {

     private HashMap<String,String> responseMessage = new HashMap<>();
     private List<HashMap<String,String>> data;

    public HashMap<String, String> getResponseMessage() {
        return responseMessage;
    }

    public List<HashMap<String, String>> getData() {
        return data;
    }

    public void setData(List<HashMap<String, String>> data) {
        this.data = data;
    }

}
