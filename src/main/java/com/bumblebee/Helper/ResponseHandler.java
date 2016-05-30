package com.bumblebee.Helper;/*
package com.deadcode.neo4j_testing.Helper;

import com.deadcode.neo4j_testing.model.ResponseMessageWithData;
import com.deadcode.neo4j_testing.model.Selfie;
import com.deadcode.neo4j_testing.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

*/
/**
 * Created by deadcode on 25/02/16.
 *//*



@Service
public class ResponseHandler {


    // Handler for generating signup response
    public HashMap<String,String> generateSignUpResponse(int insertStatus){

        HashMap<String,String> response = new HashMap<>();

        if(insertStatus != -1){ // Selfie successfully added

            if(insertStatus > 0){
                response.put("status", String.valueOf(ResponseCodes.USER_INSERT_SUCCESS));

            }
              else {
                response.put("status", String.valueOf(ResponseCodes.USER_INSERT_FAILED));
            }

        }
        else { // User already exist
            response.put("status", String.valueOf(ResponseCodes.USER_ALREADY_EXIST));
        }

        return response;
    }

    // Handler for generating selfie upload response
    public HashMap<String,String> generateSelfieUploadResponse(int insertStatus, HashMap<String,String> previousResponse){

        if(previousResponse != null){

            if(insertStatus != -1){ // Selfie successfully added

                if(insertStatus > 0){
                    previousResponse.put("status", String.valueOf(ResponseCodes.SELFIE_INSERT_SUCCESS));
                }
                else {
                    previousResponse.put("status", String.valueOf(ResponseCodes.SELFIE_INSERT_FAILED));
                }

            }
            else {
                previousResponse.put("status", String.valueOf(ResponseCodes.SELFIE_INSERT_FAILED));
            }
        }

        return previousResponse;
    }

    //Handler for generating check access token response
    public HashMap<String,String> generateTokenResponse(boolean value){

        HashMap<String,String> response = new HashMap<>();

        response.put("token_status", value ? String.valueOf(ResponseCodes.ACCESS_TOKEN_ACCEPTED) : String.valueOf(ResponseCodes.ACCESS_TOKEN_REJECTED));

        return response;
    }


    //Hander for generating get all users response
    public ResponseMessageWithData generateGetAllUsersResponse(List<HashMap<String,String>> userList){

        HashMap<String,String> responseStatus = new HashMap<>();
        ResponseMessageWithData responseData = new ResponseMessageWithData();

        if(userList.size() > 0){
            responseStatus.put("status",String.valueOf(ResponseCodes.GET_USER_LIST_SUCCESS));
            responseData.setData(userList);
        }
         else {
            responseStatus.put("status",String.valueOf(ResponseCodes.GET_USER_LIST_FAILED));
         }

        responseData.setResponseStatus(responseStatus);

        return responseData;
    }

    //Hander for generating get all selfies response
    public ResponseMessageWithData generateGetAllSelfiesResponse(List<HashMap<String,String>> selfieList){

        HashMap<String,String> responseStatus = new HashMap<>();
        ResponseMessageWithData responseData = new ResponseMessageWithData();

        if(selfieList.size() > 0){
            responseStatus.put("status",String.valueOf(ResponseCodes.GET_USER_LIST_SUCCESS));
            responseData.setData(selfieList);
        }
        else {
            responseStatus.put("status",String.valueOf(ResponseCodes.GET_USER_LIST_FAILED));
        }

        return responseData;
    }

    // Handler for generating images as response
    public HttpHeaders generateImageAsReponse(byte[] fileBytes){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); //or what ever type it is
        headers.setContentLength(fileBytes.length);

        return headers;
    }
}
*/
