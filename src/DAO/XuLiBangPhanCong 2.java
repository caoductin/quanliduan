/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Database.myConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author caoductin
 */
public class XuLiBangPhanCong {
    
     public void setTableIsNull(JTable nameTable){
          // Assuming you have already created a JTable with a TableModel instance
            TableModel model = nameTable.getModel();
       
            // Loop through the rows and columns of the table and set each cell to null
            for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
                for (int colIndex = 0; colIndex < model.getColumnCount(); colIndex++) {
                    model.setValueAt(null, rowIndex, colIndex); // Set the cell to null
                }
            }
            
    
          
          
      }
    
      public String changeFormat(String dateStr){// thay đổi định dạng của ngày
           DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
           DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           LocalDate date = LocalDate.parse(dateStr, inputFormatter);
           String outputStr = outputFormatter.format(date);
             
           return outputStr;
    }
    public void LoadNhanVienVaoBangPhanCong(JTable NameTable, String CaLam ){
           
             String[] cols = { "Date", "MaNV", "TenNhanVien" };
     //  DefaultTableModel model = new DefaultTableModel(cols, 0);
      DefaultTableModel model =  (DefaultTableModel)NameTable.getModel();
       try {
           // Create a connection to MySQL database
          Connection conn = myConnection.getConnection();

           // Prepare a SQL statement to retrieve required columns' data matching the date criteria
           String sql = """
                        SELECT Hoten, NgayLam FROM quanliduan.PhanCong 
                        join quanliduan.Nhanvien on Nhanvien.MaNV = PhanCong.MaNV
                        Where PhanCong.NgayLam = ? and CaLam = ? """;
           PreparedStatement ps = conn.prepareStatement(sql);

           // Set the date parameter to match the header table dates
           for (int col = 0; col < NameTable.getColumnCount(); col++) {
               
               TableColumnModel columnModel = NameTable.getColumnModel(); 
               TableColumn column = columnModel.getColumn(col);  // Get the column at the i-th index
               Object headerValue = column.getHeaderValue();  // Get the header value for the column
               System.out.println(changeFormat((String)headerValue));  // print out the column header 
                
               ps.setString(1, changeFormat((String)headerValue));
               ps.setString(2,CaLam);
               ResultSet rs = ps.executeQuery();

               // Populate the DefaultTableModel with retrieved data
              int rowIndex = 0 ;
               while (rs.next()) { 
                   
               
//                   String date = rs.getString("Date");
//                   String manv = rs.getString("MaNV");
                   String tennv = rs.getString("Hoten");
                   
                   

                // Loop through the rows of the table and add data to the desired column
                
                   
                    model.setValueAt(tennv, rowIndex, col);
                    rowIndex++;
                
               //  model.addRow(new Object[] { null, tennv });
                  
               }
           }
           //Set the DefaultTableModel as the model for the JTable
        //jTablePhancongCaSang.setModel(model);
           // Close the database connection
           conn.close();
       } catch (Exception e) {
           e.printStackTrace();
       }

     }
    
    
    
    
    
}
