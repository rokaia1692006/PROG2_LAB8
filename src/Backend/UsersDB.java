/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import static Backend.DBMANAGER.UFile;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author it
 */
public class UsersDB extends DBMANAGER{
     private static ArrayList<Students> Students= new ArrayList<>();
    private static ArrayList<Instructor> instructors= new ArrayList<>();
private static ArrayList<Certificate> certificates = new ArrayList<>();
  private static final adminRole admin = new adminRole("admin", "admin@gmail.com", "1");
   
    public UsersDB() {
         Students = new ArrayList<>();
instructors = new ArrayList<>();
    }

  
    public  ArrayList<Students> getStudents() {
        return Students;
    }

    public  ArrayList<Instructor> getInstructors() {
        return instructors;
    }
        
        
        @Override
      public  void LOAD(){
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
                  ArrayList <Certificate> certificates = new ArrayList<>();
                  if(s.has("Certificates")){
                  JSONArray certs = s.getJSONArray("Certificates");
                   for(int j = 0; j < certs.length(); j++){
                   JSONObject c = certs.getJSONObject(j);
                   String certid = c.getString("CertificateID");
                   String CourseId = c.getString("COURSEID");
                       LocalDate date = LocalDate.parse(c.getString("DATEISSUED"));
                       Certificate cer = new Certificate(id ,certid, CourseId, date);
                       certificates.add(cer);
                   
                   }
                  }
                  this.certificates.addAll(certificates);
                  Students.add(new Students(id, name, email, passwordHash ,salt,enrolledCourses,certificates));
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
        public  void addStudent(Students s){
        if(s  == null){
        return;
        }
        Students.add(s);
          
        }
        public  void addInstructor(Instructor i){
        if(i  == null){
        return;
        }
        instructors.add(i);
      
        
        
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
      public  Instructor containsInstructor(String id){
        id=id.trim();
        
    for(Instructor i : instructors){
        System.err.println(i.getId().trim());
    if(i.getId().trim().equals(id)){
    return i;
    }
    }
    
    return null;
    
    }
    public static adminRole  isAdmin(String email , char [] pass){
        if(admin.getEmail().equals(email)){
            byte[] hashP = hashPassword(pass, admin.getSalt());
                 if(MessageDigest.isEqual(hashP, admin.getPasswordHash())){
                 return admin;
                 }
        }
    return null;
    }
          public  Students studentEmail(String email, char [] password){
         for(Students i:Students){
             if(i.getEmail().equals(email)){
                 byte[] hashP = hashPassword(password, i.getSalt());
                 if(MessageDigest.isEqual(hashP, i.getPasswordHash()))
                 return i;
             }
         }
         return null;
     }
     public  Instructor instructorEmail(String email, char [] password){
         for(Instructor i:instructors){
             if(i.getEmail().equals(email)){
                 byte[] hashP = hashPassword(password, i.getSalt());
                 if(MessageDigest.isEqual(hashP, i.getPasswordHash()))
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
    public static Certificate containsCertificate(String certid){
    for(Certificate c : certificates){
    if(c.getCertificateId().equals(certid)){
    return c;
    }
    }
    return null;
    }
   
     @Override
    public   void SAVE(){
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
    JSONObject quizes = new JSONObject();
    

    o.put("Enrolled_Courses", Enrolled);
    
    JSONArray Certificates = new JSONArray();
    for(Certificate certf : s.getCertificatesEarned()){
    JSONObject o2 = new JSONObject();
    o2.put("CertificateID", certf.getCertificateId());
    o2.put("COURSEID", certf.getCourseId());
    o2.put("DATEISSUED", certf.getIssuedate().toString());
    Certificates.put(o2);
    }
    o.put("Certificates", Certificates);

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

        
}
