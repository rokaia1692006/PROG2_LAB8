/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;



/**
 *
 * @author it
 */
public class jsonFile {
    private static UsersDB udB = new UsersDB();
    private static CoursesDB cdb = new CoursesDB();
    
   
   
    
    public final void LOAD(){
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

    public jsonFile() {
        LOAD();
    }
   
        public static Course  containsCourse(String id) {
        
        return CoursesDB.containsCourse(id);
        }
    public static Students containsStudent(String id){
    return UsersDB.containsStudent(id);
    }
    
    public static Students studentEmail(String email, char [] password){
    return udB.studentEmail(email, password);
    }
       public  static Instructor instructorEmail(String email, char [] password){
       return udB.instructorEmail(email, password);
       }
       public  static void addStudent(Students s){
       udB.addStudent(s);
       }
        public  static void addInstructor(Instructor i){
        udB.addInstructor(i);
        }
        public  static Instructor containsInstructor(String id){
        return udB.containsInstructor(id);
        } 
       public static void  SAVE(){
       udB.SAVE();
       cdb.SAVE();
       
       }
       public static ArrayList<Students> getAllStudentinCourse(String id){
       return UsersDB.getAllStudentinCourse(id);
       
       }
       public static ArrayList<Course> getAllCourses(){
       return cdb.getAllCourses();
       }
      public static Course CreateCourse(String insId, String title, String description){
      return cdb.CreateCourse(udB, cdb, insId, title, description);
      }
       public static  void updatecourse (String insID, String CId , String title, String description){
       cdb.updatecourse(udB, cdb, insID, CId, title, description);
       }
       public static void  DeleteCourse(String cId , String insID){
       cdb.DeleteCourse(udB,cdb,cId,insID );
       }
     }
