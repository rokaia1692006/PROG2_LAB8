/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Certificate {
    
    String studentId;
    String courseId;
    int certificateId;
    //Date issueDate;

    public Certificate(String studentId, String courseId, int certificateId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.certificateId = certificateId;
        //and the date to be added
    }
    
    public boolean allQuizzesPassed(Students s)
    {
        ArrayList<Course> courses = jsonFile.getAllCourses();
       for(Course c : courses)
       {
           if(c.getCourseId().equals(courseId))
           {
               ArrayList<Quizzes> quizzes = jsonFile.getAllQuizzes(); //han-assume en el function de m3ana
               break;
           }
       }
       for ( Quiz q : quizzes)
       {
           if(!q.isPassed())
           {
              JOptionPane.showMessageDialog(null,"One or more quizzes is not passed yet, cannot create certificate!"); //lw l2ena quiz mesh passed, cannot create
           return false;
           }
       }
       return true; //kolo passed!!
        
    }
    
    
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public int getCertificateId() {
        return certificateId;
    }
    
    
}
