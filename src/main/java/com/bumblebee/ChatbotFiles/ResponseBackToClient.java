package com.bumblebee.ChatbotFiles;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Created by deadcode on 03/06/2016.
 */
public class ResponseBackToClient {

    public void sendMessageBackToClient(String userId, String userMessage){

        JSONObject recipient = new JSONObject();
        recipient.put("id", userId);

        JSONObject message = new JSONObject();
        message.put("text", userMessage);


        Future<HttpResponse<JsonNode>> postClient = Unirest.post("https://graph.facebook.com/v2.6/me/messages?access_token=EAAM3Ur3mIKwBAMTEOP6hlcBy19UhTN7SZClKfiAOgpZAwZBr8LOiDBWepOMaR4jebtSZBUTEuAMMC00gxY79FH9RTdSmvPTbra5HokqELMb1a9cyRhnSMZCxWbpDj5ox98PRPHWVn7deznikBITZBkLHUFlIZB7pPLLSRZCK8AkZBMQZDZD")
                .field("recipient", recipient)
                .field("message", message)
                .asJsonAsync(new Callback<JsonNode>() {
                    @Override
                    public void completed(HttpResponse<JsonNode> httpResponse) {
                        System.out.println(httpResponse.getBody());
                        //closeConnection();
                    }

                    @Override
                    public void failed(UnirestException e) {

                    }

                    @Override
                    public void cancelled() {

                    }
                });

    }

    public void closeConnection(){
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
