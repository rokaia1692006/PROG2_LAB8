/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author it
 */
public class CoursesDB extends jsonFile{
     private static ArrayList<String> lids;
      private static ArrayList<Course> AllCourses;
      
      @Override
         public  void LOAD(){
      try{
      Path p =Paths.get(jsonFile.CFile);
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
          return ;
          }
      }  
      else{
          AllCourses = new ArrayList<>();
          return ;
      }
    
    }
    catch(Exception e){
        e.printStackTrace();
    }
   
    }

    public static ArrayList<String> getLids() {
        return lids;
    }

    public  ArrayList<Course> getAllCourses() {
        return AllCourses;
    }
         
         
         
         public void AddCourse(Course c){
         if(AllCourses.contains(c)){
         JOptionPane.showMessageDialog(null, "COURSE ALREADY EXISTS");
         return;
         }
         AllCourses.add(c);
         
         
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

        @Override
        public  void SAVE(){
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
     
                       
}
