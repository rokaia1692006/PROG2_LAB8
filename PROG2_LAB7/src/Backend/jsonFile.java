/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author it
 */
public class jsonFile {
    public static ArrayList<Course> AllCourses;
    public static ArrayList<Student> Students;
    public static ArrayList<Instructors> instructors;
    private static final String CFile = "courses.json";
    private static final String UFile = "users.json";
    
    public static ArrayList <Course> loadCourses(){
      try{
      Path p =Paths.get(CFile);
      if(p. )
      
          String data = new String(java.nio.file.Files.readAllBytes());
      }  
    
    }
    
}
