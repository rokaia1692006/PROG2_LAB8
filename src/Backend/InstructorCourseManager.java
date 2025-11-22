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
public class InstructorCourseManager {

  private jsonFile db;

    public InstructorCourseManager(jsonFile db) {
        this.db = db;
    }

    public Course createCourse(String title, String description, Instructor instructor) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("title can't be empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("description can't be empty");
        }
       
        Course newCourse = new Course( title, description, instructor);
        db.CreateCourse(instructor.getId(),title,description);
//        instructor.addCourse(newCourse.getCourseId());
//        db.updateInstructor(instructor);
        return newCourse;
    }

    public void editCourse(String courseId, String newTitle, String newDescription, Instructor instructor) {

        Course course = db.containsCourse(courseId);
        if (!course.getInstructorId().equals(instructor.getId())) {
            throw new IllegalArgumentException("You are not allowed to edit this course.");
        }
 if (newTitle == null ||  newTitle.trim().isEmpty()) {
      newTitle = course.getTitle();
     }
        if (newDescription == null || newDescription.trim().isEmpty()) {
            newDescription  = course.getDescription();
    }
//        db.saveCourse(course);
db.updatecourse(instructor.getId(),courseId,newTitle,newDescription);
    }

    public void deleteCourse(String courseId, Instructor instructor) {

        Course course = db.containsCourse(courseId);
        if (!course.getInstructorId().equals(instructor.getId())) {
            throw new IllegalArgumentException("course can't be deleted");
        }
        db.DeleteCourse(courseId,instructor.getId());
    }

}
