package com.bumblebee.Controller;

import com.bumblebee.model.Response;
import com.bumblebee.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by deadcode on 24/04/16.
 */


@RestController
public class UserController {

    @Autowired
    UserModel userModel;

    @RequestMapping(value = "/*")
    public Response addUser(HttpServletRequest request) {

        Action action = new ActionFactory().getAction(request);

        ActionResult actionResult = action.execute();

        return actionResult.createResponse();
    }


}
