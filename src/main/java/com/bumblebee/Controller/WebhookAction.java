package com.bumblebee.Controller;

import com.bumblebee.ChatbotFiles.ChatParser;
import com.bumblebee.ChatbotFiles.MessageMiner;
import com.bumblebee.ChatbotFiles.Query;
import com.bumblebee.ChatbotFiles.ResponseExecuter;
import com.bumblebee.common.utils.StatusCodes;
import com.bumblebee.common.utils.WatsonCallback;
import com.mashape.unirest.http.Unirest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by deadcode on 29/05/2016.
 */
public class WebhookAction extends Action {

    private ChatParser chatParser = new ChatParser();

    private MessageMiner messageMiner = new MessageMiner();

    private ResponseExecuter responseExecuter = new ResponseExecuter();

    @Override
    public ActionResult execute() {

        String response = "";

        switch (getRequestMethod()){

            case "GET":{

                HashMap<String, String> paramMap;

                if(!(paramMap = getUrlParameter(new String[]{"hub.verify_token","hub.challenge"})).isEmpty()){

                    if(paramMap.get("hub.verify_token").equals("Charlie123")){

                        response = paramMap.get("hub.challenge");
                    }

                }

            }break;

            case "POST":{


                StringBuffer jb = new StringBuffer();
                String line = null;
                try {
                    BufferedReader reader = request.getReader();
                    while ((line = reader.readLine()) != null)
                        jb.append(line);
                } catch (Exception e) { /*report an error*/ }

                response = jb.toString();

                System.err.println(response);

               /* chatParser.parseText(response.toString());

                if(chatParser.getMessageType() != null){
                    System.err.println("Message Type "+chatParser.getMessageType());

                    if(chatParser.getMessageType().equals(StatusCodes.CLIENT_MESSAGE)){
                        messageMiner.sendMessageToWatson(chatParser, new WatsonCallback() {
                            @Override
                            public void GetQuery(Query query) {

                                System.out.println("QUery Message "+query.getWhere()+query.getWhat());

                                // Execute the response
                                responseExecuter.execute(query);

                            }
                        });
                    }
                }*/
               // new UnirestStop().start();

                System.err.println(response);

            }break;
        }

        return createShowPageResult(response);
    }

    class UnirestStop extends Thread{

        @Override
        public void run() {
            super.run();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Close the connection
            try {
                Unirest.shutdown();
                System.err.println("Thread is stopped");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}

