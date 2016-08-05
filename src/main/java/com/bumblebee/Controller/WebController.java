package com.bumblebee.Controller;

import com.bumblebee.database.DAO.CommonDAO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by deadcode on 24/04/16.
 */


@Controller
public class WebController {


      @RequestMapping("/redirect")
      public String home()
    {
        return "daddy";
    }

    @RequestMapping(path = "/getFileById/{id}", method = RequestMethod.GET)
    public HttpEntity<byte[]> getFileById(@PathVariable String id) {

        CommonDAO commonDAO = new CommonDAO();

        // Get file bytes from user provide file id
        byte[] fileBytes = commonDAO.getUserFileById(id);

        // Get the response headers attached with file bytes
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); //or what ever type it is
        headers.setContentLength(fileBytes.length);

        return new HttpEntity<byte[]>(fileBytes, headers);
    }

    @RequestMapping(path = "/getFileByName/{name}", method = RequestMethod.GET)
    public HttpEntity<byte[]> getFileByName(@PathVariable String name) {

        CommonDAO commonDAO = new CommonDAO();

        // Get file bytes from user provide file id
        byte[] fileBytes = commonDAO.getBotImageFromName(name);

        // Get the response headers attached with file bytes
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); //or what ever type it is
        headers.setContentLength(fileBytes.length);

        return new HttpEntity<byte[]>(fileBytes, headers);
    }

}
