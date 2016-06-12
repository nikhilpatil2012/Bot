package com.bumblebee.ChatbotFiles;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.MultipartBody;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.concurrent.Future;

/**
 * Created by deadcode on 03/06/2016.
 */

public class ResponseBackToClient {

    public void sendMessageBackToClient(HashMap<String, JSONObject> list){

        HttpRequestWithBody multipartBody = Unirest.post("https://graph.facebook.com/v2.6/me/messages?access_token=EAAM3Ur3mIKwBAHqdqjPt3x7snweURejmCeTORnZCsqZCJq0Sg2tzuJeg8WucWWCYZCwdCoQ2qDTw5VQ8t6bZCmO7QuEBjIUfwdeaLvZBBkmQaHEh1d3JY29hZC3t6ujjNkqXgxFdN7ZB2ZCELLmle4Lde9EEi1mzRGS7IK2ZBRhH3nAZDZD");

        // Testing


        //!!!!!!!!!!!!//

        MultipartBody body = null;

        for(String key : list.keySet()){

            if(body == null){
                body = multipartBody.field(key, list.get(key));
            }
             else {
                body.field(key, list.get(key));
            }
            
            System.out.print(key);
        }

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
