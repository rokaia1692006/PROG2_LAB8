
package Backend;

import java.util.ArrayList;



/**
 *
 * @author DELL
 */

public class Students extends details{
    
    private int userId;
    private final static String ROLE = "Student";
    private String username;
    private String email, passwordHash;
    private ArrayList<Course> enrolledCourses;
    private float progress;
  
    
    public Students(int userId, String username, String email, String passwordHash,float progress) {
        
        super(userId,username,email,passwordHash);
        this.progress=progress;
        
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public int getUserId() {
        return userId;
    }

    public static String getROLE() {
        return ROLE;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public float getProgress() {
        return progress;
    }
    
    
}
