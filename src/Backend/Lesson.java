/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author it
 */
public class Lesson {
    private final String lessonID;
    private String title;
    private String content;
    private ArrayList<String> resources;

    public Lesson(String title, String content, ArrayList resources) {
        this.lessonID  = generate.LessonID();
        this.title = title;
        this.content = content;
        this.resources = resources;
    }

    public String getLessonID() {
        return lessonID;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<String> getResources() {
        return resources;
    }

    public void setResources(ArrayList<String> resources) {
        this.resources = resources;
    }

   
    
    
}
