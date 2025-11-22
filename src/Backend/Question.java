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
public class Question {
        private String question;
        private ArrayList<String> options;
        private int correct;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public ArrayList<String> getOptions() {
            return options;
        }

        public void setOptions(ArrayList<String> options) {
            this.options = options;
        }

        public int getCorrect() {
            return correct;
        }

        public void setCorrect(int correct) {
            this.correct = correct;
        }

        public Question(String question, ArrayList<String> options, int correct) {
            this.question = question;
            this.options = options;
            this.correct = correct;
        }
}
