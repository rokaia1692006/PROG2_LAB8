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
public class AssessmentManager {
    QuizManager manager = new QuizManager();
    public AssessmentManager(QuizManager manager){
        this.manager=manager;
    }
    public Quiz createQuiz(ArrayList<Question> questions, int passScore){
        if(questions==null)
            throw new IllegalArgumentException("No questions entered.");
        for(int i=0;i<questions.size();i++){
            if(questions.get(i)==null||questions.get(i).getQuestion().isEmpty())
                throw new IllegalArgumentException("Question can't be blank.");
        }
        if(passScore==0)
            throw new IllegalArgumentException("No passing score entered.");
        Quiz newQuiz=new Quiz(questions, passScore);
        return newQuiz;
    }
    public StudentQuizAttempt submitQuiz(String studentId, String lessonId, Quiz quiz, ArrayList<Integer> answers){
        StudentQuizAttempt attempt=new StudentQuizAttempt(studentId, lessonId, quiz, answers);
        if(attempt.isPassed()){
            manager.studentCompleteQuiz(attempt);
        }
        return attempt;
    }
//    public boolean canAccessNextLesson(String studenId, String lessonId){
//        
//    }
}
