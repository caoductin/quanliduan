/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication3;
import DAO.XuLiBangPhanCong;
import dto.ManipulateComponents;
import dto.NgayPhanCong;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
/**
 *
 * @author Le Q. Tien
 */
public class BangPhanCong_Admin extends javax.swing.JFrame {

    
      public String changeFormat(String dateStr){// thay đổi định dạng của ngày
           DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
           DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           LocalDate date = LocalDate.parse(dateStr, inputFormatter);
           String outputStr = outputFormatter.format(date);
             
           return outputStr;
    }
//    
//      
//     public void LoadNhanVienVaoBangPhanCong(JTable NameTable){
//        
//           
//             String[] cols = { "Date", "MaNV", "TenNhanVien" };
//     //  DefaultTableModel model = new DefaultTableModel(cols, 0);
//      DefaultTableModel model =  (DefaultTableModel)NameTable.getModel();
//       try {
//           // Create a connection to MySQL database
//          Connection conn = myConnection.getConnection();
//
//           // Prepare a SQL statement to retrieve required columns' data matching the date criteria
//           String sql = """
//                        SELECT Hoten, NgayLam FROM quanliduan.PhanCong 
//                        join quanliduan.Nhanvien on Nhanvien.MaNV = PhanCong.MaNV
//                        Where PhanCong.NgayLam = ? and CaLam = 'Ca Sang'""";
//           PreparedStatement ps = conn.prepareStatement(sql);
//
//           // Set the date parameter to match the header table dates
//           for (int col = 0; col < NameTable.getColumnCount(); col++) {
//               
//               TableColumnModel columnModel = NameTable.getColumnModel(); 
//               TableColumn column = columnModel.getColumn(col);  // Get the column at the i-th index
//               Object headerValue = column.getHeaderValue();  // Get the header value for the column
//               System.out.println(changeFormat((String)headerValue));  // print out the column header 
//                
//               ps.setString(1, changeFormat((String)headerValue));
//               ResultSet rs = ps.executeQuery();
//
//               // Populate the DefaultTableModel with retrieved data
//              int rowIndex = 0 ;
//               while (rs.next()) { 
//                   
//               
////                   String date = rs.getString("Date");
////                   String manv = rs.getString("MaNV");
//                   String tennv = rs.getString("Hoten");
//                   
//                   
//
//                // Loop through the rows of the table and add data to the desired column
//                
//                   
//                    model.setValueAt(tennv, rowIndex, col);
//                    rowIndex++;
//                
//               //  model.addRow(new Object[] { null, tennv });
//                  
//               }
//           }
//           //Set the DefaultTableModel as the model for the JTable
//        //jTablePhancongCaSang.setModel(model);
//           // Close the database connection
//           conn.close();
//       } catch (Exception e) {
//           e.printStackTrace();
//       }
//
//     }
     
     public void LoadNVIntoBangPhancong(){ // this function will load the Nhanvien into bang phan cong if Nhanvien have jobs
      XuLiBangPhanCong thaoTac = new XuLiBangPhanCong();
      thaoTac.setTableIsNull(jTablePhancongCaSang);
      thaoTac.setTableIsNull(jTablePhanCongCaChieu);
       thaoTac.setTableIsNull(jTablePhanCongCaToi);
       
      thaoTac.LoadNhanVienVaoBangPhanCong(jTablePhancongCaSang,"Ca Sang");
      thaoTac.LoadNhanVienVaoBangPhanCong(jTablePhanCongCaChieu,"Ca Chieu");
      thaoTac.LoadNhanVienVaoBangPhanCong(jTablePhanCongCaToi,"Ca Toi");
         
         
         
     }
     
     
    public void setDataForTable(String name,int row, int col,JTable table){
        table.setValueAt(name, row, col);
    }

