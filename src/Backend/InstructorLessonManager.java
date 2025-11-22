/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author LapTop
 */
public class InstructorLessonManager {
    private jsonFile db;

    public InstructorLessonManager(jsonFile db) {
        this.db = db;
    }

  
    public Lesson addLesson(String courseId, String title, String content ,ArrayList<String> resourcesOptional, Instructor instructor) {

        Course course = db.containsCourse(courseId);
        if (!course.getInstructorId().equals(instructor.getId())) {
            throw new IllegalArgumentException("Can't add lessons to this course!!");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title can't be empty!");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content can't be empty!");
        }
       if(resourcesOptional  == null){
       resourcesOptional = new ArrayList<>();
       }
        Lesson lesson = new Lesson( title, content,resourcesOptional);
       course.addLesson(lesson, instructor);
       IncreaseNumberOfLessons(courseId,lesson.getLessonID());
        return lesson;
    }
   
     private void IncreaseNumberOfLessons(String lessonid , String courseId){
     ArrayList<Students> st = db.getAllStudentinCourse(courseId);
     if(st.isEmpty() || st == null){
     return;
     }
     else{
     for(Students s : st){
     s.NewLesson(courseId, lessonid);
     
     }
     }
     }

    public void editLesson(String courseId, String lessonId, String newTitle, String newContent, Instructor instructor) {
        Course course = db.containsCourse(courseId);//to search for thr code
        if (!course.getInstructorId().equals(instructor.getId())) //to be sure en el instructor owns the course
        {
            throw new IllegalArgumentException("can't edit this lesson!");
        }
        Lesson lessonwanted = null;
        for (int i = 0; i < course.getLessons().size(); i++) {
            Lesson l = course.getLessons().get(i);
            if (l.getLessonID().equals(lessonId)) {
                lessonwanted = l;
                break;
            }
        }
        if (lessonwanted == null) {
            throw new IllegalArgumentException("lesson not found!!");
        }
        if (newTitle != null && !newTitle.trim().isEmpty()) {
            lessonwanted.setTitle(newTitle);
        }
        if (newContent != null && !newContent.trim().isEmpty()) {
            lessonwanted.setContent(newContent);
        }
        db.SAVE();
    }

    public void deleteLesson(String courseId, String lessonId, Instructor instructor) {

        Course course = db.containsCourse(courseId);

        if (!course.getInstructorId().equals(instructor.getId())) {
            throw new IllegalArgumentException("can't delete this lesson!");
        }
        for (int i = 0; i < course.getLessons().size(); i++) {
            Lesson l = course.getLessons().get(i);
            if (l.getLessonID().equals(lessonId)) {
                course.getLessons().remove(i);
                i--;
            }
        }
    db.SAVE();
    }
}
