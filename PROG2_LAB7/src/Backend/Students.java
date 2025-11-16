
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Backend;


import java.util.Random;
import Backend.Person;


import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */

public class Students extends Person {
    
    private int id;
    private String department;
    private float gpa;
    private static StudentManager database = new StudentManager();
  
    
    public Students(int id,String fullName,int age,String gender,String department,float gpa) {
        super(fullName, age, gender); 
        setID(id);
        setDepartment(department);
        setGpa(gpa);
        
    }
    
   
    public static int generateID()
    {
        Random autom = new Random(); //method gowa java bt-generate random integers w hahot el limit
        
        int newID;
        while(true)
        {
            newID = autom.nextInt(9000) + 1000;
            if (database.searchStudent(String.valueOf(newID)) == null)
                return newID; //y3ne hwa mesh mwgod fel database, fa tmam generated!
        }
        
       
    }
    
    
    public static boolean handlingID(int id)
    {
//        if (id == 1) //the user wants to generate an ID
//        {
//            int newID = generateID();
//            int confirm = JOptionPane.showConfirmDialog(null, "This is your generated ID: " + newID + ". Accept?",
//                "Confirm ID",
//                JOptionPane.YES_NO_OPTION);
//            if (confirm == JOptionPane.YES_OPTION){
//                 JOptionPane.showMessageDialog(null,"Successfully generated an ID!");
//                 return true;
//            }
//            else {
//                JOptionPane.showMessageDialog(null, "Generation of ID failed. Try again please!","Error",JOptionPane.ERROR_MESSAGE);
//                return false;
//            }
//            
//        }
//        else {  //el user hwa eli dkhal ID
            if (!(database.searchStudent(String.valueOf(id)) == null)) 
            {
                JOptionPane.showMessageDialog(null,"Error ID already exists!","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else{
                if ( id < 1000 || id > 10000)
                {
                     JOptionPane.showMessageDialog(null,"Error ID should be between 1000-10,000","Error",JOptionPane.ERROR_MESSAGE);
                     return false;
                }
                else{
                  
//                 JOptionPane.showMessageDialog(null,"Valid entered ID!");
                 return true;
                }
           
        }
    }
    
    
    
    public void setID(int id) {
       
        
            this.id = id;
       
    }

    
    public void setDepartment(String department) {
        if (!department.isEmpty())
        {this.department = department;
         }
        else{
             JOptionPane.showMessageDialog(null,"Error in storing the department","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static boolean validGPA(float gpa)
    {
        if (gpa < 0.0 || gpa > 4.0 )
        {
            JOptionPane.showMessageDialog(null,"Error! GPA should be between 0.0 - 4.0","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else{
            
            return true;
        }
    }
    
    
    public void setGpa(float gpa) {
        if (validGPA(gpa))
        {
            this.gpa=gpa;
            
        }
        
    }
    public String lineRepresentation() {
        return this.getID()+","+this.getFullName()+","+this.getAge()+","+this.getGender()+","+this.getDepartment()+","+this.getGpa();
    }
    public int getID() {
        return id;
    }
    public String getDepartment() {
        return department;
    }

    public float getGpa() {
        return gpa;
    }

    
}
