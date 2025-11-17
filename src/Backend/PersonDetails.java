/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author LapTop
 */
public abstract class PersonDetails {
     private String id; 
    
    private String email;
    private byte[] passwordHash;
    private byte[] salt;
    private String userName;

    public PersonDetails(String id, String email, byte[] passwordHash, byte[] salt,String userName) {
        setId(id);
        setEmail(email);
        setSalt(salt);
        setPasswordHash(passwordHash);
        setUserName(userName);
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
     public PersonDetails(String email, byte[] passwordHash,byte []salt, String userName) {
         settRandomId();
         setSalt(salt);
        setEmail(email);
        setPasswordHash(passwordHash);
        setUserName(userName);
    }

    public byte[] getSalt() {
        return salt;
    }
public abstract void settRandomId();
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

    public byte [] getPasswordHash() {
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

    
    public void setPasswordHash(byte [] passwordHash) {
        this.passwordHash = passwordHash; }

    public abstract  String getRole() ;
   

}
