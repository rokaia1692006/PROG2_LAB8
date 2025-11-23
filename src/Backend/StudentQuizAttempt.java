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
    private final String Qid;
    
    public StudentQuizAttempt(String studentId, String lessonId, Quiz quiz, ArrayList<Integer> answers) {
        this.studentId = studentId;
        this.lessonId = lessonId;
        this.Qid = quiz.getQuizid();
        this.answers = answers;
        this.score = quiz.calculateScore(answers);
        this.passed = quiz.hasPassed(this.score);
    }

    public StudentQuizAttempt(String studentId, String lessonId, int score, boolean passed, ArrayList<Integer> answers, String Qid) {
        this.studentId = studentId;
        this.lessonId = lessonId;
        this.score = score;
        this.passed = passed;
        this.answers = answers;
        this.Qid = Qid;
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

    public ArrayList<Integer> getAnswers() {
        return answers;
    }

    public String getQid() {
        return Qid;
    }
    
}
