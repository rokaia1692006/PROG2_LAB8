/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frontend;

import Backend.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 *
 * @author malak
 */
public class test {
    public static void main(String[] args) {
        // 1. Initialize jsonFile (loads DB)
        jsonFile db = new jsonFile();

        // 2. Create and register a dummy instructor
        Instructor ins = new Instructor("John Doe", "john@example.com", new byte[0], new byte[0]);
        jsonFile.addInstructor(ins); // <-- register instructor in UsersDB

        // 3. Create a course for this instructor
        Course course = jsonFile.CreateCourse(ins.getId(), "Test Course", "This is a test course");
        ins.addCourse(course.getCourseId());

        // 4. Launch the dashboard
        SwingUtilities.invokeLater(() -> {
            InstructorDashboard dash = new InstructorDashboard(ins);
            dash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dash.setVisible(true);
        });
    }
}
