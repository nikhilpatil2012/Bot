/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bumblebee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author deadcode
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

  private String name;
  private String email;
  private String gender;


    public User() {
    }
    
    public User(String name, String email, String gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }
  
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }
}
