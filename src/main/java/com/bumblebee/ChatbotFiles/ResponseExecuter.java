package com.bumblebee.ChatbotFiles;

import com.bumblebee.common.utils.StatusCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by deadcode on 02/06/2016.
 */
@Service
public class ResponseExecuter {

    private FetchDataFromFourSq fetchDataFromFourSq = new FetchDataFromFourSq();

    private ResponseBackToClient responseBackToClient = new ResponseBackToClient();

    public void execute(Query query){

        String message = "Everything is ok";
/*
        switch (query.getStatusCode()){

            case StatusCodes.QUERY_OK:{

                // Call Four Square
                //fetchDataFromFourSq.sendQueryToFourSq(query);
                message = "Query is ok";

            }break;

            case StatusCodes.WHAT_MISSING:{

                System.out.println("What is missing");
                message = "What is missing";

            }break;

            case StatusCodes.WHERE_MISSING:{

                message = "Where is missing";
                System.out.println("Where is missing");

            }break;

            case StatusCodes.QUERY_NOT_OK:{

                message = "Query is not ok";
                System.out.println("Query is not correct");

            }break;

        }*/

        responseBackToClient.sendMessageBackToClient(query.getSenderId(), message);
    }
}
