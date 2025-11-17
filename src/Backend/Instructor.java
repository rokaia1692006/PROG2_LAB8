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
public class Instructor extends PersonDetails {
    
    private final String role = "instructor";
    private ArrayList<String> createdCourses;

    public Instructor(String userName, String email, byte [] hashPassword, byte[]salt) {
        super( email, hashPassword, salt,userName);
        this.createdCourses = new ArrayList<>();
    }
public Instructor(String userName, String id, String email, byte [] hashPassword,byte[]salt,ArrayList<String> CreatedCourses) {
        super(id, email, hashPassword,salt, userName);
        this.createdCourses =CreatedCourses ;
    }
@Override
public void settRandomId(){
this.setId(generate.InstructorID());

}
@Override
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
