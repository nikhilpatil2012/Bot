/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bumblebee.Helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author deadcode
 */

@Service
public class SecurityHandler {
    
     
/*    public void storeSecretKey(SecretKey secretKey){
         
                   FileOutputStream file = null;
            try {
               
                file = new FileOutputStream("key.properties");
                Properties prop = new Properties();
                prop.setProperty("key", Base64.getEncoder().encodeToString(secretKey.getEncoded()));
                prop.store(file, "");
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AddDataCtrl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AddDataCtrl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    file.close();
                } catch (IOException ex) {
                    Logger.getLogger(AddDataCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
    }
    
    public SecretKey getSecretKey(){
                   
                   SecretKey secretKey = null;
                   FileInputStream file = null;
                   
            try {
               
                file = new FileInputStream("key.properties");
                Properties prop = new Properties();
                prop.load(file);
                
                byte[] decodedKey = Base64.getDecoder().decode(prop.getProperty("key"));
                
                secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AddDataCtrl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AddDataCtrl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    file.close();
                } catch (IOException ex) {
                    Logger.getLogger(AddDataCtrl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            return secretKey;
    }
    
    public String generateFreshAccessToken(User user){

        SecretKey secretKey = getSecretKey();

        // Add the userid and subject and generate the access token
       String token = Jwts.builder().claim("name", user.getName()).claim("email", user.getEmail()).setSubject("SaveASelfie").signWith(SignatureAlgorithm.HS512, secretKey).compact();

                return token;
    }

    public int checkAccessToken(String token){

        token = token.replace("Bearer", "");

        return  Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody().getSubject().equals("SaveASelfie") ? ResponseCodes.ACCESS_TOKEN_ACCEPTED : ResponseCodes.ACCESS_TOKEN_REJECTED;
    }*/
    
}

// Generate a secret key
// SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();