
package Backend;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */
public class Enrollment {
    
    public void enrollstudentincourse(int courseID, int studentID)
    {
        boolean enroll=false;
        
        String content = new String(Files.readAllBytes(Paths.get("courses.json")), StandardCharsets.UTF_8);
        JSONArray coursesArray = new JSONArray(content);
        
        for (int i = 0 ; i < coursesArray.length(); i++)
        {
            JSONObject course = coursesArray.getJSONObject(i);
            if (course.getInt("courseId") == courseID)
            {
                JSONArray students = course.getJSONArray("students");
                
                
                for (int j = 0; j < students.length(); j++) {
                   if (students.getInt(j) == studentID) {
                       enroll = true;
                       break;
                   }
                     }
                if (enroll)
                {
                    JOptionPane.showMessageDialog(null, "Already enrolled!");
                   }
                else{
                    students.put(studentID);
                    Files.write(Paths.get("courses.json"), coursesArray.toString(4).getBytes(StandardCharsets.UTF_8));
                    JOptionPane.showMessageDialog(null, "Enrolled successfully!");
                }
            }
            {
                
            }
        }
    }
}
