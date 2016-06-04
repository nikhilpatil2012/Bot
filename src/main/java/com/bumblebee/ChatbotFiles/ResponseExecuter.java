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

        switch (query.getStatusCode()){

            case StatusCodes.QUERY_OK:{

                // Call Four Square
                //fetchDataFromFourSq.sendQueryToFourSq(query);
                responseBackToClient.sendMessageBackToClient(query.getSenderId(), "Query is ok bro");

            }break;

            case StatusCodes.WHAT_MISSING:{

                System.out.println("What is missing");

            }break;

            case StatusCodes.WHERE_MISSING:{

                responseBackToClient.sendMessageBackToClient(query.getSenderId(), "Where is missing bro");

                System.out.println("Where is missing");

            }break;

            case StatusCodes.QUERY_NOT_OK:{

                responseBackToClient.sendMessageBackToClient(query.getSenderId(), "I don't get it bro");
                System.out.println("Query is not correct");

            }break;

        }
    }
}
