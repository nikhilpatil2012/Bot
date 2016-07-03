package com.bumblebee.ResponseToClient;

import com.bumblebee.JSONCreator.MasterJSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by deadcode on 29/06/2016.
 */
public class ResponseActionResult {

    private MasterJSON masterJSON;

    public ResponseActionResult(MasterJSON masterJSON){
        this.masterJSON = masterJSON;
    }

    public void sendMessage(){

        HttpRequestWithBody multipartBody = Unirest.post("https://graph.facebook.com/v2.6/me/messages?access_token=EAAM3Ur3mIKwBAHqdqjPt3x7snweURejmCeTORnZCsqZCJq0Sg2tzuJeg8WucWWCYZCwdCoQ2qDTw5VQ8t6bZCmO7QuEBjIUfwdeaLvZBBkmQaHEh1d3JY29hZC3t6ujjNkqXgxFdN7ZB2ZCELLmle4Lde9EEi1mzRGS7IK2ZBRhH3nAZDZD");

        multipartBody.field("recipient", masterJSON.getRecipientJSON())
                     .field("message", masterJSON.getMessageJSON());

        System.out.println(masterJSON.getMessageJSON());


        Future<HttpResponse<JsonNode>> postClient = multipartBody.asJsonAsync(new Callback<JsonNode>() {

            @Override
            public void completed(HttpResponse<JsonNode> httpResponse) {
                System.out.println(httpResponse.getBody());
            }

            @Override
            public void failed(UnirestException e) {

            }

            @Override
            public void cancelled() {

            }
        });

    }

}
