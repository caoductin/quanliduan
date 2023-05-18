
package javaapplication3;


import DAO.XuatHD;
import static DAO.XuatHD.Update;
import static DAO.XuLiHoaDon.CTHD;
import static DAO.XuLiHoaDon.TaoHD;
import DAO.XuLiHoaDon;
import static DAO.XuLiHoaDon.TaoHD;
import static DAO.DAOSanPham.filter;
import static DAO.DAOSanPham.LoadDataTrademarkComBoBox;
import static DAO.DAOSanPham.LoadDataCategoryComBoBox;
import static DAO.DAOSanPham.DanhSachSanPham;
import static DAO.XuLiHoaDon.LoadDataComBoBox;
import static DAO.XuLiHoaDon.DanhSachHoaDon;
import dto.ChiTietHoaDon;
import dto.SanPham;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
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
        jLabel5 = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        TenNguoiBan = new javax.swing.JComboBox<>();
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
        jButton1 = new javax.swing.JButton();
        btnNhapHD = new javax.swing.JButton();
        btnXuatHD = new javax.swing.JButton();

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

        Menu.setBackground(new java.awt.Color(51, 52, 72));
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
        jButton7.setForeground(new java.awt.Color(51, 52, 72));
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
                    .addComponent(jButtonChonSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                .addContainerGap())
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jButtonChonSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabelPhanCong, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
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
        MaHoaDonText.setBounds(130, 90, 120, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã Hóa Đơn");
        jPanel4.add(jLabel4);
        jLabel4.setBounds(31, 94, 78, 20);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Người bán");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(280, 90, 70, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày Bán");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(610, 90, 80, 30);
        jPanel4.add(jDateChooser);
        jDateChooser.setBounds(700, 90, 170, 30);

        TenNguoiBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TenNguoiBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TenNguoiBanActionPerformed(evt);
            }
        });
        jPanel4.add(TenNguoiBan);
        TenNguoiBan.setBounds(360, 90, 200, 30);

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

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Thêm Sản Phẩm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnNhapHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNhapHD.setText("Tạo Hóa Đơn");
        btnNhapHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHDActionPerformed(evt);
            }
        });

        btnXuatHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXuatHD.setText("Xuất Hóa Đơn");
        btnXuatHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(btnNhapHD, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(btnXuatHD, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNhapHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuatHD))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 630, 640, 170));

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
       Date ngayTao = jDateChooser.getDate();
        TaoHD(maHD, ngayTao, cbbLoaiSP, SoLuong);
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    
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
    private javax.swing.JButton btnNhapHD;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXuatHD;
    private javax.swing.JComboBox<String> cbbLoaiSP;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonChonSanPham;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelPhanCong;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableSanPham;
    private javax.swing.JPanel pnlSanPham;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtTimTen;
    // End of variables declaration//GEN-END:variables
}
