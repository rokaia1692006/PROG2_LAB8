/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author malak
 */
public class Quiz {
    private String Quizid;
    private ArrayList<Question> questions;
    private int passScore;

    public Quiz(String Quizid, ArrayList<Question> questions, int passScore) {
        this.Quizid = Quizid;
        this.questions = questions;
        this.passScore = passScore;
    }
    
    public void addQuestion(Question q){
        if(q.getQuestion()==null||q.getQuestion().isEmpty()){
            JOptionPane.showMessageDialog(null, "Can't add empty question");
            return; //returns when question is invalid, stops execution
        }
        if(questions==null){
            questions=new ArrayList<>();
        }
        questions.add(q);
    }
    public int calculateScore(ArrayList<Integer> answers){
        int score=0;
        for(int i=0;i<questions.size();i++){
            if(questions.get(i).isCorrect(answers.get(i)))
                score++;
        }
        return score;
    }

    public String getQuizid() {
        return Quizid;
    }

   
    
    public boolean hasPassed(int score){
        return score>=passScore;
    }
    public boolean isPassed(){
        return this.hasPassed(passScore);
    }
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getPassScore() {
        return passScore;
    }

    public void setPassScore(int passScore) {
        this.passScore = passScore;
    }

    public Quiz(ArrayList<Question> questions, int passScore) {
        this.Quizid  = generate.QuizID();
        this.questions = questions;
        this.passScore = passScore;
    }
}
