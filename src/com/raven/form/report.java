/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

/**
 *
 * @author sachi
 */
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;



public class report extends javax.swing.JPanel
        

{

    /**
     * Creates new form report
     */
    public report() {
        initComponents();
          table_update();
    }
    
    Connection con1;
    PreparedStatement pst;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
     private void initComponent() {
        // ... Your existing initComponents code ...

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "C_Name", "F_Name", "Mobile", "DOB", "Gender", "BR nb", "Section", "Issue Date", "NIC", "Email", "Address"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 560));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Generate Report to Excel");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 560, 470, 50));
    }// </editor-fold>//GEN-END:initComponents
      private void table_update()
    {
        try {
            int c;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con1 =DriverManager.getConnection("jdbc:mysql://localhost:3306/simple_student","root","");
                pst=con1.prepareStatement("select * from student");
                ResultSet rs=pst.executeQuery();
                
                ResultSetMetaData rsd=rs.getMetaData();
                c=rsd.getColumnCount();
                
                DefaultTableModel d=(DefaultTableModel)jTable1.getModel();
                d.setRowCount(0);
                
               while(rs.next()) 
               {
                   Vector v2 = new Vector();
                   
                   for(int i=1; i<=c; i++)
                   {
                       v2.add(rs.getString("id"));
                        v2.add(rs.getString("studentname"));
                        v2.add(rs.getString("fathername"));
                        v2.add(rs.getString("phone"));
                        v2.add(rs.getString("dateofbirth"));
                        v2.add(rs.getString("gender"));
                        v2.add(rs.getString("class"));
                        v2.add(rs.getString("section"));
                        v2.add(rs.getString("admissiondate"));
                        v2.add(rs.getString("cnic"));
                        v2.add(rs.getString("email"));
                        v2.add(rs.getString("address"));
                   
                   
                   }
                     d.addRow(v2);
               }  
                   

                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(report.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            
        } catch (SQLException ex) {
            Logger.getLogger(report.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      
      
   
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
         String path = "";
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int x = jFileChooser.showSaveDialog(this);

    if (x == JFileChooser.APPROVE_OPTION) {
        path = jFileChooser.getSelectedFile().getPath();
    }

    try {
        Workbook workbook = new HSSFWorkbook(); // Create a new Excel workbook
        Sheet sheet = workbook.createSheet("Student Report"); // Create a new sheet

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount();

        // Create cell style for header
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font headerFont = workbook.createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerCellStyle.setFont(headerFont);

        // Create header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < model.getColumnCount(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(model.getColumnName(i));
            cell.setCellStyle(headerCellStyle);
        }

        // Create data rows
        for (int i = 0; i < rowCount; i++) {
            Row dataRow = sheet.createRow(i + 1);
            for (int col = 0; col < model.getColumnCount(); col++) {
                dataRow.createCell(col).setCellValue(model.getValueAt(i, col).toString());
            }
        }

        // Auto-size columns
        for (int col = 0; col < model.getColumnCount(); col++) {
            sheet.autoSizeColumn(col);
        }
 
        // Save the workbook as an Excel file
        try (FileOutputStream fileOut = new FileOutputStream(new File(path + "summary.xls"))) {
            workbook.write(fileOut);
            fileOut.close();
        }

        JOptionPane.showMessageDialog(null, "Download Excel successful");
    } catch (Exception ex) {
        Logger.getLogger(report.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
