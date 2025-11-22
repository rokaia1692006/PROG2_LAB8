/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;
import java.nio.charset.StandardCharsets;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 *
 * @author it
 */
public abstract class jsonFile {
   
   
   public static final String CFile = "courses.json";

    public static final String UFile = "users.json";
   public abstract  void  SAVE();
   public abstract void LOAD();
     }
