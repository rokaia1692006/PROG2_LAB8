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
public class Quiz {
    private ArrayList<Question> questions;
    private int passScore;
    
    public void addQuestion(Question q){
        
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
        this.questions = questions;
        this.passScore = passScore;
    }
}
