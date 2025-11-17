/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author it
 */
public class StudentProgressInCourse {
    private String CourseId;
    private int AllLessonsInCourse;
    private HashMap<String ,Boolean > lessonsDone;
    private float overallProgress;

    public StudentProgressInCourse(String CourseId, int AllLessonsInCourse, HashMap<String, Boolean>lessonsDone, float overallProgress) {
        this.CourseId = CourseId;
        this.AllLessonsInCourse = AllLessonsInCourse;
        this.lessonsDone = lessonsDone;
        this.overallProgress = overallProgress;
    }

    public StudentProgressInCourse(String CourseId, int AllLessonsInCourse ,ArrayList<String> AllLessonIDs) {
        this.CourseId = CourseId;
        this.AllLessonsInCourse = AllLessonsInCourse;
       this.lessonsDone = new HashMap<>();
        for(String id : AllLessonIDs ){
         
         this.lessonsDone.put(id,false);
        
        }
        this.AllLessonsInCourse = lessonsDone.size();
        this.overallProgress = 0f;
    }
public void markAsDone(String LessonID){
   
   if(lessonsDone.containsKey(LessonID)){
   lessonsDone.put(LessonID, true);
   updateAll();
   
   }else{
       JOptionPane.showMessageDialog(null, "ERROR");
   }
   
   


}
public void addlesson(String lessonId,String courseID){
if(CourseId.equals(courseID)){
lessonsDone.putIfAbsent(courseID, Boolean.FALSE);
AllLessonsInCourse ++;
updateAll();

}
}
private void updateAll(){
    long CountallDone = 0 ;
    for (Boolean done : lessonsDone.values()){
    if(done){
    CountallDone++;
    
    }
    
    }
    if(AllLessonsInCourse > 0 ){
    overallProgress = (CountallDone*100f)/AllLessonsInCourse;
    
    
    }
    else{
        overallProgress = 0f ;
    }
}

    public float getOverallProgress() {
        return overallProgress;
    }

    public String getCourseId() {
        return CourseId;
    }

    public HashMap<String, Boolean> getLessonsDone() {
        return lessonsDone;
    }

    public int getAllLessonsInCourse() {
        return AllLessonsInCourse;
    }
    

    
    
    
    
    
    
}
