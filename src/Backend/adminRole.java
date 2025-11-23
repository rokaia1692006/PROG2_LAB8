/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;

/**
 *
 * @author it
 */
public class adminRole extends PersonDetails{
    private String RandomId;
    private final String Role  = "Admin";
public adminRole(String userName, String email, String pass) {
   
    
   super(email, hash(pass.toCharArray(), new byte[0]), new byte[0], userName);
        
    }
private static byte[] hash(char[] password, byte[] salt){
    try{
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        return md.digest(new String(password).getBytes(StandardCharsets.UTF_8));
    }
    catch(Exception e){
        return null;
    }
}

    @Override
    public void settRandomId() {
        
        RandomId = generate.AdminID();
    }

    @Override
    public String getRole() {
      return Role;
    }
    
    
}
