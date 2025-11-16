/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author it
 */
public class Course {
    private String courseId;
    private String title;
    private String description;
    private String InstructorId;
    private ArrayList<Lesson> lessons;
    private ArrayList<Student> students;
    private boolean  checkIfInstructor(Person p){
     if(!p.getRole().equals("instructor")){
       return false;
     }
     return true;
    }
    public Course(String title, String description, Person Instructor) {
       if(!checkIfInstructor(Instructor)){
        JOptionPane.showMessageDialog(null, "CANT CREATE COURSE");
        return;
       }
        this.courseId = generate.CourseId();
        this.title = title;
        this.description = description;
        this.InstructorId = Instructor.getId();
        this.lessons  = new ArrayList<>();
        this.students  = new ArrayList<>(); 
    }
    public void addLessons(ArrayList<Lesson> l , Person I){
    if(!checkIfInstructor(I)){
        JOptionPane.showMessageDialog(null, "CANT CREATE COURSE");
        return;
       }
       this.lessons.addAll(l);
    }
    public void enrollInCourse(Student s ){
    if(!students.contains(s)){
    students.add(s);
    }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public String getCourseId() {
        return courseId;
    }
    
}

