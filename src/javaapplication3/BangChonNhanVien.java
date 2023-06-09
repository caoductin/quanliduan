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
public class BangChonNhanVien extends javax.swing.JFrame {
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

    public String DatePhanCong = "12-12-2013";
    public String caLam = "Ca Sang";
       public void setDate(String date,String caLam){
           this.DatePhanCong =  date;
           this.caLam = caLam;
       }
    /**
     * 
     * Creates new form BangChonNhanVien
     */
      
       
       public void UPdateDataBase (){
       try {
            Connection con = myConnection.getConnection();
            String query = "UPDATE `PhanCong` SET `TrangThai` = 'Hoan thanh' WHERE `NgayLam` < ? AND `TrangThai` = 'Chua hoan thanh';";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
            int rowsUpdated = stmt.executeUpdate();
            System.out.printf("Updated %d rows.%n", rowsUpdated);
        } catch (SQLException ex) {
            Logger.getLogger(BangChonNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
    
    
    
       public final void LoadDataIntoBangNhanVien(){
         ResultSet rs ;
         PreparedStatement ps;
         Statement st;
         int count = 0;
         String date = changeFormat(DatePhanCong);
         Connection con = myConnection.getConnection();
        try {
            st = con.createStatement();
            String query1 = "SELECT * FROM quanliduan.Nhanvien nv\n" +
                " where not exists(\n" +
                "	select *\n" +
                "    from quanliduan.Phancong pc\n" +
                "    Where nv.MaNV = pc.MaNV And pc.NgayLam = '"+date +"' and pc.CaLam = '"+this.caLam+"'\n" +
                " );";
            rs = st.executeQuery(query1);
               DefaultTableModel model =(DefaultTableModel)jTableChonNhanVien.getModel();
            while(rs.next()){
                    
                 count++;
                  
                 Object[] rowData = {rs.getString("MaNV"), rs.getString("Hoten"),rs.getString("ChucVu")};
                 model.addRow(rowData);
               
                    
                        
              }
          model.setRowCount(count);
                
                
                
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BangChonNhanVien.class.getName()).log(Level.SEVERE, null, ex);
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
 
  
    public BangChonNhanVien() {
        initComponents();
        ManipulateComponents Thaotac = new ManipulateComponents();
        Thaotac.setHeaderTableTest(jTableChonNhanVien);
        
        changeData();
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
        jLabel2 = new javax.swing.JLabel();
        jComboBoxSapXep = new javax.swing.JComboBox<>();
        xoabutton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTableChonNhanVien.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jTableChonNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaNV", "TenNV", "Chuc vu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
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
        }

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Sắp Xếp");

        jComboBoxSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "theo Tên", "theo mã", "theo chức vụ" }));
        jComboBoxSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSapXepActionPerformed(evt);
            }
        });

        xoabutton.setText("Chọn");
        xoabutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoabuttonActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(51, 52, 72));
        jPanel5.setForeground(new java.awt.Color(153, 255, 255));

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("X");
        jLabel7.setToolTipText("");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Chọn nhân viên");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jComboBoxSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297)
                .addComponent(xoabutton)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xoabutton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
             System.out.println("cao duc tin");
        
        
    }//GEN-LAST:event_formWindowClosing

    private void jComboBoxSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSapXepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSapXepActionPerformed

    private void xoabuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoabuttonActionPerformed
        // TODO add your handling code here:

        NgayPhanCong phancong = new NgayPhanCong();
        
        int row2 = jTableChonNhanVien.getSelectedRow();//return the row is select
        int[] selectedRows = jTableChonNhanVien.getSelectedRows();
        
        int row1 = jTableChonNhanVien.getSelectedRowCount();// return the number of row is select
       

        if (row2 != -1) {
            System.out.print("cao duc tin");
            
            try {
                Connection conn = myConnection.getConnection();
                Statement stmt = conn.createStatement();
                 String sql = "INSERT INTO `PhanCong` (`MaNV`, `CaLam`, `NgayLam`) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                for (int i = 0; i < selectedRows.length; i++) {
                    int row = selectedRows[i];
                    ps.setString(1, jTableChonNhanVien.getValueAt(row, 0).toString());
                    ps.setString(2, this.caLam);
                    ps.setString(3, changeFormat(DatePhanCong));
                    ps.addBatch();
                }
                ps.executeBatch();
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog( null,"MySQL error: " + ex.getMessage(), "MySQL Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            
                
            }
            // User selected a row, perform action
          
//            if( phancong.InsertPhanCong((String)jTableChonNhanVien.getValueAt(row, 0), this.caLam,DatePhanCong,selectedRows.length) == false){
//                return ;
//            }
            
            
            this.setTenNhanVien((String)jTableChonNhanVien.getValueAt(row, 1));
            this.UPdateDataBase();

            this.dispose();
        } else {
            // User did not select a row, show error message or perform other action

              JOptionPane.showMessageDialog(this, "No row was selected");
        }
        //  this.dispose();
    }//GEN-LAST:event_xoabuttonActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

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
            java.util.logging.Logger.getLogger(BangChonNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BangChonNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BangChonNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BangChonNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BangChonNhanVien().setVisible(true);
                

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxSapXep;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableChonNhanVien;
    private javax.swing.JButton xoabutton;
    // End of variables declaration//GEN-END:variables
}