    /**
     * Creates new form DanhSachNhanVien
     */
    public  void putTheEmployeeToTable(JTable Name ,String content, int row , int col){
        
        Name.setValueAt(Name, row, col);
        
    }
    
    
    
    
    public void SetDateForTable(LocalDate dateStart){ // set ngày cho bảng phân công
        
      NgayPhanCong date = new NgayPhanCong();
      DefaultTableModel model = (DefaultTableModel) jTablePhancongCaSang.getModel();
      
      DefaultTableModel model1 = (DefaultTableModel) jTablePhanCongCaChieu.getModel();
      DefaultTableModel model2 = (DefaultTableModel) jTablePhanCongCaToi.getModel();
      
      

      
     model.setRowCount(date.countTheEmployee());  // đặt số lượng hàng của bảng phân công ca sáng = số lượng nhân viê
     model1.setRowCount(date.countTheEmployee());// đặt số lượng hàng của bảng phân công ca  = số lượng nhân viê
     model2.setRowCount(date.countTheEmployee());// đặt số lượng hàng của bảng phân công ca  = số lượng nhân viê 

      date.countTheEmployee();
      date.setTheTitleOfTable(dateStart, jTablePhancongCaSang);
        date.setTheTitleOfTable(dateStart, jTablePhanCongCaChieu);
         date.setTheTitleOfTable(dateStart, jTablePhanCongCaToi);
        // date.setTheTitleOfTable(dateStart, jTablePhanCongCaToi);
       
      
        
    }
    public BangPhanCong_Admin() {
        
        initComponents();
          new ManipulateComponents().setHeaderTableTest(jTablePhancongCaSang);
        this.setLocationRelativeTo(null);
        this.SetDateForTable(LocalDate.now());
        //this.LoadNhanVienVaoBangPhanCong(jTablePhancongCaSang);
        LoadNVIntoBangPhancong();
      
        
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
        jLabel1 = new javax.swing.JLabel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePhanCongCaToi = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePhancongCaSang = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePhanCongCaChieu = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateChooserLaplich = new com.toedter.calendar.JDateChooser();
        jButtonLapLich = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanelEmployee = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanelCustomers = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanelProducts = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1400, 800));
        setUndecorated(true);
        setSize(new java.awt.Dimension(1200, 800));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Hệ Thống Quản Lý");

        jButton4.setBackground(new java.awt.Color(242, 242, 242));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/cancel1.png"))); // NOI18N
        jButton4.setMinimumSize(new java.awt.Dimension(75, 50));
        jButton4.setPreferredSize(new java.awt.Dimension(75, 48));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(242, 242, 242));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/restore1.png"))); // NOI18N
        jButton5.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton5.setPreferredSize(new java.awt.Dimension(50, 48));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(242, 242, 242));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/minimize1.png"))); // NOI18N
        jButton8.setMinimumSize(new java.awt.Dimension(50, 50));
        jButton8.setPreferredSize(new java.awt.Dimension(50, 48));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 818, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 80));

        jPanel4.setBackground(new java.awt.Color(192, 192, 192));
        jPanel4.setForeground(new java.awt.Color(255, 204, 204));
        jPanel4.setToolTipText("");
        jPanel4.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Bảng phân công");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(60, 30, 240, 40);

        jTablePhanCongCaToi.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jTablePhanCongCaToi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "T2", "T3", "T4", "T5", "T6", "T7", "CN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePhanCongCaToi.setRowHeight(50);
        jTablePhanCongCaToi.setShowVerticalLines(true);
        jTablePhanCongCaToi.setSurrendersFocusOnKeystroke(true);
        jTablePhanCongCaToi.getTableHeader().setReorderingAllowed(false);
        jTablePhanCongCaToi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePhanCongCaToiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePhanCongCaToi);
        if (jTablePhanCongCaToi.getColumnModel().getColumnCount() > 0) {
            jTablePhanCongCaToi.getColumnModel().getColumn(0).setResizable(false);
            jTablePhanCongCaToi.getColumnModel().getColumn(1).setResizable(false);
            jTablePhanCongCaToi.getColumnModel().getColumn(2).setResizable(false);
            jTablePhanCongCaToi.getColumnModel().getColumn(3).setResizable(false);
            jTablePhanCongCaToi.getColumnModel().getColumn(4).setResizable(false);
            jTablePhanCongCaToi.getColumnModel().getColumn(5).setResizable(false);
            jTablePhanCongCaToi.getColumnModel().getColumn(6).setResizable(false);
        }
        jTablePhanCongCaToi.getTableHeader().setVisible(false);

        jPanel4.add(jScrollPane1);
        jScrollPane1.setBounds(130, 530, 980, 180);

        jTablePhancongCaSang.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jTablePhancongCaSang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePhancongCaSang.setRowHeight(50);
        jTablePhancongCaSang.setShowVerticalLines(true);
        jTablePhancongCaSang.getTableHeader().setReorderingAllowed(false);
        jTablePhancongCaSang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePhancongCaSangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTablePhancongCaSang);
        if (jTablePhancongCaSang.getColumnModel().getColumnCount() > 0) {
            jTablePhancongCaSang.getColumnModel().getColumn(0).setResizable(false);
            jTablePhancongCaSang.getColumnModel().getColumn(1).setResizable(false);
            jTablePhancongCaSang.getColumnModel().getColumn(2).setResizable(false);
            jTablePhancongCaSang.getColumnModel().getColumn(3).setResizable(false);
            jTablePhancongCaSang.getColumnModel().getColumn(4).setResizable(false);
            jTablePhancongCaSang.getColumnModel().getColumn(5).setResizable(false);
            jTablePhancongCaSang.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel4.add(jScrollPane2);
        jScrollPane2.setBounds(130, 150, 980, 180);

        jTablePhanCongCaChieu.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jTablePhanCongCaChieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "T2", "T3", "T4", "T5", "T6", "T7", "CN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePhanCongCaChieu.setRowHeight(50);
        jTablePhanCongCaChieu.setShowVerticalLines(true);
        jTablePhanCongCaChieu.getTableHeader().setReorderingAllowed(false);
        jTablePhanCongCaChieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePhanCongCaChieuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTablePhanCongCaChieu);
        if (jTablePhanCongCaChieu.getColumnModel().getColumnCount() > 0) {
            jTablePhanCongCaChieu.getColumnModel().getColumn(0).setResizable(false);
            jTablePhanCongCaChieu.getColumnModel().getColumn(1).setResizable(false);
            jTablePhanCongCaChieu.getColumnModel().getColumn(2).setResizable(false);
            jTablePhanCongCaChieu.getColumnModel().getColumn(3).setResizable(false);
            jTablePhanCongCaChieu.getColumnModel().getColumn(4).setResizable(false);
            jTablePhanCongCaChieu.getColumnModel().getColumn(5).setResizable(false);
            jTablePhanCongCaChieu.getColumnModel().getColumn(6).setResizable(false);
        }
        jTablePhanCongCaChieu.getTableHeader().setVisible(false);

        jPanel4.add(jScrollPane3);
        jScrollPane3.setBounds(130, 340, 980, 180);

        jLabel4.setBackground(new java.awt.Color(0, 128, 128));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CA TỐI");
        jLabel4.setOpaque(true);
        jPanel4.add(jLabel4);
        jLabel4.setBounds(20, 530, 110, 180);

        jLabel5.setBackground(new java.awt.Color(0, 128, 128));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CA SÁNG");
        jLabel5.setOpaque(true);
        jPanel4.add(jLabel5);
        jLabel5.setBounds(20, 150, 110, 180);

        jLabel6.setBackground(new java.awt.Color(0, 128, 128));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("CA CHIỀU");
        jLabel6.setOpaque(true);
        jPanel4.add(jLabel6);
        jLabel6.setBounds(20, 340, 110, 180);
        jPanel4.add(jDateChooserLaplich);
        jDateChooserLaplich.setBounds(810, 50, 160, 30);

        jButtonLapLich.setText("Lập lịch");
        jButtonLapLich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLapLichActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonLapLich);
        jButtonLapLich.setBounds(1000, 50, 80, 30);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 1140, 720));

        jPanel3.setBackground(new java.awt.Color(51, 52, 72));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/BACK1.png"))); // NOI18N
        jButton2.setText("Trở về");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanelEmployee.setBackground(new java.awt.Color(51, 52, 72));
        jPanelEmployee.setToolTipText("");
        jPanelEmployee.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanelEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelEmployeeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelEmployeeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelEmployeeMouseExited(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nhân viên");

        javax.swing.GroupLayout jPanelEmployeeLayout = new javax.swing.GroupLayout(jPanelEmployee);
        jPanelEmployee.setLayout(jPanelEmployeeLayout);
        jPanelEmployeeLayout.setHorizontalGroup(
            jPanelEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmployeeLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelEmployeeLayout.setVerticalGroup(
            jPanelEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmployeeLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanelCustomers.setBackground(new java.awt.Color(51, 52, 72));
        jPanelCustomers.setToolTipText("");
        jPanelCustomers.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanelCustomers.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanelCustomersMouseMoved(evt);
            }
        });
        jPanelCustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelCustomersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelCustomersMouseExited(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Hóa đơn");

        javax.swing.GroupLayout jPanelCustomersLayout = new javax.swing.GroupLayout(jPanelCustomers);
        jPanelCustomers.setLayout(jPanelCustomersLayout);
        jPanelCustomersLayout.setHorizontalGroup(
            jPanelCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCustomersLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanelCustomersLayout.setVerticalGroup(
            jPanelCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCustomersLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel12)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanelProducts.setBackground(new java.awt.Color(51, 52, 72));
        jPanelProducts.setToolTipText("");
        jPanelProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelProductsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelProductsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelProductsMouseExited(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Sản phẩm");

        javax.swing.GroupLayout jPanelProductsLayout = new javax.swing.GroupLayout(jPanelProducts);
        jPanelProducts.setLayout(jPanelProductsLayout);
        jPanelProductsLayout.setHorizontalGroup(
            jPanelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductsLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanelProductsLayout.setVerticalGroup(
            jPanelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel14)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(51, 52, 72));
        jPanel9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel9MouseExited(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Phân công");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel13)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanelEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 260, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButtonLapLichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLapLichActionPerformed
        // TODO add your handling code here:
        Date date = jDateChooserLaplich.getDate();
        if (date != null) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();// hàm này chuyển  tu Date form Jdate sang  localDate
       // System.out.println("Selected date: " + localDate);
           this.SetDateForTable(localDate);
           this.LoadNVIntoBangPhancong();
} else {
        System.out.println("No date selected yet.");
}
    }//GEN-LAST:event_jButtonLapLichActionPerformed

    private void jTablePhancongCaSangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePhancongCaSangMouseClicked
        // TODO add your handling code here:
            if (evt.getClickCount() == 2) {// nếu người dùng click vào ô trống

                                //get the vaule of the cell you clicked
                                 int row = jTablePhancongCaSang.getSelectedRow();
                                 int column1 = jTablePhancongCaSang.getSelectedColumn();
                                 Object value = jTablePhancongCaSang.getValueAt(row, column1);

                                if(value == null){

                                  


                                 TableColumnModel columnModel = jTablePhancongCaSang.getColumnModel(); // get the column model
                                 int index = jTablePhancongCaSang.columnAtPoint(evt.getPoint()); // get the column index of the clicked cell
                                 TableColumn column = columnModel.getColumn(index); // get the TableColumn at the specified index
                                 Object headerValue = column.getHeaderValue(); // get the header value of the column





                             //khi người dùng double click vào một ô bất kì thì sẽ  chuyển tới 
                                   //  2 = new BangChonNhanVien();
                                     BangChonNhanVien BangChon = new BangChonNhanVien();
                                     BangChon.setDate((String) headerValue,"Ca Sang");
                                     BangChon.LoadDataIntoBangNhanVien();


                                      BangChon.addWindowListener(new WindowAdapter() {
                                    @Override
                                         public void windowClosed(WindowEvent e) {
                                              int row = jTablePhancongCaSang.getSelectedRow();
                                                int column = jTablePhancongCaSang.getSelectedColumn();
                                // This method is called when the SecondJFrame is closed

                                        //jTablePhancongCaSang.setValueAt(navigate.tenNhanVien, row, column);
                                         XuLiBangPhanCong thaoTac = new XuLiBangPhanCong();

                                         thaoTac.LoadNhanVienVaoBangPhanCong(jTablePhancongCaSang,"Ca Sang");

                            }
                        });
                                     BangChon.setVisible(true);//visible jfame 
                                     BangChon.pack();
                                     BangChon.setLocationRelativeTo(null);//set position cho jframe

                                }
                else //nếu người dùng click vào ô có người thì sẽ thực hiện xoá thay đổi nhân viên
                    {
                        BangXoaNhanVien bangXoa = new BangXoaNhanVien();
                                 TableColumnModel columnModel = jTablePhancongCaSang.getColumnModel(); // get the column model
                                 int index = jTablePhancongCaSang.columnAtPoint(evt.getPoint()); // get the column index of the clicked cell
                                 TableColumn column = columnModel.getColumn(index); // get the TableColumn at the specified index
                                 Object headerValue = column.getHeaderValue(); // get the header value of the column
                                     
                                 




                             //khi người dùng double click vào một ô bất kì thì sẽ  chuyển tới 
                                   //  BangChonNhanVien navigate = new BangChonNhanVien();
                                     bangXoa.setDate((String) headerValue,"Ca Sang"); 
                                     bangXoa.LoadDataIntoBangNhanVien();
                               


                                      bangXoa.addWindowListener(new WindowAdapter() {
                                    @Override
                                         public void windowClosed(WindowEvent e) {
                                              int row = jTablePhancongCaSang.getSelectedRow();
                                                int column = jTablePhancongCaSang.getSelectedColumn();
                                // This method is called when the SecondJFrame is closed

                                        //jTablePhancongCaSang.setValueAt(navigate.tenNhanVien, row, column);
                                         XuLiBangPhanCong thaoTac = new XuLiBangPhanCong();
                                         thaoTac.setTableIsNull(jTablePhancongCaSang);
                                         thaoTac.LoadNhanVienVaoBangPhanCong(jTablePhancongCaSang,"Ca Sang");
                            }
                        });
                                     bangXoa.setVisible(true);//visible jfame 
                                     bangXoa.pack();
                                     bangXoa.setLocationRelativeTo(null);//set position cho jframe
                      
                    }
               
       }
    }//GEN-LAST:event_jTablePhancongCaSangMouseClicked
    
    private void jTablePhanCongCaChieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePhanCongCaChieuMouseClicked
        // TODO add your handling code here:
         if (evt.getClickCount() == 2) {
             
             

                                //get the vaule of the cell you clicked
                                 int row = jTablePhanCongCaChieu.getSelectedRow();
                                 int column1 = jTablePhanCongCaChieu.getSelectedColumn();
                                 Object value = jTablePhanCongCaChieu.getValueAt(row, column1);

                                if(value == null){

                                  


                                 TableColumnModel columnModel = jTablePhanCongCaChieu.getColumnModel(); // get the column model
                                 int index = jTablePhanCongCaChieu.columnAtPoint(evt.getPoint()); // get the column index of the clicked cell
                                 TableColumn column = columnModel.getColumn(index); // get the TableColumn at the specified index
                                 Object headerValue = column.getHeaderValue(); // get the header value of the column





                             //khi người dùng double click vào một ô bất kì thì sẽ  chuyển tới 
                                   //  2 = new BangChonNhanVien();
                                     BangChonNhanVien BangChon = new BangChonNhanVien();
                                     BangChon.setDate((String) headerValue,"Ca Chieu");
                                     BangChon.LoadDataIntoBangNhanVien();


                                      BangChon.addWindowListener(new WindowAdapter() {
                                    @Override
                                         public void windowClosed(WindowEvent e) {
                                              int row = jTablePhanCongCaChieu.getSelectedRow();
                                                int column = jTablePhanCongCaChieu.getSelectedColumn();
                                // This method is called when the SecondJFrame is closed

                                        //jTablePhancongCaSang.setValueAt(navigate.tenNhanVien, row, column);
                                         XuLiBangPhanCong thaoTac = new XuLiBangPhanCong();

                                         thaoTac.LoadNhanVienVaoBangPhanCong(jTablePhanCongCaChieu,"Ca Chieu");

                            }
                        });
                                     BangChon.setVisible(true);//visible jfame 
                                     BangChon.pack();
                                     BangChon.setLocationRelativeTo(null);//set position cho jframe

                                }
                else //nếu người dùng click vào ô có người thì sẽ thực hiện xoá thay đổi nhân viên
                    {
                        BangXoaNhanVien bangXoa = new BangXoaNhanVien();
                                 TableColumnModel columnModel = jTablePhanCongCaChieu.getColumnModel(); // get the column model
                                 int index = jTablePhanCongCaChieu.columnAtPoint(evt.getPoint()); // get the column index of the clicked cell
                                 TableColumn column = columnModel.getColumn(index); // get the TableColumn at the specified index
                                 Object headerValue = column.getHeaderValue(); // get the header value of the column
                                     
                                 




                             //khi người dùng double click vào một ô bất kì thì sẽ  chuyển tới 
                                   //  BangChonNhanVien navigate = new BangChonNhanVien();
                                     bangXoa.setDate((String) headerValue,"Ca Chieu"); 
                                     bangXoa.LoadDataIntoBangNhanVien();
                               


                                      bangXoa.addWindowListener(new WindowAdapter() {
                                    @Override
                                         public void windowClosed(WindowEvent e) {
                                              int row = jTablePhanCongCaChieu.getSelectedRow();
                                                int column = jTablePhanCongCaChieu.getSelectedColumn();
                                // This method is called when the SecondJFrame is closed

                                        //jTablePhancongCaSang.setValueAt(navigate.tenNhanVien, row, column);
                                         XuLiBangPhanCong thaoTac = new XuLiBangPhanCong();
                                         thaoTac.setTableIsNull(jTablePhanCongCaChieu);
                                         thaoTac.LoadNhanVienVaoBangPhanCong(jTablePhanCongCaChieu,"Ca Chieu");
                            }
                        });
                                     bangXoa.setVisible(true);//visible jfame 
                                     bangXoa.pack();
                                     bangXoa.setLocationRelativeTo(null);//set position cho jframe
                      
                    }
              
               
       }
    }//GEN-LAST:event_jTablePhanCongCaChieuMouseClicked

    private void jTablePhanCongCaToiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePhanCongCaToiMouseClicked
        // TODO add your handling code here:
         if (evt.getClickCount() == 2) {
             
                                //get the vaule of the cell you clicked
                                 int row = jTablePhanCongCaToi.getSelectedRow();
                                 int column1 = jTablePhanCongCaToi.getSelectedColumn();
                                 Object value = jTablePhanCongCaToi.getValueAt(row, column1);

                                if(value == null){

                                  


                                 TableColumnModel columnModel = jTablePhanCongCaToi.getColumnModel(); // get the column model
                                 int index = jTablePhanCongCaToi.columnAtPoint(evt.getPoint()); // get the column index of the clicked cell
                                 TableColumn column = columnModel.getColumn(index); // get the TableColumn at the specified index
                                 Object headerValue = column.getHeaderValue(); // get the header value of the column





                             //khi người dùng double click vào một ô bất kì thì sẽ  chuyển tới 
                                   //  2 = new BangChonNhanVien();
                                     BangChonNhanVien BangChon = new BangChonNhanVien();
                                     BangChon.setDate((String) headerValue,"Ca Toi");
                                     BangChon.LoadDataIntoBangNhanVien();


                                      BangChon.addWindowListener(new WindowAdapter() {
                                    @Override
                                         public void windowClosed(WindowEvent e) {
                                              int row = jTablePhanCongCaToi.getSelectedRow();
                                                int column = jTablePhanCongCaToi.getSelectedColumn();
                                // This method is called when the SecondJFrame is closed

                                        //jTablePhancongCaSang.setValueAt(navigate.tenNhanVien, row, column);
                                         XuLiBangPhanCong thaoTac = new XuLiBangPhanCong();

                                         thaoTac.LoadNhanVienVaoBangPhanCong(jTablePhanCongCaToi,"Ca Toi");

                            }
                        });
                                     BangChon.setVisible(true);//visible jfame 
                                     BangChon.pack();
                                     BangChon.setLocationRelativeTo(null);//set position cho jframe

                                }
                else //nếu người dùng click vào ô có người thì sẽ thực hiện xoá thay đổi nhân viên
                    {
                        BangXoaNhanVien bangXoa = new BangXoaNhanVien();
                                 TableColumnModel columnModel = jTablePhanCongCaToi.getColumnModel(); // get the column model
                                 int index = jTablePhanCongCaToi.columnAtPoint(evt.getPoint()); // get the column index of the clicked cell
                                 TableColumn column = columnModel.getColumn(index); // get the TableColumn at the specified index
                                 Object headerValue = column.getHeaderValue(); // get the header value of the column
                                     
                                 




                             //khi người dùng double click vào một ô bất kì thì sẽ  chuyển tới 
                                   //  BangChonNhanVien navigate = new BangChonNhanVien();
                                     bangXoa.setDate((String) headerValue,"Ca Toi"); 
                                     bangXoa.LoadDataIntoBangNhanVien();
                               


                                      bangXoa.addWindowListener(new WindowAdapter() {
                                    @Override
                                         public void windowClosed(WindowEvent e) {
                                              int row = jTablePhanCongCaToi.getSelectedRow();
                                                int column = jTablePhanCongCaToi.getSelectedColumn();
                                // This method is called when the SecondJFrame is closed

                                        //jTablePhancongCaSang.setValueAt(navigate.tenNhanVien, row, column);
                                         XuLiBangPhanCong thaoTac = new XuLiBangPhanCong();
                                         thaoTac.setTableIsNull(jTablePhanCongCaToi);
                                         thaoTac.LoadNhanVienVaoBangPhanCong(jTablePhanCongCaToi,"Ca Toi");
                            }
                        });
                                     bangXoa.setVisible(true);//visible jfame 
                                     bangXoa.pack();
                                     bangXoa.setLocationRelativeTo(null);//set position cho jframe
                      
                    }
              
               
              
               
       }
    }//GEN-LAST:event_jTablePhanCongCaToiMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanelEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEmployeeMouseClicked
        // TODO add your handling code here:
        //                 NhanVien supf = new NhanVien();
        //                supf.setVisible(true);//visible jfame
        //                supf.pack();
        //                supf.setLocationRelativeTo(null);//set position cho jframe
        //
        //
        //
        //                supf.setDefaultCloseOperation(EXIT_ON_CLOSE);//ham nay khi dong se tat luon
        //                this.dispose();
    }//GEN-LAST:event_jPanelEmployeeMouseClicked

    private void jPanelEmployeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEmployeeMouseEntered
        // TODO add your handling code here:
        jPanelEmployee.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_jPanelEmployeeMouseEntered

    private void jPanelEmployeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEmployeeMouseExited
        // TODO add your handling code here:
        jPanelEmployee.setBackground(new Color(51,52,72));
    }//GEN-LAST:event_jPanelEmployeeMouseExited

    private void jPanelCustomersMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCustomersMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelCustomersMouseMoved

    private void jPanelCustomersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCustomersMouseEntered
        // TODO add your handling code here:
        jPanelCustomers.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_jPanelCustomersMouseEntered

    private void jPanelCustomersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCustomersMouseExited
        // TODO add your handling code here:
        jPanelCustomers.setBackground(new Color(51,52,72));
    }//GEN-LAST:event_jPanelCustomersMouseExited

    private void jPanelProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProductsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelProductsMouseClicked

    private void jPanelProductsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProductsMouseEntered
        // TODO add your handling code here:
        jPanelProducts.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_jPanelProductsMouseEntered

    private void jPanelProductsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProductsMouseExited
        // TODO add your handling code here:
        jPanelProducts.setBackground(new Color(51,52,72));
    }//GEN-LAST:event_jPanelProductsMouseExited

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered
        // TODO add your handling code here:
        jPanel9.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_jPanel9MouseEntered

    private void jPanel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseExited
        // TODO add your handling code here:
        jPanel9.setBackground(new Color(51,52,72));
    }//GEN-LAST:event_jPanel9MouseExited

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
            java.util.logging.Logger.getLogger(BangPhanCong_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BangPhanCong_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BangPhanCong_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BangPhanCong_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new BangPhanCong_Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonLapLich;
    private com.toedter.calendar.JDateChooser jDateChooserLaplich;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelCustomers;
    private javax.swing.JPanel jPanelEmployee;
    private javax.swing.JPanel jPanelProducts;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTablePhanCongCaChieu;
    private javax.swing.JTable jTablePhanCongCaToi;
    private javax.swing.JTable jTablePhancongCaSang;
    // End of variables declaration//GEN-END:variables
}
