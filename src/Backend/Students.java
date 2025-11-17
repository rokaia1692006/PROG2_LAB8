
package Backend;

import java.util.ArrayList;



/**
 *
 * @author DELL
 */

public class Students extends PersonDetails{
    
  
    private final static String role = "Student";
    private ArrayList<String> enrolledCourses;
    private float progress;
  
    
    public Students(String userId, String username, String email, String passwordHash,float progress) {
        
        super(userId, email, passwordHash, username);
        this.progress=progress;
        this.enrolledCourses  = new ArrayList<>();
        
    }
    public Students(String userId, String username, String email, String passwordHash,float progress,ArrayList<String>Enrolled) {
        
        super(userId, email, passwordHash, username);
        this.progress=progress;
        this.enrolledCourses = Enrolled;
        
    }


    public void setProgress(float progress) {
        this.progress = progress;
    }

   @Override
    public String getRole() {
        return role;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public String getPasswordHash() {
//        return passwordHash;
//    }

    public ArrayList<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public float getProgress() {
        return progress;
    }
    
    
}
