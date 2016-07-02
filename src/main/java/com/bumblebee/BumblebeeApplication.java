package com.bumblebee;

import com.bumblebee.ResponseToClient.ResponseActionFactory;
import com.bumblebee.ResponseToClient.ResponseAction;
import com.bumblebee.ResponseToClient.ResponseActionResult;
import com.bumblebee.common.utils.ConversationCodes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BumblebeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BumblebeeApplication.class, args);


/*		ResponseAction responseAction = new ResponseActionFactory().getAction(ConversationCodes.SHOW_HANGOUT_OPTIONS);
		ResponseActionResult responseActionResult = responseAction.execute();

		responseActionResult.sendMessage();*/

	}

}
