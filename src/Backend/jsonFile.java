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
    static{LoadAllFiles();}
    public static void LoadAllFiles(){
    AllCourses = new ArrayList<>();
    Students = new ArrayList<>();
    instructors = new ArrayList<>();
    loadUsers();
    loadCourses();
    FinalValidationIns();
   
    }
    private static void FinalValidationIns(){
    for(Instructor ins : instructors){
    ArrayList <String> Correct = new ArrayList<>();
    for(String id  : ins.getCourses()){
    if(containsCourse(id)!=null){
    Correct.add(id);
    
    }
    }
    ins.setCourses(Correct);
    
    }
    
    }
    private static ArrayList <Course> loadCourses(){
      try{
      Path p =Paths.get(CFile);
      if(Files.exists(p) ){
      
          String data = new String(java.nio.file.Files.readAllBytes(p));
          JSONObject o = new JSONObject(data);
          if(o.has("Courses")){
          JSONArray a = o.getJSONArray("Courses");
          for(int i = 0 ; i < a.length() ; i++){
          JSONObject course = a.getJSONObject(i);
          String title = course.getString("Title");
          String dis = course.getString("Description");
          String InstructorID = course.getString("InstructorID");
          Instructor ins = containsInstructor(InstructorID);
          if(ins == null){
              JOptionPane.showMessageDialog(null, "FILE ERROR");
              return null;
          }
          Course c = new Course(title, dis, ins);
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
         
           c.addLessons(ls,ins);
          
          }
          AllCourses.add(c);
          }}
          else{
               AllCourses = new ArrayList<>();
          return null;
          }
      }  
      else{
          AllCourses = new ArrayList<>();
          return null;
      }
    
    }
    catch(Exception e){
        
    }
    return AllCourses;
    }
        private static void loadUsers(){
      try{
      Path p = Paths.get(UFile);
      if(Files.exists(p)){
          String data = new String(Files.readAllBytes(p));
          JSONObject o = new JSONObject(data);
          if(o.has("Students")){
              JSONArray stuarr = o.getJSONArray("Students");
              for(int i = 0; i < stuarr.length(); i++){
                  JSONObject s = stuarr.getJSONObject(i);
                  String id = s.getString("ID");
                  String name = s.getString("Name");
                  String email = s.getString("Email");
                  String passwordHash = s.getString("PasswordHash");
                  ArrayList<String> enrolledCourses = new ArrayList<>();
                  if(s.has("Enrolled_Courses")){
                      JSONArray earr = s.getJSONArray("Enrolled_Courses");
                      for(int j = 0; j < earr.length(); j++){
                          enrolledCourses.add(earr.getString(j));
                      }
                  }
                  Students.add(new Student(id, name, email, passwordHash, enrolledCourses));
              }
          }
          if(o.has("Instructors")){
              JSONArray insarr = o.getJSONArray("Instructors");
              for(int i = 0; i < insarr.length(); i++){
                  JSONObject ins = insarr.getJSONObject(i);
                  String id = ins.getString("ID");
                  String name = ins.getString("Name");
                  String email = ins.getString("Email");
                  String passwordHash = ins.getString("PasswordHash");
                  ArrayList<String> createdCourses = new ArrayList<>();
                  if(ins.has("CreatedCourses")){
                      JSONArray carr = ins.getJSONArray("CreatedCourses");
                      for(int j = 0; j < carr.length(); j++){
                          createdCourses.add(carr.getString(j));
                      }
                  }
                  instructors.add(new Instructor(id, name, email, passwordHash, createdCourses));
              }
          }
      }
      else{
      instructors = new ArrayList<>();
      Students = new ArrayList<>();
      
      }
      }
      catch(Exception e){
          
      }
    }
        public static void addStudent(Student s){
        if(s  == null){
        return;
        }
        Students.add(s);
        
        
        }
        public static void addInstructor(Instructor i){
        if(i  == null){
        return;
        }
        instructors.add(i);
        
        
        }
        
        
                                          
    public static Instructor containsInstructor(String id){
    for(Instructor i : instructors){
    if(i.getid().equals(id)){
    return i;
    }
    }
    return null;
    
    }
    
    public static Student containsStudent(String id){
    for(Student i : Students){
    if(i.getid().equals(id)){
    return i;
    }
    }
    return null;
    
    }
     public static Course containsCourse(String id){
    for(Course i : AllCourses){
    if(i.getCourseId().equals(id)){
    return i;
    }
    }
    return null;
    
    }
}
