package com.bumblebee;

import com.bumblebee.ChatbotFiles.*;
import com.bumblebee.common.utils.WatsonCallback;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.JsonParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.Future;
import java.util.stream.Stream;

@SpringBootApplication
public class BumblebeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BumblebeeApplication.class, args);



	}


}
