/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;
import static Backend.CoursesDB.containsCourse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author it
 */
public class DBMANAGER {
    
    private UsersDB udB = new UsersDB();
    private CoursesDB cdb = new CoursesDB();
    public void LOAD(){
  udB.LOAD();
  cdb.LOAD();
 
  
  
//    }
// private static void FinalValidationIns(){
//    for(Instructor ins : instructors){
//    ArrayList <String> Correct = new ArrayList<>();
////    for(String id  : ins.getCreatedCourse()){
////    if(containsCourse(id)!=null){
////    Correct.add(id);
////    
////    }
////    else{
////    ins.removeCourse(id);
////    }
//Iterator<String> i = ins.getCreatedCourse().iterator();
//while(i.hasNext()){
//String id = i.next();
//if(CoursesDB.containsCourse(id) == null){
//i.remove();
//
//}
//}
//    }
   
    
    }
    public  Course  CreateCourse (String insID, String title, String description){
             Instructor ins = udB.containsInstructor(insID);
             if(ins  == null){
             JOptionPane.showMessageDialog(null, "NO INSTRUCTOR");
             return null;
             
             
             }
             Course c = new Course(title,description,ins);
             ins.addCourse(c.getCourseId());
             cdb.AddCourse(c);
             
           
             return c;
        
        }
    
    public  void updatecourse (String insID, String CId , String title, String description){
              Instructor ins = udB.containsInstructor(insID);
              Course c= containsCourse(CId);
             if(ins  == null || c == null || !insID.equals(c.getInstructorId())){
             JOptionPane.showMessageDialog(null, "NO INSTRUCTOR");
             return;
             
             
             }
             c.UpdateValues(title, description);
          
             
             
         }
         
        public  void DeleteCourse(String cId , String insID){
         Instructor ins = udB.containsInstructor(insID);
              Course c= containsCourse(cId);
             if(ins  == null || c == null || !insID.equals(c.getInstructorId())){
             JOptionPane.showMessageDialog(null, "CANNOT DELETE");
             return;
             
             }
             
             ins.removeCourse(cId);
             AllCourses.remove(c);
         
        }
    
    
    public Students studentEmail(String email, char [] password){
    return udB.studentEmail(email, password);
    }
       public  Instructor instructorEmail(String email, char [] password){
       return udB.instructorEmail(email, password);
       }
       public  void addStudent(Students s){
       udB.addStudent(s);
       }
        public  void addInstructor(Instructor i){
        udB.addInstructor(i);
        }
        public  Instructor containsInstructor(String id){
        
        } 
       public void  SAVE(){
       udB.SAVE();
       cdb.SAVE();
       
       }

}
