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
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public List<HashMap<String,String>> addNewUser(User user){

            Document document = new Document();

             // Store User profile pic and obtain ObjectId for reference

          List<HashMap<String,String>> response = new ArrayList<>();

            document.append("name", user.getName())
                    .append("email", user.getEmail())
                    .append("gender", user.getGender());

             System.out.println(document.toJson());

              int insertStatus = dBManager.insertDocument(DBManager.CollectionList.Users, document);

              System.out.println("Insert Status "+insertStatus);

              insertStatus = (insertStatus > 0) ? ResponseCodes.USER_INSERT_SUCCESS : ResponseCodes.USER_ALREADY_EXIST;

              HashMap<String,String> map = new HashMap<>();
              map.put("INSERT_STATUS", String.valueOf(insertStatus));

             response.add(map);

        return response;
    }

    public List<HashMap<String,String>> getUserById(String id){


        List<HashMap<String,String>> users = new ArrayList<>();

        dBManager.getDocumentById(DBManager.CollectionList.Users, id).forEach(new Block<Document>() {

          @Override
          public void apply(final Document document) {

              HashMap<String,String> u = new HashMap<>();

              u.put("name", document.getString("name"));
              u.put("email", document.getString("email"));
              u.put("gender", document.getString("gender"));

              users.add(u);
          }
         }
        );

        return users;
    }

    public List<HashMap<String,String>> getAllUsers(){

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> users = new ArrayList<>();

        dBManager.getAllDocuments(DBManager.CollectionList.Users).forEach(new Block<Document>() {

                @Override
                public void apply(final Document document) {

                        HashMap<String,String> u = new HashMap<>();

                        u.put("name", document.getString("name"));
                        u.put("email", document.getString("email"));
                        u.put("gender", document.getString("gender"));

                        users.add(u);
                }
            }
        );

        return users;
    }

}
