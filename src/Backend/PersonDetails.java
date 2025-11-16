/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author LapTop
 */
public abstract class PersonDetails {
     private String id; 
    private String email;
    private String passwordHash;
    private String userName;

    public PersonDetails(String id, String email, String passwordHash, String userName) {
        setId(id);
        setEmail(email);
        setPasswordHash(passwordHash);
        setUserName(userName);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email){
        String regex = "^[A-Za-z0-9][A-Za-z0-9+_.-]*[A-Za-z0-9]@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"; 
        if (email.matches(regex)){
        this.email = email;
        }
        else {
            throw new IllegalArgumentException("Invalid email.");
        }
    }

    
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash; }
   

}
