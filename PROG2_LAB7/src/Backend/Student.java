
package Backend;
import Backend.Course;
import Backend.PersonDetails;
import java.util.ArrayList;
import Backend.StudentProgressInCourse;
import javax.swing.JOptionPane;



/**
 *
 * @author DELL
 */

public class Student extends PersonDetails {
    
    private final static String ROLE = "Student";
    private ArrayList<Course> enrolledCourses;
    private ArrayList<StudentProgressInCourse> enrolledData;
    private float progress;
  
    
    public Student(int userId, String userName, String email, String passwordHash,float progress) {
        
        super(userId,email,passwordHash,userName);
        this.progress=progress;
        this.enrolledCourses= new ArrayList<>();
    }
     
    public Student(String userId, String username, String email, String passwordHash,float progress,ArrayList<StudentProgressInCourse>enrolledData) {
        
        super(userId, email, passwordHash, username);
        this.progress=progress;
        this.enrolledCourses = enrolledData;
        
    }
  
    @Override
    public static String getROLE() {
        return ROLE;
    }

    public void setProgress(float progress) {
        this.progress = progress;
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
    
    public void UpdateLesson(String CourseId, String LessonID){
    StudentProgressInCourse course = SearchINEnrolled(CourseId);
    if(course  == null){
        JOptionPane.showMessageDialog(null, "ERROR");
    
    }
    else{
    course.markAsDone(LessonID);
    
    }
    
    
    }
    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
   
   
    
    
}
