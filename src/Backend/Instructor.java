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
public class Instructor extends Person implements details {

    private String id; 
    private String email;
    private String passwordHash;
    private ArrayList<String> createdCourses;
public Instructor(String fullName, String id,String email,String hashPassword) {
        super(fullName);
       setEmail(email);
       setId(id);
       setPasswordHash (passwordHash);
        
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

    @Override
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash; }
    public ArrayList<String> getCreatedCourse()
            {
                return createdCourses;


            }
    public void addCourse (String courseId)
    {
    if (courseId==null||courseId.trim().isEmpty())
    {
       throw new IllegalArgumentException("Course ID cannot be empty!!"); 
    }else 
    {
        createdCourses.add(courseId);
    }
    }
     public void removeCourse(String courseId) {
        createdCourses.remove(courseId);
    }}
    
 

    