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
        this.createdCourses = new ArrayList<>();
    }

    public String getRole() {
        return role;
    }
    
    public ArrayList<String> getCreatedCourse() {
        return createdCourses;
        
    }

    public void addCourse(String courseId) {
        if (courseId == null || courseId.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "course id can't be empty", "Error", JOptionPane.ERROR_MESSAGE);           
        } else {
            createdCourses.add(courseId);
        }
    }

    public void removeCourse(String courseId) {
        createdCourses.remove(courseId);
    }
}
