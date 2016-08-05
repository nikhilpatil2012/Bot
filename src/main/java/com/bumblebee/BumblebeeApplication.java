package com.bumblebee;

import com.bumblebee.ClientMessage.ClientMessage;
import com.bumblebee.Controller.Action;
import com.bumblebee.ConverstationFiles.Conversation;
import com.bumblebee.ConverstationFiles.ConversationHandler;
import com.bumblebee.ResponseToClient.ResponseActionFactory;
import com.bumblebee.ResponseToClient.ResponseAction;
import com.bumblebee.ResponseToClient.ResponseActionResult;
import com.bumblebee.common.utils.Const;
import com.bumblebee.common.utils.ConversationCodes;
import com.bumblebee.database.DAO.CommonDAO;
import com.bumblebee.database.DAO.UserDAO;
import com.bumblebee.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


@SpringBootApplication
public class BumblebeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BumblebeeApplication.class, args);

/*		FileInputStream stream = null;
		try {
			stream = new FileInputStream(new File("/Users/deadcode/Desktop/good_morning.png"));

			final byte[] bytes = new byte[stream.available()];
			stream.read(bytes);
			stream.close();

			CommonDAO commonDAO = new CommonDAO();
			commonDAO.storeBotImages("good_morning", bytes);

			System.out.println(bytes.length);



		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/


	}

}
