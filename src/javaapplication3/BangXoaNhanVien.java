    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication3;

import Database.myConnection;
import dto.ManipulateComponents;
import dto.NgayPhanCong;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author caoductin
 */
public class BangXoaNhanVien extends javax.swing.JFrame {
        private JTable table;
    private int row;
    private int col;
    
    public void setDataFromTable(JTable table ,int row , int col){//lay 
        this.table = table;
        this.row = row;
        this.col = col;
        
    }
    public String tenNhanVien;
    
    public void setTenNhanVien(String name){
        this.tenNhanVien = name ;
    }
    public String getTenNhanVien(){
        
        return tenNhanVien;
    }

    public String DatePhanCong = "13-05-2023" ;
    public String caLam = "Ca Sang";
       public void setDate(String date,String caLam){
           this.DatePhanCong =  date;
           this.caLam = caLam;
           }
    /**
     * 
     * Creates new form BangChonNhanVien
     */
 
    
    
    
       public final void LoadDataIntoBangNhanVien(){
         ResultSet rs ;
         PreparedStatement ps;
         Statement st;
         int count = 0;
         String date = changeFormat(DatePhanCong);
         System.out.print(date);
         Connection con = myConnection.getConnection();
        try {
            
            st = con.createStatement();
            String query1 = "SELECT  nv.MaNV,nv.Hoten,pc.CaLam,pc.TrangThai ,nv.ChucVu FROM quanliduan.Nhanvien nv\n" +
"join quanliduan.PhanCong pc on  pc.MaNV = nv.MaNV \n" +
"where pc.NgayLam = '" + date + "' And CaLam = '"+ this.caLam+ "'";
            rs = st.executeQuery(query1);
               DefaultTableModel model =(DefaultTableModel)jTableChonNhanVien.getModel();
            while(rs.next()){
                    
                 count++;
                  
                 Object[] rowData = {rs.getString("MaNV"), rs.getString("Hoten"),rs.getString("ChucVu"),rs.getString("TrangThai")};
                 model.addRow(rowData);
                System.out.println(rs.getString("TrangThai"));
                    
                        
              }
            
          model.setRowCount(count);
                
                
                
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BangXoaNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    } 
       public void pushDateToDatabase(){
          int selectedRow = jTableChonNhanVien.getSelectedRow();
         if (selectedRow != -1) {
             //Do something with the selected row
             System.out.println("Selected row: " + selectedRow);
         } else {
             //No row is currently selected
             System.out.println("No row selected");
         }

           
       }
      
    public void DanhDauKhongHoanThanh(String name){
        DefaultTableModel model = (DefaultTableModel) jTableChonNhanVien.getModel();
        int row2 = jTableChonNhanVien.getSelectedRow();//return the row is select
        int[] selectedRows = jTableChonNhanVien.getSelectedRows();
        
        int row1 = jTableChonNhanVien.getSelectedRowCount();// return the number of row is select
        
        System.out.print(changeFormat(DatePhanCong));
        if (row2 != -1) {
          
            try {
                Connection conn = myConnection.getConnection();
                Statement stmt = conn.createStatement();
                 String sql = "UPDATE `PhanCong` SET `TrangThai` = ?  WHERE MaNV = ? AND CaLam = ? AND NgayLam = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                for (int i = 0; i < selectedRows.length; i++) {// loop all the row was selected
                    int row = selectedRows[i];
                    ps.setString(1, name);
                    ps.setInt(2, Integer.parseInt(jTableChonNhanVien.getValueAt(row, 0).toString()));
                    ps.setString(3, this.caLam);
                    ps.setString(4, changeFormat(DatePhanCong));
                    
                    ps.addBatch();
                    
                    }
                ps.executeBatch();
               
                
            // Loop over the selected rows and remove them
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                int rowIndex = selectedRows[i];
               // model.removeRow(rowIndex);
                model.setValueAt(name, rowIndex, 3);// thay đổi trạng thái của bảng nhân viên
            }


                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog( null,"MySQL error: " + ex.getMessage(), "MySQL Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            
                
            }
        
            this.setTenNhanVien((String)jTableChonNhanVien.getValueAt(row, 1));
            System.out.print(this.getTenNhanVien());
     

            //this.dispose();
        } else {
            // User did not select a row, show error message or perform other action

              JOptionPane.showMessageDialog(this, "No row was selected");
        }

        
        
    }
       
