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
public class Instructor extends PersonDetails {
    
    private final String role = "instructor";
    private ArrayList<String> createdCourses;

    public Instructor(String userName, String id, String email, String hashPassword) {
        super(id, email, hashPassword, userName);
        
    }
    
    public ArrayList<String> getCreatedCourse() {
        return createdCourses;
        
    }

    public void addCourse(String courseId) {
        if (courseId == null || courseId.trim().isEmpty()) {
            throw new IllegalArgumentException("Course ID cannot be empty!!");            
        } else {
            createdCourses.add(courseId);
        }
    }

    public void removeCourse(String courseId) {
        createdCourses.remove(courseId);
    }
}
