/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Backend;
import java.util.Random;
import javax.swing.JOptionPane;
/**
 *
 * @author it
 */
public class generate {
    private static String RandomString( StringBuilder s){
         String Allchar="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
              Random r = new Random();
             
        for(int  i = 0 ; i < 10; i ++ ){
        s.append(Allchar.charAt(r.nextInt(Allchar.length())));
        
        }
        return s.toString();
    }
    public  static String CourseId(){
       
        StringBuilder s = new StringBuilder();
        s.append("C_");
        String id  = RandomString(s);
        if(jsonFile.containsCourse(id)!=null){
          id  = RandomString(s);
        }
        return id;
    
    
    }
     public  static String LessonID(){
       
        StringBuilder s = new StringBuilder();
        s.append("L_");
        String id  = RandomString(s);
        
        
        return id;
    
    
    }
      public  static String InstructorID(){
       
        StringBuilder s = new StringBuilder();
        s.append("I_");
        String id  = RandomString(s);
        if(jsonFile.containsInstructor(id)!=null){
          id  = RandomString(s);
        }
        return id;
    
    
    }
       public  static String StudentID(){
       
        StringBuilder s = new StringBuilder();
        s.append("S_");
        String id  = RandomString(s);
        if(jsonFile.containsStudent(id)!=null){
          id  = RandomString(s);
        }
        return id;
    
    
    }
}