    public String changeFormat(String dateStr){// thay đổi định dạng của ngày
           DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
           DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           LocalDate date = LocalDate.parse(dateStr, inputFormatter);
           String outputStr = outputFormatter.format(date);
             
           return outputStr;
    }
    
    public void sortTable(String selectItem){
            TableModel model = jTableChonNhanVien.getModel();

            // Create a TableRowSorter based on the table's model
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);

            // Set the sort keys based on the desired columns and sort order
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            if(selectItem.equals("theo Tên")){
            sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING)); // Sort by column 1 in ascending order
            }
            else if(selectItem.equals("theo mã")){
                sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING)); // Sort by column 1 in ascending order
            }
            else if(selectItem.equals("theo chức vụ")){
                sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING)); // Sort by column 1 in ascending order
            }
            sorter.setSortKeys(sortKeys);

            // Attach the sorter to the table
            jTableChonNhanVien.setRowSorter(sorter);

        
    }
    public void changeData(){
        jComboBoxSapXep.addActionListener(e -> {
          
            // Get the selected value from the combobox
            String selectedValue = (String) jComboBoxSapXep.getSelectedItem();

            // Sort the table based on the selected value
            sortTable(selectedValue);
        });   
    }
 
    
        
//    JComboBox<String> comboBox = new JComboBox<>();
//List<String> dataList = new ArrayList<>();
//
//// Add an ActionListener to the JComboBox
//    jComboBoxSapXep.addActionListener(new ActionListener() {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        // Get the current selected item from the JComboBox
//        String selectedItem = (String) comboBox.getSelectedItem();
//        
//        // Sort the data list based on the selected item 
//        if ("Ascending".equals(selectedItem)) {
//            Collections.sort(dataList);
//        } else if ("Descending".equals(selectedItem)) {
//            Collections.sort(dataList, Collections.reverseOrder());
//        }
//        
//        // Update the JTable using DefaultTableModel or any custom TableModel implementation
//        updateTable(dataList);
//    }
//});
  
    public BangXoaNhanVien() {
        initComponents();
        ManipulateComponents Thaotac = new ManipulateComponents();
        Thaotac.setHeaderTableTest(jTableChonNhanVien);
     LoadDataIntoBangNhanVien();
        changeData();//this function use for sort data in table when the combobox change 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableChonNhanVien = new javax.swing.JTable();
        xoabutton = new javax.swing.JButton();
        jComboBoxSapXep = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonKoHoanThanh = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jTableChonNhanVien.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jTableChonNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaNV", "TenNV", "Chuc vu", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableChonNhanVien.setGridColor(new java.awt.Color(102, 0, 102));
        jTableChonNhanVien.setRowHeight(40);
        jTableChonNhanVien.setShowHorizontalLines(true);
        jTableChonNhanVien.setShowVerticalLines(true);
        jScrollPane1.setViewportView(jTableChonNhanVien);
        if (jTableChonNhanVien.getColumnModel().getColumnCount() > 0) {
            jTableChonNhanVien.getColumnModel().getColumn(0).setResizable(false);
            jTableChonNhanVien.getColumnModel().getColumn(1).setResizable(false);
            jTableChonNhanVien.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 280, 710, 440);

        xoabutton.setText("Xoá");
        xoabutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoabuttonActionPerformed(evt);
            }
        });
        jPanel1.add(xoabutton);
        xoabutton.setBounds(610, 80, 72, 40);

        jComboBoxSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "theo Tên", "theo mã", "theo chức vụ" }));
        jComboBoxSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSapXepActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxSapXep);
        jComboBoxSapXep.setBounds(90, 90, 103, 30);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Sắp Xếp");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 90, 60, 30);

        jPanel3.setBackground(new java.awt.Color(51, 52, 72));
        jPanel3.setForeground(new java.awt.Color(153, 255, 255));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("X");
        jLabel3.setToolTipText("");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Xóa nhân viên");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(242, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 710, 70);

        jButtonKoHoanThanh.setText("Không hoàn thành");
        jButtonKoHoanThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKoHoanThanhActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonKoHoanThanh);
        jButtonKoHoanThanh.setBounds(420, 80, 160, 40);

        jButton1.setText("Hoàn Thành");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(240, 80, 130, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//the event  sẽ bắt event khi form bị dismiss
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
                 BangPhanCong_Admin navigate = new  BangPhanCong_Admin();
     //   System.out.println("cao duc tin");
                navigate.setVisible(true);//visible jfame 
              
                navigate.pack();
                navigate.setLocationRelativeTo(null);//set position cho jframe
               navigate.setDefaultCloseOperation(EXIT_ON_CLOSE);
               this.dispose();
    
        
    }//GEN-LAST:event_formWindowClosing

    private void xoabuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoabuttonActionPerformed
                    // TODO add your handling code here:

        NgayPhanCong phancong = new NgayPhanCong();
        DefaultTableModel model = (DefaultTableModel) jTableChonNhanVien.getModel();
        int row2 = jTableChonNhanVien.getSelectedRow();//return the row is select
        int[] selectedRows = jTableChonNhanVien.getSelectedRows();
        
        int row1 = jTableChonNhanVien.getSelectedRowCount();// return the number of row is select
       

        if (row2 != -1) {
          
            try {
                Connection conn = myConnection.getConnection();
                Statement stmt = conn.createStatement();
                 String sql = "DELETE FROM `PhanCong` WHERE MaNV = ? AND CaLam = ? AND NgayLam = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                for (int i = 0; i < selectedRows.length; i++) {// loop all the row was selected
                    int row = selectedRows[i];
                    ps.setInt(1, Integer.parseInt(jTableChonNhanVien.getValueAt(row, 0).toString()));
                    ps.setString(2, this.caLam);
                    ps.setString(3, changeFormat(DatePhanCong));
                    
                    ps.addBatch();
                    
                    }
                ps.executeBatch();
               
                
            // Loop over the selected rows and remove them
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                int rowIndex = selectedRows[i];
                model.removeRow(rowIndex);
            }


                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog( null,"MySQL error: " + ex.getMessage(), "MySQL Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            
                
            }
        
            this.setTenNhanVien((String)jTableChonNhanVien.getValueAt(row, 1));
            System.out.print(this.getTenNhanVien());
     

            //this.dispose();
        } else {
            // User did not select a row, show error message or perform other action

              JOptionPane.showMessageDialog(this, "No row was selected");
        }

      //  this.dispose();
    }//GEN-LAST:event_xoabuttonActionPerformed

    private void jComboBoxSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSapXepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSapXepActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
            this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButtonKoHoanThanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKoHoanThanhActionPerformed
        // TODO add your handling code here:
        this.DanhDauKhongHoanThanh("Không hoàn thành");
    }//GEN-LAST:event_jButtonKoHoanThanhActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.DanhDauKhongHoanThanh("Hoan thanh");
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BangXoaNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BangXoaNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BangXoaNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BangXoaNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BangXoaNhanVien().setVisible(true);
                  
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonKoHoanThanh;
    private javax.swing.JComboBox<String> jComboBoxSapXep;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableChonNhanVien;
    private javax.swing.JButton xoabutton;
    // End of variables declaration//GEN-END:variables
}
