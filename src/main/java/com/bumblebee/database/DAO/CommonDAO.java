package com.bumblebee.database.DAO;

import com.bumblebee.Helper.ResponseCodes;
import com.bumblebee.database.DBManager;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

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

    public int reportFileById(String id){

        ObjectId objectId = new ObjectId(id);

        int insertStatus = dBManager.insertDocument(DBManager.CollectionList.ImagesUnderReview, new Document().append("image", objectId));

        insertStatus = (insertStatus > 0) ? ResponseCodes.SELFIE_INSERT_SUCCESS : ResponseCodes.SELFIE_INSERT_FAILED;

        return insertStatus;
    }

}
