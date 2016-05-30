/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bumblebee.database.DAO;

import com.bumblebee.Helper.ResponseCodes;
import com.bumblebee.database.DBManager;
import com.bumblebee.model.Selfie;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

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

// Document keys :-  lat, lng, add, aid_type, postal_code, description, file

@Service
// CRUD Operation's Interface for adding new Selfie 
public class SelfieDAO {
    
    private DBManager dBManager;

    public SelfieDAO(){
        dBManager = new DBManager();        
    }
    
    // Insert selfie safely into the db and return the write status
    public int addNewSelfie(Selfie selfie){

        try {
            
            Document document = new Document();

             // Store Selfie image and obtain ObjectId for refrence
               if(!selfie.getFile().isEmpty()){
                    ObjectId objectId = dBManager.insertFile(selfie.getFile().getBytes());
                    document.append("file", objectId.toString());
               }

            document.append("lat", selfie.getLat())
                    .append("lng", selfie.getLng())
                    .append("add", selfie.getAdd())
                    .append("aid_type", selfie.getAidtype())
                    .append("postal_code", selfie.getPostalCode())
                    .append("description", selfie.getDescription());


              int insertStatus = dBManager.insertDocument(DBManager.CollectionList.Selfies, document);

             insertStatus = (insertStatus > 0) ? ResponseCodes.SELFIE_INSERT_SUCCESS : ResponseCodes.SELFIE_INSERT_FAILED;

            return insertStatus;
              
        } catch (IOException ex) {
            Logger.getLogger(SelfieDAO.class.getName()).log(Level.SEVERE, null, ex);
            return ResponseCodes.SELFIE_INSERT_FAILED;
        }
    }

    public List<HashMap<String,String>> getAllSelfies(){

        List<HashMap<String,String>> selfies = documentToHashMapConv(dBManager.getAllDocuments(DBManager.CollectionList.Selfies));

        return selfies;
    }

    public List<HashMap<String,String>> getAllSelfiesByPostalCode(String postalCode){

        List<HashMap<String,String>> selfies = documentToHashMapConv(dBManager.getAllDocuments(DBManager.CollectionList.Selfies, new Document("postal_code", postalCode)));

        return selfies;
    }

    public  List<HashMap<String,String>> documentToHashMapConv(FindIterable<Document> docList){

        List<HashMap<String,String>> selfies = new ArrayList<>();

        docList.forEach(new Block<Document>() {

                            @Override
                            public void apply(final Document document) {

                                HashMap<String,String> s = new HashMap<>();

                                s.put("lat", document.getString("lat"));
                                s.put("lng",  document.getString("lng"));
                                s.put("add", document.getString("add"));
                                s.put("aid_type", document.getString("aid_type"));
                                s.put("postal_code", document.getString("postal_code"));
                                s.put("file", document.getString("file"));
                                s.put("description", document.getString("description"));

                                selfies.add(s);
                            }
                        }
        );

        return selfies;
    }

}