
package javaapplication3;
import Database.myConnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Menu extends javax.swing.JFrame {

    
    public Menu() {
        initComponents();
         this.setLocationRelativeTo(null);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanelLogout = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanelEmployee = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanelCustomers = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanelProducts = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCustomer = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 900));
        setPreferredSize(new java.awt.Dimension(1300, 850));
        setSize(new java.awt.Dimension(1300, 850));
        getContentPane().setLayout(null);

        jPanel3.setBackground(new java.awt.Color(51, 51, 60));
        jPanel3.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelLogout.setBackground(new java.awt.Color(51, 51, 61));
        jPanelLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelLogoutMouseExited(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Segoe UI Historic", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Exit_26px_1.png"))); // NOI18N
        jLabel5.setText("     Log out");

        javax.swing.GroupLayout jPanelLogoutLayout = new javax.swing.GroupLayout(jPanelLogout);
        jPanelLogout.setLayout(jPanelLogoutLayout);
        jPanelLogoutLayout.setHorizontalGroup(
            jPanelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLogoutLayout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        jPanelLogoutLayout.setVerticalGroup(
            jPanelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLogoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
        );

        jPanel3.add(jPanelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 300, 50));

        jPanelEmployee.setBackground(new java.awt.Color(51, 51, 60));
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

        jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/employee (1).png"))); // NOI18N
        jLabel6.setText("      Employee");

        javax.swing.GroupLayout jPanelEmployeeLayout = new javax.swing.GroupLayout(jPanelEmployee);
        jPanelEmployee.setLayout(jPanelEmployeeLayout);
        jPanelEmployeeLayout.setHorizontalGroup(
            jPanelEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmployeeLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanelEmployeeLayout.setVerticalGroup(
            jPanelEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel3.add(jPanelEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 300, 50));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Features");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 80, -1));

        jPanelCustomers.setBackground(new java.awt.Color(51, 51, 61));
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

        jLabel12.setFont(new java.awt.Font("Segoe UI Historic", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcon/client.png"))); // NOI18N
        jLabel12.setText("      Customers");

        javax.swing.GroupLayout jPanelCustomersLayout = new javax.swing.GroupLayout(jPanelCustomers);
        jPanelCustomers.setLayout(jPanelCustomersLayout);
        jPanelCustomersLayout.setHorizontalGroup(
            jPanelCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCustomersLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanelCustomersLayout.setVerticalGroup(
            jPanelCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCustomersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanelCustomers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 300, -1));

        jPanelProducts.setBackground(new java.awt.Color(51, 51, 60));
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

        jLabel8.setFont(new java.awt.Font("Segoe UI Historic", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcon/product.png"))); // NOI18N
        jLabel8.setText("      Products");

        javax.swing.GroupLayout jPanelProductsLayout = new javax.swing.GroupLayout(jPanelProducts);
        jPanelProducts.setLayout(jPanelProductsLayout);
        jPanelProductsLayout.setHorizontalGroup(
            jPanelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductsLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanelProductsLayout.setVerticalGroup(
            jPanelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanelProducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 300, 50));

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 90, 300, 760);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("X");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcon/person.png"))); // NOI18N
        jLabel7.setText("Admin");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(818, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6);
        jPanel6.setBounds(300, 0, 1000, 90);

        jPanel7.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel7.setLayout(null);

        jPanel8.setBackground(new java.awt.Color(204, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel7.add(jPanel8);
        jPanel8.setBounds(60, 60, 250, 90);
        jPanel8.getAccessibleContext().setAccessibleName("");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel9.setText("Number of Employees");
        jPanel7.add(jLabel9);
        jLabel9.setBounds(60, 16, 250, 30);

        jTableCustomer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "User Name ", "Password", "Gender", "Phone", "Full Name"
            }
        ));
        jTableCustomer.setFocusCycleRoot(true);
        jTableCustomer.setFocusTraversalPolicyProvider(true);
        jTableCustomer.setFocusable(false);
        jTableCustomer.setGridColor(new java.awt.Color(153, 153, 153));
        jTableCustomer.setRowHeight(25);
        jTableCustomer.setSelectionBackground(new java.awt.Color(255, 51, 51));
        jTableCustomer.setShowGrid(true);
        jScrollPane1.setViewportView(jTableCustomer);

        jPanel7.add(jScrollPane1);
        jScrollPane1.setBounds(50, 220, 800, 150);

        jPanel1.setBackground(new java.awt.Color(47, 47, 47));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_menu_48px_1.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Management System");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(30, 30, 30))
        );

        jPanel7.add(jPanel1);
        jPanel1.setBounds(330, 90, 300, 90);

        getContentPane().add(jPanel7);
        jPanel7.setBounds(300, 90, 1000, 810);

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jPanelCustomersMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCustomersMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelCustomersMouseMoved

    private void jPanelEmployeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEmployeeMouseEntered
        // TODO add your handling code here:
        jPanelEmployee.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_jPanelEmployeeMouseEntered

    private void jPanelEmployeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEmployeeMouseExited
        // TODO add your handling code here:
        jPanelEmployee.setBackground(new Color(51,51,60));
    }//GEN-LAST:event_jPanelEmployeeMouseExited

    private void jPanelCustomersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCustomersMouseEntered
        // TODO add your handling code here:
        jPanelCustomers.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_jPanelCustomersMouseEntered

    private void jPanelCustomersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCustomersMouseExited
        // TODO add your handling code here:
          jPanelCustomers.setBackground(new Color(51,51,60));
    }//GEN-LAST:event_jPanelCustomersMouseExited

    private void jPanelLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelLogoutMouseExited
        // TODO add your handling code here:
        
          jPanelLogout.setBackground(new Color(51, 51, 61));
    }//GEN-LAST:event_jPanelLogoutMouseExited

    private void jPanelLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelLogoutMouseEntered
        // TODO add your handling code here:
             jPanelLogout.setBackground(new Color(54, 47, 217));
    }//GEN-LAST:event_jPanelLogoutMouseEntered

    private void jPanelProductsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProductsMouseExited
        // TODO add your handling code here:
        jPanelProducts.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_jPanelProductsMouseExited

    private void jPanelProductsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProductsMouseEntered
        // TODO add your handling code here:
        jPanelProducts.setBackground(new Color(0,0,0));
        
    }//GEN-LAST:event_jPanelProductsMouseEntered

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

    private void jPanelProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProductsMouseClicked
        // TODO add your handling code here:
          Products supf = new Products();
                supf.setVisible(true);//visible jfame 
                supf.pack();
                supf.setLocationRelativeTo(null);//set position cho jframe
                
          
                supf.setDefaultCloseOperation(EXIT_ON_CLOSE);//ham nay khi dong se tat luon 
                this.dispose();
    }//GEN-LAST:event_jPanelProductsMouseClicked

    private void jPanelLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelLogoutMouseClicked
        // TODO add your handling code here:
                LoginForm supf = new LoginForm();
                supf.setVisible(true);//visible jfame 
                supf.pack();
                supf.setLocationRelativeTo(null);//set position cho jframe
                supf.setDefaultCloseOperation(EXIT_ON_CLOSE);//ham nay khi dong se tat luon 
                this.dispose();
    }//GEN-LAST:event_jPanelLogoutMouseClicked
  
    
    
    public void setHeaderTable(){
        
        jTableCustomer.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,16));//set the font and the size for header of table
        DefaultTableCellRenderer head_render = new DefaultTableCellRenderer(); 
        head_render.setBackground(new Color(204,153,255)); 
        jTableCustomer.getTableHeader().setPreferredSize(new Dimension(jTableCustomer.getTableHeader().getWidth(),25)); //set the with and height for hearder table

        jTableCustomer.getTableHeader().setDefaultRenderer(head_render);//set color for header table
      
         jTableCustomer.getTableHeader().setForeground(new Color(255,255,255));
       
        
        
        
        
    }
    
    
    
    
    public  void getData(){
           Connection con = myConnection.getConnection();
    PreparedStatement ps;
    Statement st;
    try {
        st = con.createStatement();
        String sql = "select * from account";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            String id = String.valueOf(rs.getInt("id"));
            String username = rs.getString("userName");
            String passWord = rs.getString("Password");
            String gender = rs.getString("Gender");
            String Phone = rs.getString("Phone");
            String fullName = rs.getString("FullName");
            String tbData[] = {id, username, passWord, gender, Phone, fullName};
            DefaultTableModel tblModel;
            tblModel = (DefaultTableModel)jTableCustomer.getModel();
            tblModel.addRow(tbData);
            
        }
    } catch (Exception ex) {
        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
    }

