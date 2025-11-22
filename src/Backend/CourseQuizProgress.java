/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author LapTop
 */
public class CourseQuizProgress {

    private String courseId;
    private HashMap<String, ArrayList<Integer>> quizScores;
    private HashMap<String, Integer> quizComplete;

    public CourseQuizProgress(String courseId) {
        this.courseId = courseId;
        this.quizComplete = new HashMap<>();
        this.quizScores = new HashMap<>();
    }

    public void addQuizScore(String quizId, int score) {
        if (!quizScores.containsKey(quizId)) {
            quizScores.put(quizId, new ArrayList<>());
        }
        quizScores.get(quizId).add(score);
    }

    public double getQuizAverage(String quizId) {
        if (!quizScores.containsKey(quizId)) {
            return 0;
        }
        ArrayList<Integer> scores = quizScores.get(quizId);
        int sum = 0;
        for (int i = 0; i < scores.size(); i++) {
            sum += scores.get(i);
        }
        return (double) sum / scores.size();
    }

    public int courseCountCompelet(String quizId)//to check kam student completed the course
    {
        return quizComplete.getOrDefault(quizId, 0);
    }

    public void updateCourseCompleteCount(String quizId)//to add student lama ya5od el quiz
    {
        int count = courseCountCompelet(quizId);
        quizComplete.put(quizId, count + 1);

    }

}
