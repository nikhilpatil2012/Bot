package com.bumblebee.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by deadcode on 24/04/16.
 */


@RestController
public class UserController {


    @RequestMapping(value = "/*")
    public ResponseEntity<String> addUser(HttpServletRequest request) {

        Action action = new ActionFactory().getAction(request);

        ActionResult actionResult = action.execute();

        return actionResult.createResponse();
    }




}
