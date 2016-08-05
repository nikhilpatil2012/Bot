package com.bumblebee.ConverstationFiles;

import com.bumblebee.common.utils.UserProfileCallback;
import com.bumblebee.database.DAO.UserDAO;
import com.bumblebee.model.User;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

/**
 * Created by deadcode on 05/07/2016.
 */
public class GetUserProfile {

    private ConversationCntrl conversationCntrl;

    public GetUserProfile(ConversationCntrl conversationCntrl) {
        this.conversationCntrl = conversationCntrl;
    }

    public void getUserProfile(UserProfileCallback userProfileCallback){

        String url = "https://graph.facebook.com/v2.6/"+conversationCntrl.getUserId()+"?access_token=EAAM3Ur3mIKwBAHqdqjPt3x7snweURejmCeTORnZCsqZCJq0Sg2tzuJeg8WucWWCYZCwdCoQ2qDTw5VQ8t6bZCmO7QuEBjIUfwdeaLvZBBkmQaHEh1d3JY29hZC3t6ujjNkqXgxFdN7ZB2ZCELLmle4Lde9EEi1mzRGS7IK2ZBRhH3nAZDZD";

        Unirest.get(url).asJsonAsync(new Callback<JsonNode>() {

            @Override
            public void completed(HttpResponse<JsonNode> httpResponse) {


                JSONObject jsonObject = httpResponse.getBody().getObject();

                System.out.println(jsonObject);

                User user = getUserFromJSON(jsonObject);

                userProfileCallback.getUserCallback(user);
            }

            @Override
            public void failed(UnirestException e) {

            }

            @Override
            public void cancelled() {

            }
        });

    }

    public User getUserFromJSON(JSONObject jsonObject){

        if(jsonObject == null){
            return null;
        }

        User user = new User();

        user.setUserFbId(conversationCntrl.getUserId());

        if(!jsonObject.isNull("first_name")){
            user.setFirstName(jsonObject.getString("first_name"));
        }

        if(!jsonObject.isNull("last_name")){
            user.setLastName(jsonObject.getString("last_name"));
        }

        if(!jsonObject.isNull("gender")){
            user.setGender(jsonObject.getString("gender"));
        }

        return user;
    }


}
