package com.bumblebee.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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

}
