/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author malak
 */
public class StudentQuizAttempt {
    private final String studentId;
    private final String lessonId;
    private int score;
    private boolean passed;
    private ArrayList<Integer> answers;
    private Quiz quiz;
    
    public StudentQuizAttempt(String studentId, String lessonId, Quiz quiz, ArrayList<Integer> answers) {
        this.studentId = studentId;
        this.lessonId = lessonId;
        this.quiz = quiz;
        this.answers = answers;
        this.score = quiz.calculateScore(answers);
        this.passed = quiz.hasPassed(this.score);
    }

    public String getStudentId() {
        return studentId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public int getScore() {
        return score;
    }

    public boolean isPassed() {
        return passed;
    }
    
}
