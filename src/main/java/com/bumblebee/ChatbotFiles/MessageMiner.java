package com.bumblebee.ChatbotFiles;

import com.bumblebee.common.utils.WatsonCallback;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Created by deadcode on 02/06/2016.
 */

@Service
public class MessageMiner {


    private String what,where;
    private WatsonCallback watsonCallback;
    private ChatParser chatParser;

    public void sendMessageToWatson(ChatParser chatParser, WatsonCallback watsonCallback){

        this.chatParser = chatParser;

        String message = chatParser.getQueryMessage();
        String url = "http://gateway-a.watsonplatform.net/calls/text/TextGetCombinedData?apikey=3bd40a92a64d5750303bd6a159a20ff576f48c13&text="+message+"&extract=entity%2Ctaxonomy&outputMode=json";

        Future<HttpResponse<String>> jsonResponse = Unirest.get(url)
                .asStringAsync(new Callback<String>() {
                    @Override
                    public void completed(HttpResponse<String> response) {

                        // Create the query from the watson response
                        parseResponseFromWatson(response.getBody());

                        // Create the query
                        Query query = createQuery();

                        // Return the query via callback
                        watsonCallback.GetQuery(query);
                    }

                    @Override
                    public void failed(UnirestException arg0) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void cancelled() {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });

    }

    private void parseResponseFromWatson(String response){

        JSONObject jsonObject = new JSONObject(response);
        JSONArray entities;

        if(!jsonObject.isNull("entities") && (entities = jsonObject.getJSONArray("entities")).length() > 0){

            for(int i=0; i<entities.length(); i++){

                if(!entities.getJSONObject(i).isNull("type")){

                    if(entities.getJSONObject(i).getString("type").equals("City")){

                        where = entities.getJSONObject(i).getString("text");

                        System.out.println("Where is "+where);
                    }
                }
            }
        }

        if(!jsonObject.isNull("taxonomy") && (entities = jsonObject.getJSONArray("taxonomy")).length() > 0){

            if(!entities.getJSONObject(0).isNull("label")){

                what = entities.getJSONObject(0).getString("label");

                if(what.contains("/")){
                    String temp[] = what.split("/");
                    what = temp[temp.length-1].replace(" and ","+");
                }
            }
        }
    }

    public String getWhat() {
        return what;
    }

    public String getWhere() {
        return where;
    }

    public Query createQuery(){

        Query query = new Query();

        // Set the sender id
        query.setSenderId(chatParser.getSenderId());

        // Set what and where
        query.setWhat(what);
        query.setWhere(where);

        // Check the query
        //query = QueryChecker.updateQuery(query);

        QueryChecker.updateQueryStatus(query);

        return query;
    }
}
