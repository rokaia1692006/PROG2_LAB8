/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Certificate {
    
    String studentId;
    String courseId;
    String certificateId;
    LocalDate issuedate;

    public Certificate(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        //generate the certificate random INTEGER id
         this.issuedate = LocalDate.now(); //mesh hantlobo mn user, el certificate btt3ml hsb el date bt3 enhrda
         //3yzyn n save fel json
    }

    public LocalDate getIssuedate() {
        return issuedate;
    }
    
   
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCertificateId() {
        return certificateId;
    }
    
    
}
