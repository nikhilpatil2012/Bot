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
public class Selfie {
    
    private String lat;
    private String lng;
    private String add;
    private String aidtype;
    private String postalCode;
    private String description;
    private MultipartFile file;

    public Selfie(String lat, String lng, String add, String aidType, String postalCode, String description, MultipartFile file){
        this.lat = lat;
        this.lng = lng;
        this.add = add;
        this.aidtype = aidType;
        this.postalCode = postalCode;
        this.file = file;
        this.description = description;
    }
    
    public Selfie(){        
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getAdd() {
        return add;
    }

    public String getAidtype() {
        return aidtype;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getDescription() {
        return description;
    }

    public MultipartFile getFile() {
        return file;
    }
}
