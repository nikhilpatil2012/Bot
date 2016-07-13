package com.bumblebee.ChatbotFiles;

import com.bumblebee.ClientMessage.MultiPartBodyWithMessage;
import com.bumblebee.ClientMessage.MultiPartBodyWithPayload;
import com.bumblebee.ResponseToClient.FetchDataFromFourSq;
import com.bumblebee.common.utils.PlaceListCallback;
import com.bumblebee.common.utils.StatusCodes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deadcode on 02/06/2016.
 */
@Service
public class ResponseExecuter implements PlaceListCallback{

    private FetchDataFromFourSq fetchDataFromFourSq = new FetchDataFromFourSq();

    private ResponseBackToClient responseBackToClient = new ResponseBackToClient();

    private MultiPartBodyWithMessage multiPartBodyWithMessage;

    public void execute(Query query){

        String message = "Every thing is ok";

        switch (query.getStatusCode()){

            case StatusCodes.QUERY_OK:{

                // Call Four Square
                fetchDataFromFourSq.sendQueryToFourSq(query, this);
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
        }

        Message m = new Message();
        m.setId(query.getSenderId());
        m.setMessage(message);

        //multiPartBodyWithMessage = new MultiPartBodyWithMessage(m);
        //multiPartBodyWithMessage.createMessage();

       // responseBackToClient.sendMessageBackToClient(multiPartBodyWithMessage.getObjectList());
    }

    @Override
    public void getPlaceList(ArrayList<Element> placesList) {

        List<Element> tempList = placesList.subList(0,5);

        for(Element element : placesList){
            //System.out.println(element.getTitle());
            //System.out.println(element.getSubtitle());
        }

        MultiPartBodyWithPayload multiPartBodyWithPayload = new MultiPartBodyWithPayload(tempList);
        multiPartBodyWithPayload.createMessage();

        responseBackToClient.sendMessageBackToClient(multiPartBodyWithPayload.getObjectList());

    }
}
