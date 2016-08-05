/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bumblebee.database.DAO;

import com.bumblebee.Helper.ResponseCodes;
import com.bumblebee.database.DBManager;
import com.bumblebee.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author deadcode
 */

// Document keys :-  name, email, area, country, file

@Service
// CRUD Operation's Interface for adding new Selfie 
public class UserDAO {
    
    private DBManager dBManager;
    
    public UserDAO(){
        dBManager = new DBManager();        
    }
    
    // Insert selfie safely into the db and return the write status
    public int addNewUser(User user){
        
            Document document = new Document();

            document.append("_id", user.getUserFbId())
                    .append("firstname", user.getFirstName())
                    .append("lastname", user.getLastName())
                    .append("gender", user.getGender());

              int insertStatus = dBManager.insertDocument(DBManager.CollectionList.Users, document);

              insertStatus = (insertStatus > 0) ? ResponseCodes.USER_INSERT_SUCCESS : ResponseCodes.USER_ALREADY_EXIST;

               return insertStatus;
    }

    public List<HashMap<String,String>> getAllUsers(){

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> users = new ArrayList<>();

        dBManager.getAllDocuments(DBManager.CollectionList.Users).forEach(new Block<Document>() {

                @Override
                public void apply(final Document document) {

                        HashMap<String,String> u = new HashMap<>();

                        u.put("_id", document.getString("_id"));
                        u.put("firstName", document.getString("firstName"));
                        u.put("lastName", document.getString("lastName"));
                        u.put("gender", document.getString("gender"));

                        users.add(u);
                }
            }
        );

        return users;
    }

    public User getUserFromId(String id){

         User user = new User();

        dBManager.getAllDocuments(DBManager.CollectionList.Users, new Document("_id", id)).forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {

                System.out.println(document);
                System.out.println(document.getString("firstname"));

                user.setUserFbId(document.getString("_id"));
                user.setFirstName(document.getString("firstname"));
                user.setLastName(document.getString("lastame"));
                user.setGender(document.getString("gender"));

            }
        });

        return user;
    }

    public void test(){

        FindIterable<Document> iterable = dBManager.getAllDocuments(DBManager.CollectionList.Users, new Document("_id", "931411386981115"));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                 System.out.println(document);

            }
        });

    }
}
