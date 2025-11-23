package Frontend;

import Backend.jsonFile;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class CustomdesignClasses {
    
   public static class ButtonCol extends JPanel{


    private final JButton approveBtn ;
    private final JButton rejectBtn;
    private String state;
    private JTable table;
    private int row;

    public void setTableAndRow(JTable table, int row){
        this.table = table;
        this.row = row;
    }

    public ButtonCol(String btn1Text , String btn2Text,Color btncolor1 , Color btncolor2){
        approveBtn  =new roundedbtn(btn1Text,15,15);
        approveBtn.setBackground(btncolor1);
          rejectBtn  =new roundedbtn(btn2Text,15,15);
          rejectBtn.setBackground(btncolor2);
        setLayout(new GridBagLayout());
        approveBtn.setActionCommand("approve");
        rejectBtn.setActionCommand("Reject");
        add(approveBtn);
        add(rejectBtn);
        ActionListener listen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // Generated from nbfs://nbhost/System/Templates/Classes/Code/GeneratedMethodBody
                state = e.getActionCommand();
                JOptionPane.showMessageDialog(null, state);
               
              
                     System.err.println(row);
                    if(state.equals("approve")){
                        String cid = AdminFrame.courses.get(row).getCourseId();
                        System.err.println(cid  + " "  + "APPROVED");
                        jsonFile.changeCourseStatus("APPROVED",cid, AdminFrame.admin);
                       
                        jsonFile.SAVE();
                    } else if(state.equals("Reject")){
                       String cid = AdminFrame.courses.get(row).getCourseId();
                        jsonFile.changeCourseStatus("REJECTED",cid, AdminFrame.admin);
                        jsonFile.SAVE();
                    }
                     AdminFrame.courses.remove(row);
                    ((DefaultTableModel)table.getModel()).removeRow(row);
               
            }
        };
        approveBtn.addActionListener(listen);
        rejectBtn.addActionListener(listen);


    } 

    public void AddActionListener(ActionListener listen){
        approveBtn.addActionListener(listen);
        rejectBtn.addActionListener(listen);
    }
    public String getState(){
        return state;
    }


 }
 public static class aproveRejectRederer extends DefaultTableCellRenderer{
private ButtonCol btnCol ;

public aproveRejectRederer( String btn1Text , String btn2Text,Color btncolor1 , Color btncolor2){


    btnCol = new ButtonCol(btn1Text, btn2Text, btncolor1, btncolor2);
}

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
          //rated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
          btnCol = new ButtonCol("APPROVE", "REJECT", Color.BLUE, Color.red);
          btnCol.setTableAndRow(table, row);
        if(isSelected){
        btnCol.setBackground(table.getSelectionBackground());
        }
        else{
        btnCol.setBackground(table.getBackground());
        }
        return btnCol;
        }



 }
  public static class btncolEditor extends AbstractCellEditor implements TableCellEditor{
private ButtonCol btnCol ;
private String btn1Text;
private String btn2Text;
private Color btncolor1;
private Color btncolor2;
public btncolEditor(String btn1Text , String btn2Text,Color btncolor1 , Color btncolor2){
this.btn1Text = btn1Text;
this.btn2Text = btn2Text;
this.btncolor1 = btncolor1;
this.btncolor2 = btncolor2;
}
@Override
public Object getCellEditorValue(){
return btnCol.getState();
}
@Override
public boolean isCellEditable(EventObject e){
return true;

}
 public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
     btnCol = new ButtonCol(btn1Text, btn2Text, btncolor1, btncolor2);
     btnCol.AddActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable(){
        @Override
        public void run(){
        stopCellEditing();
        }
        });
    }
});
     btnCol.setTableAndRow(table, row);
     if (isSelected) {
                btnCol.setBackground(table.getSelectionBackground());
            } else {
                btnCol.setBackground(table.getBackground());
            }
            return btnCol;
        }
 }

     
   
     
   
    
    public static class roundedbtn extends JButton{
private int arcW;
private int arcH;

        public roundedbtn(String text ,int arcW, int arcH) {
            super(text);
            this.arcW = arcW;
            this.arcH = arcH;
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(false);

        }
        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if(getModel().isArmed() && getModel().isEnabled()){
                g2.setColor(getBackground().darker());
            }
            else if(getModel().isRollover() && getModel().isEnabled()){
                g2.setColor(getBackground().brighter());
            }
            else{
                g2.setColor(getBackground());
            }
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcW, arcH);
            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        public boolean contains(int x, int y) {
            return new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcW, arcH).contains(x, y); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        }

        @Override
        public Dimension getPreferredSize() {
            Dimension size = super.getPreferredSize();
            return size;

        }

}
    public static class roundedlabel extends JLabel{
private int arcW;
private int arcH;

        public roundedlabel(String text ,int arcW, int arcH) {
            super(text);
            this.arcW = arcW;
            this.arcH = arcH;
           
            setOpaque(false);

        }
        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         
                g2.setColor(getBackground());
         
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, arcW, arcH));
            super.paintComponent(g2);
            g2.dispose();
        }

      
}
    
   public static class roundedins extends JTextField{
private int arcW;
private int arcH;
private Color borderColor = Color.GRAY;

        public roundedins(int arcW, int arcH) {
            
            this.arcW = arcW;
            this.arcH = arcH;
           
            setOpaque(false);

        }
        public void setBorderColor(Color color){
            this.borderColor = color;
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         
                g2.setColor(getBackground());
         
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, arcW, arcH));
            super.paintComponent(g2);
            g2.dispose();
        }
        @Override
        protected void paintBorder(Graphics g){
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcW, arcH);
            g2.dispose();
        }
}
         public static class roundedPASS extends JPasswordField{
private int arcW;
private int arcH;
private Color borderColor = Color.GRAY;

        public roundedPASS(int arcW, int arcH) {
            
            this.arcW = arcW;
            this.arcH = arcH;
           
            setOpaque(false);

        }
        public void setBorderColor(Color color){
            this.borderColor = color;
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         
                g2.setColor(getBackground());
         
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, arcW, arcH));
            super.paintComponent(g2);
            g2.dispose();
        }
        @Override
        protected void paintBorder(Graphics g){
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcW, arcH);
            g2.dispose();
        }

      
}
    
}
