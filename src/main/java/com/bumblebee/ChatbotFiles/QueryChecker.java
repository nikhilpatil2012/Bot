package com.bumblebee.ChatbotFiles;

import com.bumblebee.common.utils.StatusCodes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by deadcode on 02/06/2016.
 */
public class QueryChecker {

    private static HashMap<String,Query> tempMemory = new HashMap<>();

    // Check and Update the query
    public static Query updateQuery(Query query){

        // One of What or Where is missing
        if(query.getWhat() == null || query.getWhere() == null){

            if(tempMemory.containsKey(query.getSenderId())) {
                // Existing Query

                // What is missing in the query, update it from previous query
                if(query.getWhat() == null && tempMemory.get(query.getSenderId()).getWhat() != null){
                    query.setWhat(tempMemory.get(query.getSenderId()).getWhat());
                }

                // Where is missing in the query, update it from previous query
                if(query.getWhere() == null && tempMemory.get(query.getSenderId()).getWhere() != null){
                    query.setWhere(tempMemory.get(query.getSenderId()).getWhere());
                }
            }
            else {
                // Non-Existing in-complete query, store it for future completion
                tempMemory.put(query.getSenderId(), query);
            }
        }

        // Update the query status
        updateQueryStatus(query);

         return query;
    }

    // Update the query status
    private static void updateQueryStatus(Query query){

         if(query.getWhat() != null && query.getWhere() != null){
             query.setStatusCode(StatusCodes.QUERY_OK);

             if(tempMemory.containsKey(query.getSenderId())) {
                 tempMemory.remove(query.getSenderId());
             }
         }
          else if(query.getWhat() != null && query.getWhere() == null){
             query.setStatusCode(StatusCodes.WHERE_MISSING);
         }
          else if(query.getWhat() == null && query.getWhere() != null){
             query.setStatusCode(StatusCodes.WHAT_MISSING);
         }
          else {
             query.setStatusCode(StatusCodes.QUERY_NOT_OK);
         }
    }

}