//        Connection con = myConnection.getConnection();
//        PreparedStatement ps;
//        Statement st;
//        try {
//            st = con.createStatement();
//            String sql  = "select * from account";
//            ResultSet rs = st.executeQuery(sql);
//            while(rs.next()){
//                String id = String.valueOf(rs.getInt("id"));
//                String username = rs.getString("userName");
//                String  passWord = rs.getString("Password");
//                String gender = rs.getString("Gender");
//                String Phone =rs.getString("Phone");
//                String fullName = rs.getString("FullName");
//                String tbData[] ={id ,username,passWord,gender,Phone,fullName};
//             //   DefaultTableModel tblModel = (DefaultTableModel)jTableUser.getModel();
//                DefaultTableModel tblModel = (DefaultTableModel)jTableUser.getModel();
//                
//                tblModel.addRow(tbData);
//                
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        }
//     

//        Connection con = myConnection.getConnection();
//        PreparedStatement ps;
//        Statement st;
//        try {
//            st = con.createStatement();
//            String sql  = "select * from account";
//            ResultSet rs = st.executeQuery(sql);
//            while(rs.next()){
//                String id = String.valueOf(rs.getInt("id"));
//                String username = rs.getString("userName");
//                String  passWord = rs.getString("Password");
//                String gender = rs.getString("Gender");
//                String Phone =rs.getString("Phone");
//                String fullName = rs.getString("FullName");
//                String tbData[] ={id ,username,passWord,gender,Phone,fullName};
//             //   DefaultTableModel tblModel = (DefaultTableModel)jTableUser.getModel();
//                DefaultTableModel tblModel = (DefaultTableModel)jTableUser.getModel();
//                
//                tblModel.addRow(tbData);
//                
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//        }
//     
        
        
        
    }
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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Menu menu = new Menu();
                menu.setVisible(true);
                menu.setHeaderTable();
                menu.getData();// get data from database;
                
                System.out.println("cao dcu tin");
            }
        });
    }

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelCustomers;
    private javax.swing.JPanel jPanelEmployee;
    private javax.swing.JPanel jPanelLogout;
    private javax.swing.JPanel jPanelProducts;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCustomer;
    // End of variables declaration//GEN-END:variables
}
