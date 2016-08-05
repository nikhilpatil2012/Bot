/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bumblebee.database;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSUploadStream;
import com.mongodb.client.model.IndexOptions;
import com.sun.javadoc.Doc;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;

/**
 *
 * @author deadcode
 */
public class DBManager {
    
    private MongoDatabase databaseConnection;

    public DBManager() {
        openConnection();
        //openTestConnection();
    }
   
    // All collections list in MongoDB
    public enum CollectionList  {

         Users, Selfies, Images, ImagesUnderReview, AppImages
    }

    // Open connection to the remote database
    private void openConnection(){

        MongoCredential credential = MongoCredential.createCredential("nikhil.patil.mongo", "bobo", "99111".toCharArray());
        MongoClient client = new MongoClient(new ServerAddress("ds145315.mlab.com", 45315), Arrays.asList(credential));
        client.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        databaseConnection = client.getDatabase("bobo");
    }

      private void openTestConnection(){

        MongoClient client = new MongoClient("localhost");
        client.setWriteConcern(WriteConcern.ACKNOWLEDGED);
        databaseConnection = client.getDatabase("bobo");
    }


     // Insert specified document into the mentioned collection
    /*
      Return -1 --> WriteException,
                   0 --> If Record Already Exist,
                   n(records inserted) --> If successfully inserted
   */
      public int insertDocument(CollectionList collectionName, Document document){


          try
        {
            MongoCollection<Document> collection = getCollectionWithIndex(collectionName);

            long count = collection.count();
            databaseConnection.getCollection(collectionName.name()).insertOne(document);
            return (int) (collection.count() - count);
        }
          catch(MongoWriteException writeException) {
            return -1;
        }
    }

    private MongoCollection<Document>  getCollectionWithIndex(CollectionList collectionName){

        MongoCollection<Document> collection = databaseConnection.getCollection(collectionName.name());

        if(collectionName.compareTo(CollectionList.Selfies) == 0){

            //collection.createIndex(new BasicDBObject("lat", 1).append("lng", 1), new IndexOptions().unique(true));
        }
         else if(collectionName.compareTo(CollectionList.Users) == 0){
            collection.createIndex(new BasicDBObject("email", 1), new IndexOptions().unique(true));
        }

        return collection;
    }

    // Insert file into the mentioned collection
    public ObjectId insertFile(byte[] bytes){

        GridFSBucket gridFSBucket = GridFSBuckets.create(databaseConnection);
        GridFSUploadStream uploadStream = gridFSBucket.openUploadStream("");
        uploadStream.write(bytes);
        uploadStream.close();
        return uploadStream.getFileId();
    }

    // Get all the documents by collection name
    public FindIterable<Document> getAllDocuments(CollectionList collectionName){

        MongoCollection<Document> collection = databaseConnection.getCollection(collectionName.name());
        return collection.find();

    }

    //Get a file from id
    public byte[] getFileFromId(ObjectId objectId){

        GridFSBucket gridFSBucket = GridFSBuckets.create(databaseConnection);
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(objectId);
        int fileLength = (int) downloadStream.getGridFSFile().getLength();
        byte[] fileBytes = new byte[fileLength];
        downloadStream.read(fileBytes);
        downloadStream.close();

        return fileBytes;
    }


    // Get all the documents by given document
    public FindIterable<Document> getAllDocuments(CollectionList collectionName, Document document){

        MongoCollection<Document> collection = databaseConnection.getCollection(collectionName.name());
        return collection.find(document);
    }

}
