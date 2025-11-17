/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Backend;


import javax.swing.JOptionPane;


public abstract class Person {
    
    
    private String fullName;
//    private int age;
//    private String gender;
    
    
    public Person(String fullName) {
        
        setFullName(fullName);
//        setAge(age);
//        setGender(gender);
        
    }
    
    public static boolean validName ( String fullName )
    {
        fullName = fullName.trim();
        
        if (fullName.length() < 2 || fullName.length() > 150 )
        {
            JOptionPane.showMessageDialog(null,"Too short name, or too long!","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }  //lw el name osyr awe hrfyn harf, aw tawel over, yeb2a ignore. 
        
        String regex = "^[A-Za-z]+$";
        String[] line = fullName.split(" ");
        
        for (int i = 0 ; i < line.length; i++)
        {
            if ( !line[i].matches(regex) || line[i].isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Invalid part of name, make sure only letters!","Error",JOptionPane.ERROR_MESSAGE);
                 return false;
            }
               
        }
       
        return true;
    }
    
    
    public void setFullName(String fullName) {
        
        if (validName (fullName))
        {
            this.fullName = fullName;
        }
        
    }
    
    
//    public static boolean validAge ( int age )
//    {
//        if (age < 1 || age > 90)
//        {
//            JOptionPane.showMessageDialog(null,"Error Iinvalid range of age!","Error",JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        else{
//          
//              return true;
//        }
//      
//    }
//    public void setAge(int age) {
//        
//        if (validAge(age))
//        {
//            this.age = age;
//            
//        }
//    }
//
//    public void setGender(String gender) {
//        
//        if (!gender.isEmpty())
//        {this.gender = gender;
//         
//        }
//        else{
//             JOptionPane.showMessageDialog(null,"Error gender entering error..","Error",JOptionPane.ERROR_MESSAGE);
//        }
//    }


    public String getFullName() {
        return fullName;
    }

//    public int getAge() {
//        return age;
//    }
//
//    public String getGender() {
//        return gender;
//    }
    
}
