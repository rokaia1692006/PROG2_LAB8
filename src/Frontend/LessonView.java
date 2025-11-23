/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frontend;
import Backend.Lesson;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author it
 */
public class LessonView  extends javax.swing.JPanel {
    private Lesson  l;
    private  javax.swing.JFrame MainFrame;
    public LessonView(Lesson l,  javax.swing.JFrame MainFrame) {
        this.l = l;
        this.MainFrame = MainFrame;
        initComponents();}
    private void initComponents(){
    
   setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(l.getTitle());
        JTextArea contentArea = new JTextArea(l.getContent());
        contentArea.setEditable(false);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(ae -> {
            javax.swing.SwingUtilities.getWindowAncestor(this).dispose();
        });
        
        
        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(contentArea), BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);}
}

