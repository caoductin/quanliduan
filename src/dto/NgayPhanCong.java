/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author caoductin
 */

import Database.myConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class NgayPhanCong {

            
    public String NhanVien;
        
    
    
    
    public  List<String> create7Days(LocalDate a) {
        // Set the start date
        LocalDate startDate = a;

        // Create a list to store the next 7 days
        List<String> next7Days = new ArrayList<>();

        // Format the date as "dd-MM-yyyy" and add it to the list
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        next7Days.add(startDate.format(formatter));

     //    Add the next 6 days to the list
        for (int i = 1; i < 7; i++) {
            startDate = startDate.plusDays(1);
            next7Days.add(startDate.format(formatter));
        }
//        
        // Print the next 7 days
//        System.out.println("Next 7 days:");
//        for (String day : next7Days) {
//            System.out.println(day);
//        }
        return next7Days;
    }

    public void getNhanvien(JTable table ,String name, int row ,int col){
          
           
        
    }
    
    
    
    
    
    
    public void setTheTitleOfTable(LocalDate a,JTable jtable){
//        for(int i=0;i<7;i++){
//             jtable.getColumnModel().getColumn(i).setHeaderValue("");
//        }
        List<String> days = this.create7Days(a);
        for(int i=0;i<7;i++){
             jtable.getColumnModel().getColumn(i).setHeaderValue(days.get(i));
        }
        jtable.getTableHeader().repaint();// hàm này giúp update UI ngày lập tức cho header table
        
    }
    public String changeFormat(String dateStr){// thay đổi định dạng của ngày
           DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
           DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           LocalDate date = LocalDate.parse(dateStr, inputFormatter);
           String outputStr = outputFormatter.format(date);
             
           return outputStr;
    }
    
    
    public boolean InsertPhanCong(String MaNV,String CaLam,String NgayLam,int NumberOfRows){
        String ngay = this.changeFormat(NgayLam);
        PreparedStatement ps;
        ResultSet rs;
        boolean check = true;
    
    try {
            // establish database connection
           Connection con = myConnection.getConnection();


            // create a PreparedStatement to count the rows in the table
          //  String sql = "INSERT INTO `PhanCong` (`MaNV`, `CaLam`, `NgayLam`"+ " VALUES (?, ?,?)";
              String sql = "INSERT INTO `PhanCong` (`MaNV`, `CaLam`, `NgayLam`) VALUES (?, ?, ?)";

            ps = con.prepareStatement(sql);
            
            ps.setString(1, MaNV);
            ps.setString(2,CaLam);
            ps.setString(3,ngay);
            
            int numRowsAffected = ps.executeUpdate();
            if (numRowsAffected > 0) {
               System.out.println("Insert succeeded.");
               check = true;
                } else {
     System.out.println("Insert failed.");
     check = false;
}

            
} catch (Exception ex) {
                  JOptionPane.showMessageDialog( null,"MySQL error: " + ex.getMessage(), "MySQL Error", JOptionPane.ERROR_MESSAGE);
                  check =  false;
                
    } 
    return check;
}
    
    
    
    
    public int countTheEmployee(){
         PreparedStatement ps;
         int rowCount = 0;
        ResultSet rs;
        try {
    // establish database connection
   Connection con = myConnection.getConnection();

    
    // create a PreparedStatement to count the rows in the table
    String sql = "SELECT COUNT(*) AS row_count FROM Nhanvien";
    ps = con.prepareStatement(sql);
    
    // execute the query and retrieve the row count
    rs = ps.executeQuery();
    if (rs.next()) {
        rowCount = rs.getInt("row_count");
    }
    System.out.println(rowCount);
} catch (Exception ex) {
    System.out.println(ex);
} 
        
        
     return rowCount;   
    }

    
//    public static void main(String[] args) {
//        LocalDate b = LocalDate.of(2003, 2, 2);
//        List<String> days = create7Days(b);
//        for (String dayOfWeek : days) {
//            System.out.println(dayOfWeek);
//        }
//    }
}
//
//
//import java.util.Calendar;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import javax.swing.JTable;
//
//public class NgayPhanCong {
//
//   public static void main(String[] args) {
//      
//      // Create a calendar object and set it to November 1, 2022
//      Calendar calendar = Calendar.getInstance();
//      calendar.set(2022, Calendar.NOVEMBER, 1);
//      
//      // Create a DateFormat object to format the date strings
//      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//      
//      // Use a loop to create the date range of 7 days
//      String[] dates = new String[7];
//      for (int i = 0; i < 7; i++) {
//         dates[i] = dateFormat.format(calendar.getTime());
//         calendar.add(Calendar.DAY_OF_MONTH, 1);
//      }
//      for(String date23: dates ){
//          System.out.println(date23);
//      }
//      
//      // Set the dates as the header for the first 7 columns of the table
////      JTable table = new JTable();
////      for (int i = 0; i < 7; i++) {
////         table.getColumnModel().getColumn(i).setHeaderValue(dates[i]);
////      }
//   }
//}
