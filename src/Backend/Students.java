
package Backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PropertyResourceBundle;
import javax.swing.JOptionPane;



/**
 *
 * @author DELL
 */

public class Students extends PersonDetails{
    
  
    private final static String role = "Student";
    private ArrayList<StudentProgressInCourse> enrolledCourses;
    
  
    
    public Students(String username, String email, byte[] passwordHash,byte[]salt) {
        
        super( email, passwordHash, salt,username);
        
        this.enrolledCourses  = new ArrayList<>();
        
    }
    public Students(String userId, String username, String email, byte[] passwordHash,byte[]salt,ArrayList<StudentProgressInCourse>enrolledData) {
        
        super(userId, email, passwordHash, salt,username);
        
        this.enrolledCourses = enrolledData;
        
    }


    public void setProgress(float progress) {
    
    }

@Override
public void settRandomId(){
this.setId(generate.StudentID());

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
    LessonID = LessonID.trim();
StudentProgressInCourse course = SearchINEnrolled(CourseId.trim());
if(course != null && course.getLessonsDone().containsKey(LessonID)){
    course.markAsDone(LessonID);
} else {
    System.out.println("ha3yt + "  + LessonID);
}

    if(course  == null){
    Course c = jsonFile.containsCourse(CourseId);
    if(c != null){
    ArrayList<String> lessonIDs = new ArrayList<>();
    for(Lesson l : c.getLessons()){
    lessonIDs.add(l.getLessonID());
    }
    newEnrollCourses(CourseId, lessonIDs);
    course = SearchINEnrolled(CourseId);
    if(course == null){
    JOptionPane.showMessageDialog(null, "ERRORINUPDATELESSON");
    return 0;
        }
   
    }
    }
    course.markAsDone(LessonID);
        jsonFile.SAVE();
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
    

    

    public void removeCourse(String cid){
    StudentProgressInCourse spic = SearchINEnrolled(cid);
    if(spic  ==null){
    JOptionPane.showMessageDialog(null, "STUDENT IS NOT ENROLLED");
        return;
    }
    enrolledCourses.remove(spic);
    }
    
}
