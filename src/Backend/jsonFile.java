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
    private static ArrayList<Instructor> instructors;
    private static ArrayList<String> lids;
    private static final String CFile = "courses.json";
    private static final String UFile = "users.json";
    static{LoadAllFiles();}
    public static void LoadAllFiles(){
    AllCourses = new ArrayList<>();
    Students = new ArrayList<>();
    instructors = new ArrayList<>();
    lids = new ArrayList<>();

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
          Course c;
          if(course.has("ID")){
          String cid = course.getString("ID");
          c = new Course(title, dis, ins, cid);
          } else {
          c = new Course(title, dis, ins);
          }
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
              Lesson ltenp = new Lesson(lTitle, lContent, resources);
              lids.add(ltenp.getLessonID());
            ls.add(ltenp);
              
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
        e.printStackTrace();
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
          e.printStackTrace();
      }
    }
        public static void addStudent(Student s){
        if(s  == null){
        return;
        }
        Students.add(s);
            SAVE();
        
        }
        public static void addInstructor(Instructor i){
        if(i  == null){
        return;
        }
        instructors.add(i);
        SAVE();
        
        
        }
        
         public static void CreateCourse (String insID, String title, String description){
             Instructor ins = containsInstructor(insID);
             if(ins  == null){
             JOptionPane.showMessageDialog(null, "NO INSTRUCTOR");
             return;
             
             
             }
             Course c = new Course(title,description,ins);
             ins.addCourse(c.getCourseId());
             AllCourses.add(c);
             SAVE();
        
        }
         public static void updatecourse (String insID, String CId , String title, String description){
              Instructor ins = containsInstructor(insID);
              Course c= containsCourse(CId);
             if(ins  == null || c == null || !insID.equals(c.getInstructorId())){
             JOptionPane.showMessageDialog(null, "NO INSTRUCTOR");
             return;
             
             
             }
             c.UpdateValues(title, description);
             SAVE();
             
             
         }
         
        public static void DeleteCourse(String cId , String insID){
         Instructor ins = containsInstructor(insID);
              Course c= containsCourse(cId);
             if(ins  == null || c == null || !insID.equals(c.getInstructorId())){
             JOptionPane.showMessageDialog(null, "CANNOT DELETE");
             return;
             
             }
             
             ins.deleteCourse(cId);
             AllCourses.remove(c);
             SAVE();
        }
                                          
    public static Instructor containsInstructor(String id){
    for(Instructor i : instructors){
    if(i.getId().equals(id)){
    return i;
    }
    }
    return null;
    
    }
    
    public static Student containsStudent(String id){
    for(Student i : Students){
    if(i.getId().equals(id)){
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
     public static Student studentEmail(String email){
         for(Student i:Students){
             if(i.getEmail().equals(email))
                 return i;
         }
         return null;
     }
     public static Instructor instructorEmail(String email){
         for(Instructor i:instructors){
             if(i.getEmail().equals(email))
                 return i;
         }
         return null;
     }
     public static void SAVE(){
     SaveCourse();
     SaveUsers();
     
     }
     private static void SaveCourse(){
     try{
        JSONArray arr = new JSONArray();
        for(Course c : AllCourses){
        JSONObject o = new JSONObject();
        o.put("ID", c.getCourseId());
        o.put("Title", c.getTitle());
        o.put("Description", c.getDescription());
        o.put("InstructorID", c.getInstructorId());
        JSONArray ls = new JSONArray();
        
        
        for(Lesson l : c.getLessons()){
        JSONObject lo = new JSONObject();
        lo.put("Title", l.getTitle());
        lo.put("Content", l.getContent());
        lo.put("Resources", new JSONArray(l.getResources()));
        ls.put(lo);
        }
        o.put("Lessons", ls);
        arr.put(o);
        }
        JSONObject root = new JSONObject();
        root.put("Courses", arr);
        Files.write(Paths.get(CFile), root.toString(4).getBytes());
        }
        catch(Exception e){
        e.printStackTrace();
        }
        }
     
     
private static void SaveUsers(){
    try{
    JSONObject root = new JSONObject();
    JSONArray stuarr = new JSONArray();
    for(Student s : Students){
    JSONObject o = new JSONObject();
    o.put("ID", s.getId());
    o.put("Name", s.getName());
    o.put("Email", s.getEmail());
    o.put("PasswordHash", s.getPasswordHash());
    o.put("Enrolled_Courses", new JSONArray(s.getCourses()));
    stuarr.put(o);
    }
    JSONArray insarr = new JSONArray();
    for(Instructor i : instructors){
    JSONObject o = new JSONObject();
    o.put("ID", i.getId());
    o.put("Name", i.getName());
    o.put("Email", i.getEmail());
    o.put("PasswordHash", i.getPasswordHash());
    o.put("CreatedCourses", new JSONArray(i.getCourses()));
    insarr.put(o);
    }
    root.put("Students", stuarr);
    root.put("Instructors", insarr);
    Files.write(Paths.get(UFile), root.toString(4).getBytes());
    }
    catch(Exception e){
    e.printStackTrace();
    }
    }

     
     }
