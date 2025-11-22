/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;
import static Backend.CoursesDB.containsCourse;
import java.awt.FileDialog;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author it
 */
public abstract class DBMANAGER {
    
    public static final String CFile = "courses.json";

    public static final String UFile = "users.json";
   public abstract  void  SAVE();
   public abstract void LOAD();
   
   
   
  

}
