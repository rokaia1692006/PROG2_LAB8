/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.HashMap;

/**
 *
 * @author LapTop
 */
public class QuizManager {

    private HashMap<String, CourseQuizProgress> courseProgress;
    private HashMap<String, StudentQuizProgress> studentProgress;

    public QuizManager() {
        this.courseProgress = new HashMap<>();
        this.studentProgress = new HashMap<>();
    }

    public HashMap<String, CourseQuizProgress> getCourseProgress() {
        return courseProgress;
    }

    public HashMap<String, StudentQuizProgress> getStudentProgress() {
        return studentProgress;
    }

    public void addStudent(String courseId, String studentId)//adding student 3ashn a3rf a track progress fel course w student
    {
        studentProgress.put(studentId, new StudentQuizProgress(courseId));
        courseProgress.putIfAbsent(courseId, new CourseQuizProgress(courseId));
    }

    public void studentCompleteQuiz(String studentId, String quizId, int score) {
        StudentQuizProgress stprog = studentProgress.get(studentId);
        if (stprog != null) {
            stprog.quizDone(quizId, score);
            String courseId = stprog.getCourseId();
            CourseQuizProgress courseProg = courseProgress.get(courseId);
            if (courseProg != null) {
                courseProg.addQuizScore(quizId, score);
                courseProg.updateCourseCompleteCount(quizId);
            }
        }
    }

    public float getStudentCompletionPercentage(String studentId) {
        StudentQuizProgress stProg = studentProgress.get(studentId);
        if (stProg != null) {
            return stProg.getQuizCompletionPercentage();
        }
        return 0f;
    }

    public double getCourseQuizAvrg(String courseId, String quizId) {
        CourseQuizProgress courseProg = courseProgress.get(courseId);
        if (courseProg != null) {
            return courseProg.getQuizAverage(quizId);
        }
        return 0;

    }

    public int getCourseCompleteCount(String courseId, String quizId) {
        CourseQuizProgress courseProg = courseProgress.get(courseId);
        if (courseProg != null) {
            return courseProg.courseCountCompelet(quizId);
        }
        return 0;
    }

}
