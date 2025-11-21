/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;
import java.nio.charset.StandardCharsets;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author it
 */
public abstract class jsonFile {
    private static ArrayList<Course> AllCourses;
    private static ArrayList<Students> Students;
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
//    for(String id  : ins.getCreatedCourse()){
//    if(containsCourse(id)!=null){
//    Correct.add(id);
//    
//    }
//    else{
//    ins.removeCourse(id);
//    }
Iterator<String> i = ins.getCreatedCourse().iterator();
while(i.hasNext()){
String id = i.next();
if(containsCourse(id) == null){
i.remove();

}
}
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
          String title = course.getString("Title").trim();
          String dis = course.getString("Description").trim();
          String InstructorID = course.getString("InstructorID").trim();
          Instructor ins = containsInstructor(InstructorID);
          if(ins == null){
              JOptionPane.showMessageDialog(null, InstructorID);
              JOptionPane.showMessageDialog(null, "FILE ERROR");
              return null;
          }
          Course c;
          if(course.has("ID")){
          String cid = course.getString("ID");
          c = new Course(cid,title, dis, ins);
          } else {
          c = new Course(title, dis, ins);
          }
          if(course.has("Lessons")){
              ArrayList<Lesson> ls = new ArrayList<>();
          JSONArray larr = course.getJSONArray("Lessons");
          for(int k = 0 ; k <larr.length();k++){
              JSONObject l = larr.getJSONObject(k);
              String lid = l.getString("IDS");
              String lTitle = l.getString("Title");
              String lContent = l.getString("Content");
              ArrayList<String> resources = new ArrayList<>();
              if(l.has("Resources")){
              JSONArray rarr = l.getJSONArray("Resources");
              for(int j = 0; j < rarr.length(); j++){
              resources.add(rarr.getString(j));
          }
          }
              Lesson ltenp = new Lesson(lid,lTitle, lContent, resources);
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
                  String id = s.getString("ID").trim();
                  String name = s.getString("Name").trim();
                  String email = s.getString("Email").trim();
                 String password = s.getString("PasswordHash").trim();
                  byte[] passwordHash = Base64.getDecoder().decode(password);
                  String saltStr = s.getString("Salt").trim();
                  byte[] salt = Base64.getDecoder().decode(saltStr);
          
                   ArrayList<StudentProgressInCourse> enrolledCourses = new ArrayList<>();
                  if(s.has("Enrolled_Courses")){
                      JSONArray earr = s.getJSONArray("Enrolled_Courses");
                      
                    
                      for(int j = 0; j < earr.length(); j++){
                          JSONObject ClassObj = earr.getJSONObject(j);
                          String ID = ClassObj.getString("CourseID").trim();
                          int AllLessons = ClassObj.getInt("AllLessons");
                          JSONObject Done = ClassObj.getJSONObject("LessonsDone");
                          HashMap<String, Boolean> LMap = new HashMap<>();
                          for (String k : Done.keySet()){
                          LMap.put(k, Done.getBoolean(k));
                          
                          }
                          float ALLPROGRESS = (float) ClassObj.getDouble("CourseProgress");
                          enrolledCourses.add(new StudentProgressInCourse(ID,AllLessons,LMap,ALLPROGRESS));
                      }
                  }
                  Students.add(new Students(id, name, email, passwordHash ,salt,enrolledCourses));
              }
          }
          if(o.has("Instructors")){
              JSONArray insarr = o.getJSONArray("Instructors");
              for(int i = 0; i < insarr.length(); i++){
                  JSONObject ins = insarr.getJSONObject(i);
                  String id = ins.getString("ID").trim();
                  String name = ins.getString("Name").trim();
                  String email = ins.getString("Email").trim();
                  String password = ins.getString("PasswordHash").trim();
                  byte[] passwordHash = Base64.getDecoder().decode(password);
                  String saltStr = ins.getString("Salt").trim();
                  byte[] salt = Base64.getDecoder().decode(saltStr);
                  ArrayList<String> createdCourses = new ArrayList<>();
                  if(ins.has("CreatedCourses")){
                      JSONArray carr = ins.getJSONArray("CreatedCourses");
                      for(int j = 0; j < carr.length(); j++){
                          createdCourses.add(carr.getString(j));
                      }
                  }
                  instructors.add(new Instructor(name, id, email, passwordHash, salt ,createdCourses));
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
        public static void addStudent(Students s){
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
        
         public static Course  CreateCourse (String insID, String title, String description){
             Instructor ins = containsInstructor(insID);
             if(ins  == null){
             JOptionPane.showMessageDialog(null, "NO INSTRUCTOR");
             return null;
             
             
             }
             Course c = new Course(title,description,ins);
             ins.addCourse(c.getCourseId());
             AllCourses.add(c);
             
             SAVE();
             return c;
        
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
             
             ins.removeCourse(cId);
             AllCourses.remove(c);
             SAVE();
        }
                                          
    public static Instructor containsInstructor(String id){
        id=id.trim();
        
    for(Instructor i : instructors){
        System.err.println(i.getId().trim());
    if(i.getId().trim().equals(id)){
    return i;
    }
    }
    
    return null;
    
    }
     public static ArrayList<Students> getAllStudentinCourse(String courseid){
         ArrayList<Students> st = new ArrayList<>();
     for(Students s : Students){
     if(s.checkifEnrolled(courseid)){
     st.add(s);
     }
     
     }
     return st;
     }
    
    public static Students containsStudent(String id){
        id=id.trim();
    for(Students i : Students){
    if(i.getId().equals(id)){
    return i;
    }
    }
    return null;
    
    }
     public static Course containsCourse(String id){
         id=id.trim();
    for(Course i : AllCourses){
    if(i.getCourseId().equals(id)){
    return i;
    }
    }
    return null;
    
    }
     
    private static byte[] hashPassword(char[] password, byte[] salt){
    try {
    MessageDigest md = MessageDigest.getInstance("SHA-512");
    md.update(salt);
    return md.digest(new String(password).getBytes(StandardCharsets.UTF_8));
    } 
    catch(Exception e){
    e.printStackTrace();
    return null;
    }
}
     public static Students studentEmail(String email, char [] password){
         for(Students i:Students){
             if(i.getEmail().equals(email)){
                 byte[] hashP = hashPassword(password, i.getSalt());
                 if(MessageDigest.isEqual(hashP, i.getPasswordHash()))
                 return i;
             }
         }
         return null;
     }
     public static Instructor instructorEmail(String email, char [] password){
         for(Instructor i:instructors){
             if(i.getEmail().equals(email)){
                 byte[] hashP = hashPassword(password, i.getSalt());
                 if(MessageDigest.isEqual(hashP, i.getPasswordHash()))
                 return i;
             }
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
        lo.put("IDS", l.getLessonID());
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
    for(Students s : Students){
    JSONObject o = new JSONObject();
    o.put("ID", s.getId());
    o.put("Name", s.getUserName());
    o.put("Email", s.getEmail());
    o.put("PasswordHash", Base64.getEncoder().encodeToString(s.getPasswordHash()));
    o.put("Salt", Base64.getEncoder().encodeToString(s.getSalt()));

    JSONArray Enrolled = new JSONArray();
    for(StudentProgressInCourse spc : s.getEnrolledCourses()){
    JSONObject ClassObj = new JSONObject();
    ClassObj.put("CourseID", spc.getCourseId());
    ClassObj.put("AllLessons", spc.getAllLessonsInCourse());

    JSONObject LessonsDone = new JSONObject();
    for(Map.Entry<String, Boolean> entry : spc.getLessonsDone().entrySet()){
    LessonsDone.put(entry.getKey(), entry.getValue());
    }
    ClassObj.put("LessonsDone", LessonsDone);
    ClassObj.put("CourseProgress", spc.getOverallProgress());

    Enrolled.put(ClassObj);
    }

    o.put("Enrolled_Courses", Enrolled);
    stuarr.put(o);
    }
    JSONArray insarr = new JSONArray();
    for(Instructor i : instructors){
    JSONObject o = new JSONObject();
    o.put("ID", i.getId());
    o.put("Name", i.getUserName());
    o.put("Email", i.getEmail());
   o.put("PasswordHash", Base64.getEncoder().encodeToString(i.getPasswordHash()));
    o.put("Salt", Base64.getEncoder().encodeToString(i.getSalt()));
    o.put("CreatedCourses", new JSONArray(i.getCreatedCourse()));
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

    public static ArrayList<Course> getAllCourses() {
        return AllCourses;
    }

    public static ArrayList<Students> getStudents() {
        return Students;
    }

    public static ArrayList<String> getLids() {
        return lids;
    }


     }
