
package Backend;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;



/**
 *
 * @author DELL
 */

public class Students extends PersonDetails{
    
  
    private final static String role = "Student";
    private ArrayList<StudentProgressInCourse> enrolledCourses;
    
  
    
    public Students(String userId, String username, String email, String passwordHash) {
        
        super(userId, email, passwordHash, username);
        
        this.enrolledCourses  = new ArrayList<>();
        
    }
    public Students(String userId, String username, String email, String passwordHash,ArrayList<StudentProgressInCourse>enrolledData) {
        
        super(userId, email, passwordHash, username);
        
        this.enrolledCourses = enrolledData;
        
    }


    public void setProgress(float progress) {
    
    }

   @Override
    public String getRole() {
        return role;
    }

    public void newEnrollCourses(String CourseID , ArrayList<String> LessonIDs) {
        this.enrolledCourses .add(new StudentProgressInCourse(CourseID, LessonIDs.size(), LessonIDs));
        
    }
    public StudentProgressInCourse SearchINEnrolled(String CourseId ){
    for(StudentProgressInCourse course : enrolledCourses){
    if(course.getCourseId().equals(CourseId)){
    return(course);
    }
    
    }
    return null;
    }
    
    public float UpdateLesson(String CourseId, String LessonID){
    StudentProgressInCourse course = SearchINEnrolled(CourseId);
    if(course  == null){
        JOptionPane.showMessageDialog(null, "ERROR");
    return 0;
    }
    else{
    course.markAsDone(LessonID);
    
    }
    
    return course.getOverallProgress();
    
    
    }
public void NewLesson(String courseid , String LessonId){
StudentProgressInCourse course = SearchINEnrolled(courseid);
if(course  == null){
    return ;
    }
else{
course.addlesson(LessonId, courseid);

}


}
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPasswordHash() {
//        return passwordHash;
//    }
public Boolean checkifEnrolled(String courseID){
for(StudentProgressInCourse c : enrolledCourses){
if(c.getCourseId().equals(courseID)){
return true;

}

}
return false;
}
    public ArrayList<StudentProgressInCourse> getEnrolledCourses() {
        return enrolledCourses;
    }

    

    
    
}
