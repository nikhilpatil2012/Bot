package com.bumblebee.ResponseToClient;

import com.bumblebee.ChatbotFiles.Element;
import com.bumblebee.ChatbotFiles.Query;
import com.bumblebee.ChatbotFiles.ResponseBackToClient;
import com.bumblebee.common.utils.PlaceListCallback;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.Future;

/**
 * Created by deadcode on 03/06/2016.
 */
@Service
public class FetchDataFromFourSq{

    private Query query;
    ResponseBackToClient responseBackToClient = new ResponseBackToClient();
    private ArrayList<Element> elementList = new ArrayList<>();


    public void sendQueryToFourSq(Query query, PlaceListCallback placeListCallback){

        this.query = query;

         String url = "https://api.foursquare.com/v2/venues/explore?client_id=J5Y54V5MBQ52ZAHODHCSC4MWYQ5FDXJK1WI1ZUDKFHFHJB5Z&client_secret=YDQRSDZOPJZERXDE0W1ZDDYQGPQVVTHUHRWE25K0HJ1Z1JAA&v=20130815&venuePhotos=1&ll="+query.getWhere()+"&query="+query.getWhat();

        System.out.println(url);

        Future<HttpResponse<String>> jsonResponse = Unirest.get(url)
                .asStringAsync(new Callback<String>() {
                    @Override
                    public void completed(HttpResponse<String> response) {

                     //   System.out.println(response.getBody());

                        parseResponseFromFourSq(response.getBody());

                        placeListCallback.getPlaceList(getElementList());

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

    public void parseResponseFromFourSq(String response){

        JSONObject master = new JSONObject(response);
        JSONArray jsonArray = master.getJSONObject("response").getJSONArray("groups").getJSONObject(0).getJSONArray("items");

        System.out.println("Array length "+jsonArray.length());

             for(int i=0; i<5; i++){

                 JSONObject jsonObject = jsonArray.getJSONObject(i);

                 JSONObject venue = jsonObject.getJSONObject("venue");

                 System.out.println(venue.getString("name"));

                 addPlace(venue);

                // System.out.println(jsonObject.getString("name"));
             }

    }

    public void addPlace(JSONObject jsonObject){

        // Create new instace of element
        Element element = new Element();

        if(!jsonObject.isNull("id") && jsonObject.getString("id") != null){
            element.setId(jsonObject.getString("id"));
        }

        if(!jsonObject.isNull("name") && jsonObject.getString("name") != null){
            element.setTitle(jsonObject.getString("name"));
        }

        if(!jsonObject.isNull("location") && !jsonObject.getJSONObject("location").isNull("address") && jsonObject.getJSONObject("location").getString("address") != null){
            element.setSubtitle(jsonObject.getJSONObject("location").getString("address"));
        }
         else if(!jsonObject.isNull("location") && !jsonObject.getJSONObject("location").isNull("formattedAddress")){

            JSONArray jsonArray;

            if((jsonArray = jsonObject.getJSONObject("location").getJSONArray("formattedAddress")).length() > 0){

                String address = "";

                for(int i=0; i<jsonArray.length()-1;i++){
                    address += jsonArray.getString(i);
                }
                element.setSubtitle(address);
            }

        }

        if(!jsonObject.isNull("featuredPhotos") && !jsonObject.getJSONObject("featuredPhotos").isNull("items")){

            JSONObject image = jsonObject.getJSONObject("featuredPhotos").getJSONArray("items").getJSONObject(0);

            element.setImageUrl(image.getString("prefix")+"300x300"+image.getString("suffix"));

        }

        if(!jsonObject.isNull("menu")){

              element.setDetailUrl(jsonObject.getJSONObject("menu").getString("mobileUrl"));
        }
         else if(!jsonObject.isNull("tips")){

            JSONObject tips = jsonObject.getJSONArray("tips").getJSONObject(0);

            if(!tips.isNull("canonicalUrl")){

                element.setDetailUrl(tips.getString("canonicalUrl"));
            }

        }

        elementList.add(element);
    }

    public ArrayList<Element> getElementList() {
        return elementList;
    }


}
