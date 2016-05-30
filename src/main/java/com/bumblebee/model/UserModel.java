package com.bumblebee.model;

import com.bumblebee.database.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by deadcode on 25/04/16.
 */

@Service
public class UserModel {

    @Autowired
    private UserDAO userDAO;



}
