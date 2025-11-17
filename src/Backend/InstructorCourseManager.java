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
        
        instructor.addCourse(newCourse.getCourseId());
        db.updateInstructor(instructor);
        return newCourse;
    }

    public void editCourse(String courseId, String newTitle, String newDescription, Instructor instructor) {

        Course course = db.getCourseById(courseId);
        if (!course.getInstructorId().equals(instructor.getId())) {
            throw new IllegalArgumentException("You are not allowed to edit this course.");
        }
        if (newTitle != null && !newTitle.trim().isEmpty()) {
            course.setTitle(newTitle);
        }
        if (newDescription != null && !newDescription.trim().isEmpty()) {
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

    public Lesson addLesson(String courseId, String title, String content, Instructor instructor) {

        Course course = db.getCourseById(courseId);
        if (!course.getInstructorId().equals(instructor.getId())) {
            throw new IllegalArgumentException("can't add lessons to this course!!");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("title can't be empty!");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("content can't be empty!");
        }
        String lessonId = db.generateLessonId();
        Lesson lesson = new Lesson(lessonId, title, content);
        course.getLessons().add(lesson);
        db.saveCourse(course);
        return lesson;
    }

    public void editLesson(String courseId, String lessonId, String newTitle, String newContent, Instructor instructor) {
        Course course = db.getCourseById(courseId);//to search for thr code
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
        db.saveCourse(course);
    }

    public void deleteLesson(String courseId, String lessonId, Instructor instructor) {

        Course course = db.getCourseById(courseId);

        if (!course.getInstructorId().equals(instructor.getId())) {
            throw new IllegalArgumentException("can't delete this lesson!");
        }
        for (int i = 0; i < course.getLessons().size(); i++) {
            Lesson l = course.getLessons().get(i);
            if (l.getLessonId().equals(lessonId)) {
                course.getLessons().remove(i);
                i--;
            }
        }
    db.saveCourse(course);
    }
}
