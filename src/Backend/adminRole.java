/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author it
 */
public class adminRole extends PersonDetails{
    private String RandomId;
    private final String Role  = "Admin";
public adminRole(String userName, String email, byte [] hashPassword, byte[]salt) {
        super( email, hashPassword, salt,userName);
        
    }
public adminRole(String userName, String id, String email, byte [] hashPassword,byte[]salt,ArrayList<String> CreatedCourses) {
        super(id, email, hashPassword,salt, userName);
       
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
