/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author LapTop
 */
public class InstructorCourseManager {
    
     private JsonDatabaseManager db;

    public InstructorCourseManager(JsonDatabaseManager db) {
        this.db = db;
    }
    public Course createCourse(String title,String description,Instructor instructor)
    {
    if(title==null||title.trim().isEmpty())
    {
    throw new IllegalArgumentException("title can't be empty");
    }if(description==null||description.trim().isEmpty())
    {
    throw new IllegalArgumentException("description can't be empty");
    }
    String courseId = db.generateCourseId();
    Course newCourse = new Course(courseId, title, description, instructor.getId());
    db.saveCourse(newCourse);
    instructor.addCourse(courseId);
    db.updateInstructor(instructor);
    return newCourse;
    }
      public void editCourse(String courseId, String newTitle, String newDescription, Instructor instructor) {

        Course course=db.getCourseById(courseId);
        if(!course.getInstructorId().equals(instructor.getId())) 
        {
            throw new IllegalArgumentException("You are not allowed to edit this course.");
        }
        if(newTitle!=null&&!newTitle.trim().isEmpty()) {
            course.setTitle(newTitle);
        }
        if (newDescription!=null&&!newDescription.trim().isEmpty()) {
            course.setDescription(newDescription);
        }
        db.saveCourse(course);
    }
      public void deleteCourse(String courseId, Instructor instructor) {

        Course course = db.getCourseById(courseId);
        if (!course.getInstructorId().equals(instructor.getId())) {
            throw new IllegalArgumentException("course can't be deleted");
        }
        instructor.removeCourse(courseId);
        db.updateInstructor(instructor);
        db.deleteCourse(courseId);
    }
     
}
