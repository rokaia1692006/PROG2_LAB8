/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author LapTop
 */
public class StudentQuizProgress {
    private String courseId;
    private HashMap<String,Integer> quizScores;//to store the quiz score fe kol quiz id
    private HashMap<String,Boolean> quizCompleted;// 3ashn a3rf wich quiz is completed
    private float quizCompletionPercentage; 

    public StudentQuizProgress(String courseId) {
        this.courseId = courseId;
        this.quizScores=new HashMap<>();
        this.quizCompleted=new HashMap<>();
        this.quizCompletionPercentage=0f;//el quiz bybd2 eno 0 not taken yet 
    }

    public String getCourseId() {
        return courseId;
    }

    public HashMap<String, Integer> getQuizScores() {
        return quizScores;
    }

    public HashMap<String, Boolean> getQuizCompleted() {
        return quizCompleted;
    }

    public float getQuizCompletionPercentage() {
        return quizCompletionPercentage;
    }
   
    public void quizDone(StudentQuizAttempt a )
            
    {
        String qid = a.getQid();
        quizScores.put(qid, a.getScore());
        quizCompleted.put(qid,a.isPassed());
         updateQuizCompletion();
    
    }
    private void updateQuizCompletion() {
    int total = quizCompleted.size();
    if (total == 0) {
        quizCompletionPercentage = 0f;
        return;
    }
 List<Boolean> values = new ArrayList<>(quizCompleted.values());
        int done = 0;
    

    for (int i = 0; i < values.size(); i++) {
        if (values.get(i)) {
            done++;
        }
    }
    quizCompletionPercentage = (done * 100f) / total;
}

   
}
