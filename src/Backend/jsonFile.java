/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author it
 */
public class jsonFile {
    private static ArrayList<Course> AllCourses;
    private static ArrayList<Student> Students;
    private static ArrayList<Instructors> instructors;
    private static final String CFile = "courses.json";
    private static final String UFile = "users.json";
    
    public static ArrayList <Course> loadCourses(){
      try{
      Path p =Paths.get(CFile);
      if(Files.exists(p) ){
      
          String data = new String(java.nio.file.Files.readAllBytes(p));
          JSONObject o = new JSONObject(data);
          JSONArray a = o.getJSONArray("Courses");
          for(int i = 0 ; i < a.length() ; i++){
          JSONObject course = a.getJSONObject(i);
          String title = course.getString("Title");
          String dis = course.getString("Description");
          String InstructorID = course.getString("InstructorID");
          if(!containsInstructor(InstructorID)){
              JOptionPane.showMessageDialog(null, "FILE ERROR");
              return null;
          }
          Course c = new Course(title, dis, InstructorID);
          if(course.has("Lessons")){
              ArrayList<Lesson> ls = new ArrayList<>();
          JSONArray larr = course.getJSONArray("Lessons");
          for(int k = 0 ; k <larr.length();k++){
              JSONObject l = larr.getJSONObject(k);
              String lTitle = l.getString("Title");
              String lContent = l.getString("Content");
              ArrayList<String> resources = new ArrayList<>();
              if(l.has("Resources")){
              JSONArray rarr = l.getJSONArray("Resources");
              for(int j = 0; j < rarr.length(); j++){
              resources.add(rarr.getString(j));
          }
          }
            ls.add(new Lesson(title, lContent, resources));
              
          }
         
           c.;
          
          }
          }
      }  
      else{
          return null;
      }
    
    }
    catch(Exception e){
        
    }
    return null;
    }
    
    public static boolean containsInstructor(String id){
    for(Instructors i : instructors){
    if(i.getid().equals(id)){
    return true;
    }
    }
    return false;
    
    }
    
    public static boolean containsStudent(String id){
    for(Student i : Students){
    if(i.getid().equals(id)){
    return true;
    }
    }
    return false;
    
    }
     public static boolean containsCourse(String id){
    for(Course i : AllCourses){
    if(i.getCourseId().equals(id)){
    return true;
    }
    }
    return false;
    
    }
}
