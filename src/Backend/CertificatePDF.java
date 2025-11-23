/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author DELL
 */
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;



public class CertificatePDF {
    
    private String certificateId;
    private String studentId;
    private String courseId;
    private String issueDate;
    
    
    public CertificatePDF(String certificateId, String studentId, String courseId, String issueDate) {
        this.certificateId = certificateId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.issueDate = issueDate;
    }
    
    public void generatePDF(String filePath)
    {
        try {
            Document document = new Document() {}; 
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
           
           document.open();

            document.add(new Paragraph("Certificate Id: " + certificateId));
            document.add(new Paragraph("Issue Date: " + issueDate)); //ka string mesh date
            document.add(new Paragraph("Student Id: " + studentId));
            document.add(new Paragraph("Course ID: " + courseId));
           
            document.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR IN GENERATING PDF!!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getCertificateId() {
        return certificateId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getIssueDate() {
        return issueDate;
    }
    
    
    }
    

