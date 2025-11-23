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
public class CoursesDB extends DBMANAGER{
     private static ArrayList<String> lids;
      private static ArrayList<Course> AllCourses;
private static ArrayList<Quiz> AllQuizs;
    public CoursesDB() {
        lids=   new ArrayList<>()  ;
        AllCourses = new ArrayList<>();
        AllQuizs = new ArrayList<>();
    }
      
      @Override
         public  void LOAD(){
             
      try{
          
      Path p =Paths.get(DBMANAGER.CFile);
        lids=   new ArrayList<>()  ;
        AllCourses = new ArrayList<>();
        AllQuizs = new ArrayList<>();
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
          Instructor ins = jsonFile.containsInstructor(InstructorID);
          if(ins == null){
              JOptionPane.showMessageDialog(null, InstructorID);
              JOptionPane.showMessageDialog(null, "FILE ERROR");
              return ;
          }
          String Status = course.getString("STATUS").trim();
          Course c;
          if(course.has("ID")){
          String cid = course.getString("ID");
          c = new Course(cid,title, dis, ins,Status);
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

              if(l.has("Quiz")){
                  JSONObject qobj = l.getJSONObject("Quiz");
                  String quizId = qobj.getString("QuizID");
                  int passScore = qobj.getInt("PassScore");
                  ArrayList<Question> questions = new ArrayList<>();
                  JSONArray QeustionsArr = qobj.getJSONArray("Questions");
                  for(int qsi = 0; qsi < QeustionsArr.length(); qsi++){
                  JSONObject object = QeustionsArr.getJSONObject(qsi);
                  String questionText = object.getString("QuestionText");
                  JSONArray opts = object.getJSONArray("Choices");
                  ArrayList<String> Choices = new ArrayList<>();
                  for(int j = 0; j < opts.length(); j++){
                    Choices.add(opts.getString(j));
                    }
                    int correctIndex = object.getInt("Correct");
                    String[] ch = Choices.toArray(new String[0]);
                    questions.add(new Question(questionText, ch, correctIndex));
                  }
                  Quiz quiz = new Quiz(quizId, questions, passScore);
                  ltenp.setQuiz(quiz);
                  AllQuizs.add(quiz);
              }

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
     public  Course  CreateCourse (UsersDB udb,CoursesDB cdb,String insID, String title, String description){
             Instructor ins = udb.containsInstructor(insID);
             if(ins  == null){
             JOptionPane.showMessageDialog(null, "NO INSTRUCTOR");
             return null;
             
             
             }
             Course c = new Course(title,description,ins);
             ins.addCourse(c.getCourseId());
             cdb.AddCourse(c);
             
           
             return c;
        
        }
    
    public  void updatecourse (UsersDB udb,CoursesDB cdb,String insID, String CId , String title, String description){
              Instructor ins = udb.containsInstructor(insID);
              Course c= cdb.containsCourse(CId);
             if(ins  == null || c == null || !insID.equals(c.getInstructorId())){
             JOptionPane.showMessageDialog(null, "NO INSTRUCTOR");
             return;
             
             
             }
             c.UpdateValues(title, description);
          
             
         }
         
        public  static void DeleteCourse(UsersDB udb,CoursesDB cdb,String cId , String insID){
         Instructor ins = udb.containsInstructor(insID);
              Course c= cdb.containsCourse(cId);
              ArrayList<Students> s = jsonFile.getAllStudentinCourse(cId);
              
             if(ins  == null || c == null || !insID.equals(c.getInstructorId())){
             JOptionPane.showMessageDialog(null, "CANNOT DELETE");
             return;
             
             }
            AllCourses.remove(c);
             ins.removeCourse(cId);
             if(s  == null){
             return;
             }
             for(Students student : s ){
             student.removeCourse(cId);
             }
             
           
             
         
        }

    public  ArrayList<Course> getAllApprovedCourses() {
        ArrayList<Course> a = new ArrayList<>();
        for(Course c : AllCourses){
            if(c.getStatus().equalsIgnoreCase("approved")){
            a.add(c);
            }
        }
        return a;
    }
         
         
         
         public void AddCourse(Course c){
         if(AllCourses.contains(c)){
         JOptionPane.showMessageDialog(null, "COURSE ALREADY EXISTS");
         return;
         }
         AllCourses.add(c);
         
         
         }
         public ArrayList<Quiz> getAllQuizzes(){
         return AllQuizs;
         
         }
         public ArrayList<Quiz> getQuizsinCourse(String cid){
             ArrayList<Quiz>qs = new  ArrayList<>();
             Course c = containsCourse(cid);
             ArrayList<Lesson> l = c.getLessons();
         if(cid!=null){
             for(Lesson l1 : l ){
                 String qid = l1.getQuiz().getQuizid();
            
         for(Quiz q : AllQuizs){
             if(qid.equals(q.getQuizid())){
             qs.add(q);
             break;
             }
         }
            
             
         
         }
         
         }
         return qs;
         }
         public static Quiz containsQuiz(String id){
         id=id.trim();
    for(Quiz i : AllQuizs){
    if(i.getQuizid().equals(id)){
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
//          public static void removeCourse(String id){
//          Course c = containsCourse(id);
//          if(c==null){
//              JOptionPane.showMessageDialog(null,"COURSE DOESNT EXIST");
//              return;
//          }
//          AllCourses.remove(c);
//          }
          
          public static void changeCourseStatus(String Status,String cid , adminRole a){
         
          CoursesDB.containsCourse(cid).setStatus(Status, a);
         
         
          
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
        o.put("STATUS", c.getStatus());
        JSONArray ls = new JSONArray();
        
        
        for(Lesson l : c.getLessons()){
        JSONObject lo = new JSONObject();
        lo.put("IDS", l.getLessonID());
        lo.put("Title", l.getTitle());
        lo.put("Content", l.getContent());
        lo.put("Resources", new JSONArray(l.getResources()));
        if(l.getQuiz() != null){
        Quiz q = l.getQuiz();
        JSONObject qobject = new JSONObject();
        qobject.put("QuizID", q.getQuizid());
        qobject.put("PassScore", q.getPassScore());
        JSONArray QeustionsArr = new JSONArray();
        for(Question qs : q.getQuestions()){
        JSONObject object = new JSONObject();
        object.put("QuestionText", qs.getQuestion());
        object.put("Choices", new JSONArray(qs.getOptions()));
        object.put("Correct", qs.getCorrect());
            QeustionsArr.put(object);
            }
        qobject.put("Questions", QeustionsArr);
        lo.put("Quiz", qobject);
        }

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
     public ArrayList<Course> getPendingCourses(){
         ArrayList <Course> p = new ArrayList<>();
         for(Course c : AllCourses){
         if(c.getStatus().equalsIgnoreCase("pending")){
         p.add(c);
         }
         }
         return p;
     
     }
    

    public static ArrayList<Course> getAllCourses() {
        return AllCourses;
    }
                       
}
