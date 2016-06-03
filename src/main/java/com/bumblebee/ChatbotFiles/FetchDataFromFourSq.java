package com.bumblebee.ChatbotFiles;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.Future;

/**
 * Created by deadcode on 03/06/2016.
 */
@Service
public class FetchDataFromFourSq {

    private Query query;

    public void sendQueryToFourSq(Query query){

        this.query = query;

        String url = "https://api.foursquare.com/v2/venues/search?client_id=J5Y54V5MBQ52ZAHODHCSC4MWYQ5FDXJK1WI1ZUDKFHFHJB5Z&client_secret=YDQRSDZOPJZERXDE0W1ZDDYQGPQVVTHUHRWE25K0HJ1Z1JAA&v=20130815&near="+query.getWhere()+"&query="+query.getWhat();

        Future<HttpResponse<String>> jsonResponse = Unirest.get(url)
                .asStringAsync(new Callback<String>() {
                    @Override
                    public void completed(HttpResponse<String> response) {

                        System.out.println(response.getBody());

                        // Parse the Foursquare response
                        parseResponseFromFourSq();
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

    public void parseResponseFromFourSq(){

        ResponseBackToClient responseBackToClient = new ResponseBackToClient();
        responseBackToClient.sendMessageBackToClient(query.getSenderId(), "What is "+query.getWhat()+" and where is "+query.getWhere());

       /*   // Close the connection
        try {
            Unirest.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
