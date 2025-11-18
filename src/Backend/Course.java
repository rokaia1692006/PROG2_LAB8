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
    private ArrayList<String> students;
    private boolean  checkIfInstructor(PersonDetails p){
     if(!p.getRole().equals("instructor")){
       return false;
     }
     return true;
    }
    public Course(String title, String description, PersonDetails Instructor) {
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
    public Course(String id ,String title, String description, PersonDetails Instructor) {
       if(!checkIfInstructor(Instructor)){
        JOptionPane.showMessageDialog(null, "CANT CREATE COURSE");
        return;
       }
        this.courseId = id;
        this.title = title;
        this.description = description;
        this.InstructorId = Instructor.getId();
        this.lessons  = new ArrayList<>();
        this.students  = new ArrayList<>(); 
    }
        public void addLesson(Lesson l , PersonDetails I){
    if(!checkIfInstructor(I)){
        JOptionPane.showMessageDialog(null, "CANT CREATE COURSE");
        return;
       }
       this.lessons.add(l);
    }
    public void addLessons(ArrayList<Lesson> l , PersonDetails I){
    if(!checkIfInstructor(I)){
        JOptionPane.showMessageDialog(null, "CANT CREATE COURSE");
        return;
       }
       this.lessons.addAll(l);
    }
    private ArrayList<String> getALLLEssonIDS(){
    ArrayList<String> lessonIDs = new ArrayList<>();
      for (Lesson l : this.getLessons()) {  
     lessonIDs.add(l.getLessonID());
    }
    return lessonIDs;
    }
    public void enrollInCourse(Students s ){
    if(!students.contains(s)){
    if (!s.checkifEnrolled(this.getCourseId())) { 
        s.newEnrollCourses(this.getCourseId(), this.getALLLEssonIDS());
    }
    students.add(s.getId());
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

    public String getInstructorId() {
        return InstructorId;
    }

    public void UpdateValues(String Title , String description){
        
        this.title = Title;
        this.description = description;
    }

    
}

