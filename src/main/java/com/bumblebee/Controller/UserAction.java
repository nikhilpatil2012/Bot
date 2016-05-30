package com.bumblebee.Controller;

import com.bumblebee.database.DAO.UserDAO;
import com.bumblebee.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by deadcode on 25/04/16.
 */

@Service
public class UserAction extends Action {


    @Override
    public ActionResult execute() {

        List<HashMap<String,String>> responseList = null;
        UserDAO userDAO = new UserDAO();

        switch (getRequestMethod()){

            // get all the users
            case "GET":{

                if(isQueryStringPresent()){

                     //responseList = userDAO.getUserById(getUrlParameter(new String[]{""}));
                }
                  else {

                    responseList = userDAO.getAllUsers();
                }

            }break;

            // add user to the database
            case "POST":{

                User user = getUserFromJson();
                responseList = userDAO.addNewUser(user);

            }break;
        }

        return createShowPageResult("");
    }

    // Jackson Object Mapper
    public User getUserFromJson(){

        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;

        try {
            user = objectMapper.readValue(getRequestBody(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
