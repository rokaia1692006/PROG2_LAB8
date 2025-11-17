/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;
import javax.swing.JOptionPane;

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
       if (userName == null || userName.trim().isEmpty()) {
     JOptionPane.showMessageDialog(null, "USERNAME CAN'T BE EMPTY!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    if (userName.length() < 3) {
        JOptionPane.showMessageDialog(null, "MUST INCLUDE ATLEAST 3 LETTERS!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    if (userName.length() > 20) {
      JOptionPane.showMessageDialog(null, "USERNAME TOO LONG!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    if (userName.contains(" ")) {
        JOptionPane.showMessageDialog(null, "CAN'T CONTAIN SPACES", "Error", JOptionPane.ERROR_MESSAGE);
    }

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
            JOptionPane.showMessageDialog(null, "INVALID EMAIL!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash; }
   

}
