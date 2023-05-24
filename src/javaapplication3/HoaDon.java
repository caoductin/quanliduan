
package javaapplication3;


import DAO.Currency;
import DAO.XuatHD;
import static DAO.XuatHD.Update;
import static DAO.XuLiHoaDon.CTHD;
import static DAO.XuLiHoaDon.TaoHD;
import static DAO.DAOSanPham.filter;
import static DAO.DAOSanPham.LoadDataTrademarkComBoBox;
import static DAO.DAOSanPham.LoadDataCategoryComBoBox;
import static DAO.DAOSanPham.DanhSachSanPham;
import static DAO.XuLiHoaDon.LoadDataComBoBox;
import static DAO.XuLiHoaDon.DanhSachHoaDon;
import dto.ChiTietHoaDon;
import dto.ManipulateComponents;
import dto.SanPham;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class HoaDon extends javax.swing.JFrame {

    
    public HoaDon() {
        initComponents();
        this.setLocationRelativeTo(null);
        //this.show_DonHang("select * from chitiethoadon join sanpham on chitiethoadon.MaSanPham = sanpham.MaSanPham");
        this.ShowTenNV();
        this.show_SanPham("SELECT * FROM SanPham");
        //show_DonHang();
        this.show_Category();
        this.show_Trademark();
    }
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) jTableSanPham.getModel();
        model.setRowCount(0);
    }
    
    
    private void ShowTenNV(){
        ArrayList<String> datalist = LoadDataComBoBox();
        for (String string : datalist) {
        TenNguoiBan.addItem(string);
        }
    }
    
     private void  show_DonHang(){
        int maHD = Integer.parseInt(MaHoaDonText.getText());
         Map<SanPham, ChiTietHoaDon> danhSachHoaDon = DanhSachHoaDon(maHD);
        DefaultTableModel model =(DefaultTableModel)jTableSanPham.getModel();

        Object[] row = new Object[10];
        for(Map.Entry<SanPham, ChiTietHoaDon> entry : danhSachHoaDon.entrySet()){
            SanPham sp = entry.getKey();
            ChiTietHoaDon cthd = entry.getValue();
            row[0] = sp.getMaSanPham();
            row[1] = sp.getTenSanPham();
            row[2] = cthd.getSoLuong();
            row[3] = sp.getGia();
            row[4] = cthd.getTongTien();
            
            DefaultTableCellRenderer currencyRenderer = new Currency();
            jTableSanPham.getColumnModel().getColumn(3).setCellRenderer(currencyRenderer);
            jTableSanPham.getColumnModel().getColumn(4).setCellRenderer(currencyRenderer);
            
            model.addRow (row);
        }
    }
     
     private void show_SanPham(String sql){
         ArrayList<SanPham> DanhSachSanPham = DanhSachSanPham(sql);
         DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
         
         Object[] row = new Object[6];
         for (int i = 0; i<DanhSachSanPham.size(); i++){
             row[0] = DanhSachSanPham.get(i).getMaSanPham();
             row[1] = DanhSachSanPham.get(i).getLoaiSanPham();
             row[2] = DanhSachSanPham.get(i).getTenSanPham();
             row[3] = DanhSachSanPham.get(i).getThuongHieu();
             row[4] = DanhSachSanPham.get(i).getGia();
             row[5] = DanhSachSanPham.get(i).getSoLuong();
             
             DefaultTableCellRenderer currencyRenderer = new Currency();
             tblSanPham.getColumnModel().getColumn(4).setCellRenderer(currencyRenderer);
             
             model.addRow(row);
         }
     }
     
     private void show_Category(){
         ArrayList<String> category = LoadDataCategoryComBoBox();
         cbbLoaiSP.addItem("Tất cả");
         for (String list : category) {
             cbbLoaiSP.addItem(list);
         }
     }
     
     private void show_Trademark(){
         ArrayList<String> trademark = LoadDataTrademarkComBoBox();
         cbbThuongHieu.addItem("Tất cả");
         
         for (String list : trademark) {
             cbbThuongHieu.addItem(list);
         }
     }
     
     public SanPham getSelectedSanPham() {
        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow != -1) {
            int maSanPham = Integer.parseInt(tblSanPham.getValueAt(selectedRow, 0).toString());
            String tenSanPham = tblSanPham.getValueAt(selectedRow, 2).toString();
            String loaiSanPham = tblSanPham.getValueAt(selectedRow, 1).toString();
            
            String thuongHieu = tblSanPham.getValueAt(selectedRow, 3).toString();
            double giaBan = Double.parseDouble(tblSanPham.getValueAt(selectedRow, 4).toString());
            return new SanPham(maSanPham, tenSanPham, loaiSanPham, thuongHieu, null, SoLuong, giaBan);
        }
        return null;
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Bar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        MaHoaDonText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TenNguoiBan = new javax.swing.JComboBox<>();
        btnNhapHD = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSanPham = new javax.swing.JTable();
        pnlSanPham = new javax.swing.JPanel();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        txtTimTen = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        cbbLoaiSP = new javax.swing.JComboBox<>();
        btnTimKiem = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnXuatHD = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanelEmployee = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanelCustomers = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanelProducts = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanelPhanCong = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
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
        MaHoaDonText.setBounds(130, 90, 120, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã Hóa Đơn");
        jPanel4.add(jLabel4);
        jLabel4.setBounds(31, 94, 78, 20);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Người bán");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(280, 90, 70, 30);

        TenNguoiBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TenNguoiBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TenNguoiBanActionPerformed(evt);
            }
        });
        jPanel4.add(TenNguoiBan);
        TenNguoiBan.setBounds(360, 90, 200, 30);

        btnNhapHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNhapHD.setText("Tạo Hóa Đơn");
        btnNhapHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHDActionPerformed(evt);
            }
        });
        jPanel4.add(btnNhapHD);
        btnNhapHD.setBounds(620, 90, 140, 30);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 1140, 160));

        jTableSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hàng", "Tên Hàng", "Số Lượng", "Giá", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSanPham.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableSanPham);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 250, 640, 380));

        pnlSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        cbbThuongHieu.setForeground(new java.awt.Color(255, 51, 102));
        cbbThuongHieu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbThuongHieuItemStateChanged(evt);
            }
        });
        cbbThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThuongHieuActionPerformed(evt);
            }
        });

        txtTimTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTimTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimTenActionPerformed(evt);
            }
        });
        txtTimTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimTenKeyReleased(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Loại Sản Phẩm", "Tên", "Thương hiệu", "Giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        cbbLoaiSP.setForeground(new java.awt.Color(255, 51, 102));
        cbbLoaiSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbLoaiSPItemStateChanged(evt);
            }
        });
        cbbLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiSPActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimKiem.setText("Tìm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSanPhamLayout = new javax.swing.GroupLayout(pnlSanPham);
        pnlSanPham.setLayout(pnlSanPhamLayout);
        pnlSanPhamLayout.setHorizontalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addGroup(pnlSanPhamLayout.createSequentialGroup()
                        .addComponent(cbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimTen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlSanPhamLayout.setVerticalGroup(
            pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimTen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 480, 510));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        btnXuatHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXuatHD.setText("Xuất Hóa Đơn");
        btnXuatHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatHDActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Thêm Sản Phẩm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(280, 280, 280)
                .addComponent(btnXuatHD, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuatHD)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 630, 640, 170));

        jPanel3.setBackground(new java.awt.Color(51, 52, 72));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/BACK1.png"))); // NOI18N
        jButton2.setText("Trở về");
        jButton2.setBorder(null);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
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
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelProductsLayout.setVerticalGroup(
            jPanelProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductsLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel14)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanelPhanCong.setBackground(new java.awt.Color(51, 52, 72));
        jPanelPhanCong.setForeground(new java.awt.Color(255, 255, 255));
        jPanelPhanCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelPhanCongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelPhanCongMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelPhanCongMouseExited(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Phân công");

        javax.swing.GroupLayout jPanelPhanCongLayout = new javax.swing.GroupLayout(jPanelPhanCong);
        jPanelPhanCong.setLayout(jPanelPhanCongLayout);
        jPanelPhanCongLayout.setHorizontalGroup(
            jPanelPhanCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPhanCongLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPhanCongLayout.setVerticalGroup(
            jPanelPhanCongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPhanCongLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel13)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPhanCong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCustomers, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanelPhanCong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 325, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 180, 730));

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

    private void MaHoaDonTextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaHoaDonTextMouseEntered
        
    }//GEN-LAST:event_MaHoaDonTextMouseEntered

    private void MaHoaDonTextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaHoaDonTextMouseExited
        
    }//GEN-LAST:event_MaHoaDonTextMouseExited

    private void MaHoaDonTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaHoaDonTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MaHoaDonTextActionPerformed

    private void TenNguoiBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TenNguoiBanActionPerformed
     
    }//GEN-LAST:event_TenNguoiBanActionPerformed

    private void cbbThuongHieuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbThuongHieuItemStateChanged
        
    }//GEN-LAST:event_cbbThuongHieuItemStateChanged

    private void cbbThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThuongHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbThuongHieuActionPerformed

    private void txtTimTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimTenActionPerformed

    private void txtTimTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimTenKeyReleased
       
    }//GEN-LAST:event_txtTimTenKeyReleased

    public static int MaSP;
    public static int SoLuong;
    
    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        
        if(evt.getClickCount() == 2){
           MaSP = (int) tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 0);
           SoLuong = Integer.parseInt(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 5).toString());
           jdlAddSanPham jdl = new jdlAddSanPham(this, true);
           jdl.setVisible(true);
      }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void cbbLoaiSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbLoaiSPItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLoaiSPItemStateChanged

    private void cbbLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbLoaiSPActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        filter(tblSanPham, cbbLoaiSP, cbbThuongHieu, txtTimTen);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    
    private void btnNhapHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHDActionPerformed
       // Lấy giá trị đã chọn từ combobox "TenNguoiBan"
       int maHD = Integer.parseInt(MaHoaDonText.getText());
        TaoHD(maHD,cbbLoaiSP, SoLuong);
    }//GEN-LAST:event_btnNhapHDActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableSanPham.getModel();
        int selectedRow = tblSanPham.getSelectedRow();
        int maHD = Integer.parseInt(MaHoaDonText.getText());
        if (selectedRow != -1) {
            int maSanPham = Integer.parseInt(tblSanPham.getValueAt(selectedRow, 0).toString());
            String tenSanPham = tblSanPham.getValueAt(selectedRow, 2).toString();
            String loaiSanPham = tblSanPham.getValueAt(selectedRow, 1).toString();
            
            String thuongHieu = tblSanPham.getValueAt(selectedRow, 3).toString();
            double giaBan = Double.parseDouble(tblSanPham.getValueAt(selectedRow, 4).toString());
            CTHD(maHD, TenNguoiBan, maSanPham, SoLuong, giaBan);
       }
        model.setRowCount(0);
        show_DonHang();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnXuatHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatHDActionPerformed
        int maHD = Integer.parseInt(MaHoaDonText.getText());
        Update();
        XuatHD.xuatHD(maHD);
    }//GEN-LAST:event_btnXuatHDActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanelEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEmployeeMouseClicked
        // TODO add your handling code here:
        ThongTinNhanVien_ThemNV supf = new ThongTinNhanVien_ThemNV();
        ManipulateComponents xuli = new ManipulateComponents();
               
        xuli.ChangeJframe(supf, this);
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
        // TODOadd your handling code here:
        jPanelCustomers.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_jPanelCustomersMouseEntered

    private void jPanelCustomersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCustomersMouseExited
        // TODO add your handling code here:
        jPanelCustomers.setBackground(new Color(51,52,72));
    }//GEN-LAST:event_jPanelCustomersMouseExited

    private void jPanelProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProductsMouseClicked
        // TODO add your handling code here:
         
        ThongTinSanPham_Admin supf = new ThongTinSanPham_Admin();
        ManipulateComponents xuli = new ManipulateComponents();
               
        xuli.ChangeJframe(supf, this);
    }//GEN-LAST:event_jPanelProductsMouseClicked

    private void jPanelProductsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProductsMouseEntered
        // TODO add your handling code here:
        jPanelProducts.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_jPanelProductsMouseEntered

    private void jPanelProductsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProductsMouseExited
        // TODO add your handling code here:
        jPanelProducts.setBackground(new Color(51,52,72));
    }//GEN-LAST:event_jPanelProductsMouseExited

    private void jPanelPhanCongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelPhanCongMouseEntered
        // TODO add your handling code here:
        jPanelPhanCong.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_jPanelPhanCongMouseEntered

    private void jPanelPhanCongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelPhanCongMouseExited
        // TODO add your handling code here:
        jPanelPhanCong.setBackground(new Color(51,52,72));
    }//GEN-LAST:event_jPanelPhanCongMouseExited

    private void jPanelPhanCongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelPhanCongMouseClicked
        // TODO add your handling code here:
         
        BangPhanCong_Admin supf = new BangPhanCong_Admin();
        ManipulateComponents xuli = new ManipulateComponents();
               
        xuli.ChangeJframe(supf, this);
    }//GEN-LAST:event_jPanelPhanCongMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        ManipulateComponents navigate = new ManipulateComponents();
        LoginForm login = new LoginForm();
        navigate.ChangeJframe(login, this);
    }//GEN-LAST:event_jButton2MouseClicked

    
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
    private javax.swing.JComboBox<String> TenNguoiBan;
    private javax.swing.JButton btnNhapHD;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXuatHD;
    private javax.swing.JComboBox<String> cbbLoaiSP;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelCustomers;
    private javax.swing.JPanel jPanelEmployee;
    private javax.swing.JPanel jPanelPhanCong;
    private javax.swing.JPanel jPanelProducts;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableSanPham;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtTimTen;
    // End of variables declaration//GEN-END:variables
}
