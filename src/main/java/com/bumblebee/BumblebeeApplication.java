package com.bumblebee;

import com.bumblebee.ChatbotFiles.*;
import com.bumblebee.common.utils.WatsonCallback;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Stream;

@SpringBootApplication
public class BumblebeeApplication {

	public static int count = 0;
	public static String id = "5uDU7k16tIw";
	public static String name = "f_6_2.txt";
	public static List<String> lines = new ArrayList<String>();

	public static void main(String[] args) {
		SpringApplication.run(BumblebeeApplication.class, args);

	//	call(null);

	}

	public static void call(String pageToken){

		String url = "https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&videoId="+id+"&key=AIzaSyD_ey1uReqy7Dje3E3CAiedt3Q_iEWIRxE&maxResults=100&fields=items/snippet,nextPageToken";

		if(pageToken != null){

			url = url + "&pageToken="+pageToken;
		}

		Future<HttpResponse<String>> jsonResponse = Unirest.get(url)
				.asStringAsync(new Callback<String>() {
					@Override
					public void completed(HttpResponse<String> response) {

						JSONObject master = new JSONObject(response.getBody());
						String nextPageToken = null;

						 if(!master.isNull("nextPageToken")){
							 nextPageToken = master.getString("nextPageToken");
							 System.out.println("Pagetoken is "+nextPageToken);
						 }

						JSONArray items = master.getJSONArray("items");



						for(int i=0;i<items.length();i++){

							String comment = items.getJSONObject(i).getJSONObject("snippet").getJSONObject("topLevelComment").getJSONObject("snippet").getString("textDisplay");
							lines.add(comment+"\n");
							System.out.println(comment);
						}



						if(count < 5){
							count += 1;
							call(nextPageToken);
						}
						 else {
							try {
								Files.write(Paths.get("/Users/deadcode/Desktop/"+name), lines);
							} catch (IOException e) {
								System.out.println("file not found "+e);
							}
						}


						/*if(count < 2){
							count += 1;
							call(nextPageToken);
							System.out.println("Calling next token "+nextPageToken);
						}*/
					}

					@Override
					public void failed(UnirestException arg0) {
						throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
					}

					@Override
					public void cancelled() {
						throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
					}
				});
	}
}
