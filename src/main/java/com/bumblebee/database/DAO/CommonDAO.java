package com.bumblebee.database.DAO;

import com.bumblebee.Helper.ResponseCodes;
import com.bumblebee.database.DBManager;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by deadcode on 26/02/16.
 */

@Service
public class CommonDAO {


    private DBManager dBManager;

    public CommonDAO(){
        dBManager = new DBManager();
    }

    public byte[] getUserFileById(String id){

        ObjectId objectId = new ObjectId(id);

        byte[] fileBytes = dBManager.getFileFromId(objectId);

        return fileBytes;
    }


    public byte[] getBotImageFromName(String image_name){

        FindIterable<Document> iterable = dBManager.getAllDocuments(DBManager.CollectionList.AppImages, new Document("name", image_name));

        ArrayList<Document> list = new ArrayList<>();

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                System.out.println(document);

                list.add(document);

            }
        });

        ObjectId objectId = new ObjectId(list.get(0).getString(image_name));

        byte[] fileBytes = dBManager.getFileFromId(objectId);

        return fileBytes;
    }


    public int storeBotImages(String image_name, byte[] data){

        Document document = new Document();

        // Store User profile pic and obtain ObjectId for reference
        if(data != null){
            ObjectId objectId = dBManager.insertFile(data);
            document.append("name", image_name);
            document.append(image_name, objectId.toString());
        }

        int insert_status = dBManager.insertDocument(DBManager.CollectionList.AppImages, document);

        insert_status = (insert_status > 0) ? ResponseCodes.SELFIE_INSERT_SUCCESS : ResponseCodes.SELFIE_INSERT_FAILED;

        return insert_status;
    }


    public int reportFileById(String id){

        ObjectId objectId = new ObjectId(id);

        int insertStatus = dBManager.insertDocument(DBManager.CollectionList.ImagesUnderReview, new Document().append("image", objectId));

        insertStatus = (insertStatus > 0) ? ResponseCodes.SELFIE_INSERT_SUCCESS : ResponseCodes.SELFIE_INSERT_FAILED;

        return insertStatus;
    }

}
