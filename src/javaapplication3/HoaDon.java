
package javaapplication3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class HoaDon extends javax.swing.JFrame {

    
    public HoaDon() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.show_SanPham("SELECT * FROM SanPham");
    }
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) jTableSanPham.getModel();
        model.setRowCount(0);
    }
    public ArrayList<SanPham> DanhSachSanPham(String sql){

            ArrayList<SanPham> DanhSachSanPham = new ArrayList();
                PreparedStatement ps;
                Statement st;
                ResultSet rs ;
            try {
                 Connection con = myConnection.getConnection();
                st = con.createStatement();
                String query1 = sql;
                rs = st.executeQuery(query1);
                SanPham sanPhamTemp;
                while(rs.next()){
                    sanPhamTemp = new SanPham(rs.getInt("MaSanPham"),rs.getString("TenSanPham"), rs.getString("LoaiSanPham"),
                            rs.getString("ThuongHieu"), rs.getString("NgayNhap"),rs.getInt("SoLuong"), rs.getDouble("Gia"));
                    

                    DanhSachSanPham.add(sanPhamTemp);//add all data to userlist


                
                        
           }
                   } catch (Exception ex) {
            Logger.getLogger(java.awt.Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        return DanhSachSanPham;
        
        
    }
     public void  show_SanPham(String sql){
        ArrayList<SanPham> list = DanhSachSanPham(sql);
    
        DefaultTableModel model =(DefaultTableModel)jTableSanPham.getModel();

        Object[] row = new Object[10];
        for(int i=0;i<list.size();i++){
            row[0] = list.get(i).getMaSanPham();
            row[1] = list.get(i).getTenSanPham();
            row[2] = list.get(i).getLoaiSanPham();
            row[3] = list.get(i).getThuongHieu();
            row[4] = list.get(i).getSoLuong();
            row[5] = list.get(i).getGia();
            row[6] = list.get(i).getNgayNhap();
         
        
            model.addRow (row);
            
            

        
   }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Bar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        Menu = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButtonChonSanPham = new javax.swing.JButton();
        jLabelPhanCong = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        MaHoaDonText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TenNguoiBan = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSanPham = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1400, 800));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Bar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

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

        javax.swing.GroupLayout BarLayout = new javax.swing.GroupLayout(Bar);
        Bar.setLayout(BarLayout);
        BarLayout.setHorizontalGroup(
            BarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarLayout.createSequentialGroup()
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
        BarLayout.setVerticalGroup(
            BarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarLayout.createSequentialGroup()
                .addGroup(BarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BarLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2))
                    .addGroup(BarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(BarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(Bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 80));

        Menu.setBackground(new java.awt.Color(0, 0, 153));
        Menu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/BACK1.png"))); // NOI18N
        jButton2.setText("Trở về");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/DSNV2.png"))); // NOI18N
        jButton3.setText("Nhân Viên");
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton3.setIconTextGap(5);
        jButton3.setPreferredSize(new java.awt.Dimension(147, 47));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/HELP1.png"))); // NOI18N
        jButton7.setText("Trợ giúp");
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton7.setIconTextGap(5);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButtonChonSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonChonSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/PRODUCT2.png"))); // NOI18N
        jButtonChonSanPham.setText("Sản Phẩm");
        jButtonChonSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonChonSanPham.setIconTextGap(7);
        jButtonChonSanPham.setPreferredSize(new java.awt.Dimension(147, 47));
        jButtonChonSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChonSanPhamActionPerformed(evt);
            }
        });

        jLabelPhanCong.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPhanCong.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabelPhanCong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPhanCong.setText("Phân Công");
        jLabelPhanCong.setOpaque(true);
        jLabelPhanCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPhanCongMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPhanCong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonChonSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MenuLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jButtonChonSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabelPhanCong, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MenuLayout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(647, Short.MAX_VALUE)))
        );

        getContentPane().add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 74, 260, 730));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setPreferredSize(new java.awt.Dimension(1200, 160));
        jPanel4.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("HÓA ĐƠN");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(31, 19, 120, 32);

        MaHoaDonText.setToolTipText("");
        MaHoaDonText.setActionCommand("");
        MaHoaDonText.setDisabledTextColor(java.awt.Color.lightGray);
        MaHoaDonText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MaHoaDonTextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MaHoaDonTextMouseExited(evt);
            }
        });
        MaHoaDonText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaHoaDonTextActionPerformed(evt);
            }
        });
        jPanel4.add(MaHoaDonText);
        MaHoaDonText.setBounds(130, 90, 120, 35);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã Hóa Đơn");
        jPanel4.add(jLabel4);
        jLabel4.setBounds(31, 94, 78, 20);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Người bán");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(280, 90, 70, 30);

        TenNguoiBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TenNguoiBanActionPerformed(evt);
            }
        });
        jPanel4.add(TenNguoiBan);
        TenNguoiBan.setBounds(360, 90, 120, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày Bán");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(510, 90, 80, 30);
        jPanel4.add(jDateChooser1);
        jDateChooser1.setBounds(590, 90, 170, 30);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 1140, 160));

        jTableSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Thương hiệu", "Số lượng", "Giá", "Ngày nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSanPham.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableSanPham);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 540, 560));

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButtonChonSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChonSanPhamActionPerformed
        // TODO add your handling code here:
        ThongTinSanPham_Admin supf = new ThongTinSanPham_Admin ();
        supf.setVisible(true);//visible jfame
        supf.pack();
        supf.setLocationRelativeTo(null);//set position cho jframe

        supf.setDefaultCloseOperation(EXIT_ON_CLOSE);//ham nay khi dong se tat luon
        this.dispose();

    }//GEN-LAST:event_jButtonChonSanPhamActionPerformed

    private void jLabelPhanCongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPhanCongMouseClicked
        // TODO add your handling code here:
        BangPhanCong_Admin navigate = new  BangPhanCong_Admin();
        navigate.setVisible(true);//visible jfame
        navigate.pack();
        navigate.setLocationRelativeTo(null);//set position cho jframe
        navigate.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // this.dispose();
        System.out.println("cao duc tin");

    }//GEN-LAST:event_jLabelPhanCongMouseClicked

    private void MaHoaDonTextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaHoaDonTextMouseEntered
        // TODO add your handling code here:
        //        TextFieldhoTen.setText(" ");
        //        TextFieldhoTen1.setText(" ");
    }//GEN-LAST:event_MaHoaDonTextMouseEntered

    private void MaHoaDonTextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaHoaDonTextMouseExited
        // TODO add your handling code here:
        //        TextFieldhoTen.setText("Nhập ID...");
        //        TextFieldhoTen1.setText("Nhập Tên...");
    }//GEN-LAST:event_MaHoaDonTextMouseExited

    private void MaHoaDonTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaHoaDonTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaHoaDonTextActionPerformed

    private void TenNguoiBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TenNguoiBanActionPerformed
      PreparedStatement ps;
      Statement st;
      ResultSet rs ;
        try {
            Connection con = myConnection.getConnection();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_TenNguoiBanActionPerformed

    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bar;
    private javax.swing.JTextField MaHoaDonText;
    private javax.swing.JPanel Menu;
    private javax.swing.JComboBox<String> TenNguoiBan;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonChonSanPham;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelPhanCong;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableSanPham;
    // End of variables declaration//GEN-END:variables
}
