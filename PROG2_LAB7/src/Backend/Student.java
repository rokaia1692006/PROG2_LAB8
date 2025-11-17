
package Backend;
import Backend.Course;
import Backend.PersonDetails;
import java.util.ArrayList;



/**
 *
 * @author DELL
 */

public class Student extends PersonDetails {
    
    private final static String ROLE = "Student";
    private ArrayList<Course> enrolledCourses;
    private float progress;
  
    
    public Student(int userId, String userName, String email, String passwordHash,float progress) {
        
        super(userId,email,passwordHash,userName);
        setProgress(progress);
        
    }

    

    public void setProgress(float progress) {
        this.progress = progress;
    }

    @Override
    public static String getROLE() {
        return ROLE;
    }


    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public float getProgress() {
        return progress;
    }
    
    
}
