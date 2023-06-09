/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package javaapplication3;

import Database.myConnection;
import dto.ManipulateComponents;
import dto.NhanVien;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Menu;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Le Q. Tien
 */
public class ThongTinNhanVien_ThemNV extends javax.swing.JFrame {

    /**
     * Creates new form DanhSachNhanVien
     */
    
    
    public ThongTinNhanVien_ThemNV() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.show_Nhanvien("SELECT * FROM Nhanvien");
        ManipulateComponents Thaotac = new ManipulateComponents();
        Thaotac.setHeaderTableTest(jTableEmployee);
        //   Thaotac.setHeaderTable(jTableEmployee);

    }
    
    
    //----------------------------------------------------------------------------------------------
    private static Image getImageFromIcon(Icon icon) {
            if (icon != null && icon instanceof ImageIcon) {
                return ((ImageIcon) icon).getImage();
            }
            return null;
        }
    
    
    public byte[] covertJlabelToByte(JLabel name) throws IOException{
                
        Image image1 = getImageFromIcon(name.getIcon());
        byte[] imageBytes1 = imageToBytes(image1);
        
        return imageBytes1;
        
        
        
    }
    
    
        //----------------------------------------------------------------------------------------------
     private static byte[] imageToBytes(Image image) throws IOException { //chuyển đổi hình anh sang byte
            if (image != null) {
                BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
                        BufferedImage.TYPE_INT_RGB);
                bufferedImage.getGraphics().drawImage(image, 0, 0, null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", baos);
                baos.flush();
                byte[] imageBytes = baos.toByteArray();
                baos.close();
                return imageBytes;
            }
            return null;
     }
    public void CopyTable() {

        try {
            // Connect to the database
            Connection con = myConnection.getConnection();

            // Get the rows to copy from table 1
            String selectQuery = "SELECT * FROM Nhanvien WHERE MaNV NOT IN (SELECT id FROM account)";
            PreparedStatement selectStmt = con.prepareStatement(selectQuery);
            ResultSet rowsToCopy = selectStmt.executeQuery();

            // Copy rows to table 2
            String insertQuery = "INSERT INTO account (id, Password) VALUES (?, ?)";
            PreparedStatement insertStmt = con.prepareStatement(insertQuery);
            while (rowsToCopy.next()) {
                insertStmt.setString(1, rowsToCopy.getString("MaNV"));
                insertStmt.setString(2, rowsToCopy.getString("Password"));
          
                insertStmt.executeUpdate();
            }
            
            // Close statements and connection
            selectStmt.close();
            insertStmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

    
        

    //tim kiem
    public void findDataNhanVien() {

        String condition = TextFieldID.getText();
        String HoTen = TextFieldhoTen1.getText();
        String Chucvu = (String) jComboBox1ChucVu.getSelectedItem();
        this.clearTable();

        String sql = "SELECT * FROM Nhanvien WHERE 1=1";
        if (!condition.equals("")) {
            sql += " AND MaNV LIKE '" + condition + "'";
        }
        if (!HoTen.equals("")) {
            sql += " AND Hoten LIKE '%" + HoTen + "%'";
        }
        if (!Chucvu.equals("Không chọn")) {
            sql += " AND Chucvu = '" + Chucvu + "'";
        }
        this.show_Nhanvien(sql);

    }
    
    
    //----------------------------------------------------------------------------------------------
    public void getDataWhenClick(int row){
          

        jTextFieldMaNV.setText(jTableEmployee.getValueAt(row, 0).toString());

        IDNhanvien.setText(jTableEmployee.getValueAt(row, 0).toString());
        IDNhanvien.setVisible(true);
        System.out.print(IDNhanvien.getText());

        jTextFieldMaNV.setVisible(false);

        jTextFieldHoTen.setText(jTableEmployee.getValueAt(row, 1).toString());

        String Gioitinh = jTableEmployee.getValueAt(row, 3).toString();
        System.out.print(Gioitinh);
        if (jRadioButtonNam.getText().equals(Gioitinh)) {
            jRadioButtonNam.setSelected(true);

        } else {
            jRadioButtonNu.setSelected(true);
        }

        jTextFieldCCCD.setText(jTableEmployee.getValueAt(row, 2).toString());

        String dateNSSV = jTableEmployee.getValueAt(row, 4).toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(dateNSSV);
            jDateChooserNSNV.setDate((date));
        } catch (ParseException ex) {
            Logger.getLogger(ThongTinNhanVien_ThemNV.class.getName()).log(Level.SEVERE, null, ex);
        }

        // jDateChooserNSNV.setDate((Date) date.getValueAt(row,4));
        jTextFieldDiaChi.setText(jTableEmployee.getValueAt(row, 5).toString());
        jComboBoxChucVu.setSelectedItem(jTableEmployee.getValueAt(row, 6).toString());
        jTextFieldSDT.setText(jTableEmployee.getValueAt(row, 7).toString());
        PassWordNv.setText(jTableEmployee.getValueAt(row, 8).toString());
        String dateNBD = jTableEmployee.getValueAt(row, 9).toString();
        String sql = "SELECT * FROM Nhanvien Where MaNV = "+ IDNhanvien.getText();
        ArrayList<NhanVien> list = userList(sql);
        try {
            // Convert the byte[] array to a BufferedImage object
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(list.get(0).getImageData()));
            ImageIcon icon = new ImageIcon(image.getScaledInstance(jLabelHanhNV.getWidth(), jLabelHanhNV.getHeight(), Image.SCALE_SMOOTH));
            jLabelHanhNV.setIcon(icon);
        } catch (IOException ex) {
            Logger.getLogger(ThongTinNhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        try {
            Date date = dateFormat.parse(dateNBD);
            jDateChooserNBD.setDate((date));
        } catch (ParseException ex) {
            Logger.getLogger(ThongTinNhanVien_ThemNV.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TextFieldID = new javax.swing.JTextField();
        jComboBox1ChucVu = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        TextFieldhoTen1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmployee = new javax.swing.JTable();
        jPanelThemNV = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabelHanhNV = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        PassWordNv = new javax.swing.JTextField();
        jTextFieldSDT = new javax.swing.JTextField();
        jTextFieldDiaChi = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldMaNV = new javax.swing.JTextField();
        jTextFieldHoTen = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldCCCD = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jButtonADD = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jComboBoxChucVu = new javax.swing.JComboBox<>();
        jDateChooserNSNV = new com.toedter.calendar.JDateChooser();
        jDateChooserNBD = new com.toedter.calendar.JDateChooser();
        jRadioButtonNam = new javax.swing.JRadioButton();
        jRadioButtonNu = new javax.swing.JRadioButton();
        IDNhanvien = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButtonSua = new javax.swing.JButton();
        jButtonThem = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
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

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setPreferredSize(new java.awt.Dimension(1200, 160));
        jPanel4.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Nhân viên");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(31, 19, 124, 30);

        TextFieldID.setToolTipText("");
        TextFieldID.setActionCommand("");
        TextFieldID.setDisabledTextColor(java.awt.Color.lightGray);
        TextFieldID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TextFieldIDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TextFieldIDMouseExited(evt);
            }
        });
        TextFieldID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldIDActionPerformed(evt);
            }
        });
        jPanel4.add(TextFieldID);
        TextFieldID.setBounds(60, 80, 120, 35);

        jComboBox1ChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không chọn", "Nhân viên bán hàng", "Nhân viên sửa chữa" }));
        jComboBox1ChucVu.setToolTipText("Chức vụ");
        jComboBox1ChucVu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jComboBox1ChucVu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBox1ChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ChucVuActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox1ChucVu);
        jComboBox1ChucVu.setBounds(540, 80, 150, 35);

        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);
        jButton1.setBounds(870, 70, 151, 50);

        TextFieldhoTen1.setToolTipText("Nhập Tên");
        TextFieldhoTen1.setActionCommand("Nhập tên");
        TextFieldhoTen1.setDisabledTextColor(java.awt.Color.lightGray);
        TextFieldhoTen1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TextFieldhoTen1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                TextFieldhoTen1MouseExited(evt);
            }
        });
        TextFieldhoTen1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldhoTen1ActionPerformed(evt);
            }
        });
        jPanel4.add(TextFieldhoTen1);
        TextFieldhoTen1.setBounds(290, 80, 120, 35);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("ID:");
        jPanel4.add(jLabel4);
        jLabel4.setBounds(31, 94, 30, 17);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Họ Tên:");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(220, 90, 60, 17);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Chức vụ:");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(460, 90, 60, 17);

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 1190, 160));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane1.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jTableEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Họ tên", "CCCD", "Giới tính", "Ngày sinh", "Địa chỉ", "Chức vụ", "SĐT", "Password", "Ngày bắt đầu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEmployee.setRowHeight(25);
        jTableEmployee.setSelectionBackground(new java.awt.Color(0, 102, 255));
        jTableEmployee.getTableHeader().setReorderingAllowed(false);
        jTableEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEmployeeMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableEmployeeMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEmployee);
        if (jTableEmployee.getColumnModel().getColumnCount() > 0) {
            jTableEmployee.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableEmployee.getColumnModel().getColumn(1).setResizable(false);
            jTableEmployee.getColumnModel().getColumn(2).setResizable(false);
            jTableEmployee.getColumnModel().getColumn(3).setResizable(false);
            jTableEmployee.getColumnModel().getColumn(4).setResizable(false);
            jTableEmployee.getColumnModel().getColumn(5).setResizable(false);
            jTableEmployee.getColumnModel().getColumn(6).setResizable(false);
            jTableEmployee.getColumnModel().getColumn(7).setResizable(false);
            jTableEmployee.getColumnModel().getColumn(8).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 560, 1190, 240));

        jPanelThemNV.setLayout(null);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/USER1.png"))); // NOI18N
        jPanelThemNV.add(jLabel8);
        jLabel8.setBounds(1331, 52, 200, 230);

        jLabelHanhNV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/USER1.png"))); // NOI18N
        jLabelHanhNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelHanhNVMouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setText("Thông tin nhân viên");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Địa chỉ:");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel20.setFocusable(false);
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("SĐT:");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel21.setFocusable(false);
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Password:");
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel22.setFocusable(false);
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextFieldDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDiaChiActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("ID:");
        jLabel24.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel24.setFocusable(false);
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Ho tên:");
        jLabel25.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel25.setFocusable(false);
        jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextFieldHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHoTenActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Giới tính:");
        jLabel26.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel26.setFocusable(false);
        jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("CCCD:");
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel27.setFocusable(false);
        jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Ngày sinh:");
        jLabel29.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel29.setFocusable(false);
        jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Ngày bắt đầu:");
        jLabel30.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel30.setFocusable(false);
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButtonADD.setText("Thêm");
        jButtonADD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonADDMouseClicked(evt);
            }
        });
        jButtonADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonADDActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Chức vụ:");
        jLabel31.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel31.setFocusable(false);
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBoxChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên bán hàng", "Nhân viên sửa chữa" }));

        buttonGroup1.add(jRadioButtonNam);
        jRadioButtonNam.setText("Nam");
        jRadioButtonNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonNu);
        jRadioButtonNu.setText("Nữ");
        jRadioButtonNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNuActionPerformed(evt);
            }
        });

        IDNhanvien.setBackground(new java.awt.Color(51, 255, 0));
        IDNhanvien.setToolTipText("");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(jLabel19))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabelHanhNV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextFieldHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextFieldCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextFieldMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jRadioButtonNam)
                                            .addGap(36, 36, 36)
                                            .addComponent(jRadioButtonNu)))
                                    .addGap(36, 36, 36)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextFieldSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(PassWordNv, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBoxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jDateChooserNSNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextFieldDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonADD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel30)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jDateChooserNBD, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(IDNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(IDNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButtonNam)
                                    .addComponent(jRadioButtonNu))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PassWordNv, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jDateChooserNSNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jDateChooserNBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonADD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelHanhNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanelThemNV.add(jPanel6);
        jPanel6.setBounds(-20, 0, 930, 320);
        jPanelThemNV.add(jTextField17);
        jTextField17.setBounds(1631, 202, 200, 25);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("CCCD:");
        jLabel28.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel28.setFocusable(false);
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelThemNV.add(jLabel28);
        jLabel28.setBounds(1549, 201, 70, 25);

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButtonSua.setText("Sửa");
        jButtonSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSuaMouseClicked(evt);
            }
        });
        jButtonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaActionPerformed(evt);
            }
        });

        jButtonThem.setText("Thêm");
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Xóa");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButton10.setText("Cancel");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonSua, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jButtonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanelThemNV.add(jPanel8);
        jPanel8.setBounds(909, 0, 234, 330);

        getContentPane().add(jPanelThemNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 1140, 330));

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
                .addGap(28, 28, 28)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelEmployeeLayout.setVerticalGroup(
            jPanelEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmployeeLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addContainerGap(28, Short.MAX_VALUE))
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelCustomersMouseClicked(evt);
            }
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCustomersLayout.setVerticalGroup(
            jPanelCustomersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCustomersLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel12)
                .addContainerGap(23, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
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
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelEmployee, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jPanelProducts, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCustomers, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 325, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 730));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ChucVuActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void TextFieldhoTen1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextFieldhoTen1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldhoTen1MouseEntered

    private void TextFieldhoTen1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextFieldhoTen1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldhoTen1MouseExited

    private void TextFieldhoTen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldhoTen1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldhoTen1ActionPerformed

    private void TextFieldIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldIDActionPerformed

    private void TextFieldIDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextFieldIDMouseExited
        // TODO add your handling code here:
        //        TextFieldhoTen.setText("Nhập ID...");
        //        TextFieldhoTen1.setText("Nhập Tên...");
    }//GEN-LAST:event_TextFieldIDMouseExited

    private void TextFieldIDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextFieldIDMouseEntered
        // TODO add your handling code here:
        //        TextFieldhoTen.setText(" ");
        //        TextFieldhoTen1.setText(" ");
    }//GEN-LAST:event_TextFieldIDMouseEntered

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextFieldHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldHoTenActionPerformed

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        // TODO add your handling code here:
        jTextFieldMaNV.setVisible(true);
        IDNhanvien.setVisible(false);
        this.ClearTextField();


    }//GEN-LAST:event_jButtonThemActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        try {
            String sqlQuery = "DELETE FROM Nhanvien WHERE MaNV = ?";
            DatabaseHelper dbHelper = new DatabaseHelper();

            // Call the deleteData() method with the appropriate id parameter to delete the row(s) from the table
            dbHelper.deleteData(jTextFieldMaNV.getText(), sqlQuery);

            // Close the database connection when you are finished
            System.out.print(jTextFieldMaNV.getText());
            dbHelper.close();

            //delete the seleted row of the table
            DefaultTableModel tblModel = (DefaultTableModel) jTableEmployee.getModel();
            if (jTableEmployee.getSelectedColumnCount() == 1) {// it return the number of the selected row
                //if the single row is selected than delete
                tblModel.removeRow(jTableEmployee.getSelectedRow());

            } else {
                JOptionPane.showMessageDialog(this, "this table now is empty");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jRadioButtonNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonNamActionPerformed

    private void jRadioButtonNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonNuActionPerformed

    private void jButtonSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSuaMouseClicked

    }//GEN-LAST:event_jButtonSuaMouseClicked

    private void jButtonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaActionPerformed
        // TODO add your handling code here:

        PreparedStatement ps;
        Statement st;
        try {

            Connection con = myConnection.getConnection();

            st = con.createStatement();
            //  DefaultTableModel tblModel = (DefaultTableModel) jTableEmployee.getModel();

            String sql = "UPdate Nhanvien set  Hoten =?, CCCD = ?, Gioitinh=?, Ngaysinh=?, DiaChi=?, ChucVu=?, SDT=?, Password=?, NgayBatDau=?, hinhanh =? where MaNV = ?";
            ps = con.prepareStatement(sql);
            ps.setString(11, jTextFieldMaNV.getText());
            ps.setString(1, jTextFieldHoTen.getText());
            ps.setString(2, jTextFieldCCCD.getText());
            if (jRadioButtonNam.isSelected()) {
                ps.setString(3, jRadioButtonNam.getText());

            } else {
                ps.setString(3, jRadioButtonNu.getText());
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(jDateChooserNSNV.getDate());
            ps.setString(4, date);
            ps.setString(5, jTextFieldDiaChi.getText());
            ps.setString(6, (String) jComboBoxChucVu.getSelectedItem());
            ps.setString(7, jTextFieldSDT.getText());
            ps.setString(8, PassWordNv.getText());

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            String NgayBD = sdf1.format(jDateChooserNBD.getDate());

            ps.setString(9, NgayBD);
            ps.setBytes(10, covertJlabelToByte(jLabelHanhNV));
            if (ps.executeUpdate() == 1) {//this function will returns a value other than 0 when it execute suscessf . otherwise it return 
                JOptionPane.showMessageDialog(this, "You create succesful ");
                clearTable(); // delete the duplicate because when you click "Thêm  " then it will be duplicate 
                ClearTextField(); // delete the text on  textfield
                this.show_Nhanvien("SELECT * FROM Nhanvien");
            } else {
                JOptionPane.showMessageDialog(this, "Something Wrongs");
            }

        } catch (Exception ex) {

            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);

        }


    }//GEN-LAST:event_jButtonSuaActionPerformed

    private void jButtonADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonADDActionPerformed
        // TODO add your handling code here:
        this.getDataNhanVienFromDataBase();
        this.CopyTable();

    }//GEN-LAST:event_jButtonADDActionPerformed

    private void jButtonADDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonADDMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonADDMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //   jPanelThemNV.setVisible(false);
        //   ManipulateComponents manipulate = new ManipulateComponents();
        //manipulate.hideJPanel(jPanelThemNV);
        findDataNhanVien();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTableEmployeeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEmployeeMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableEmployeeMousePressed

    private void jTableEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEmployeeMouseClicked
        // TODO add your handling code here:// when i click the table . it will show the data to jtextField
        int row = jTableEmployee.rowAtPoint(evt.getPoint());
        this.getDataWhenClick(row);
    }//GEN-LAST:event_jTableEmployeeMouseClicked

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jTextFieldDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDiaChiActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanelEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEmployeeMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanelEmployeeMouseClicked

    private void jPanelEmployeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEmployeeMouseEntered
        // TODO add your handling code here:
        jPanelEmployee.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_jPanelEmployeeMouseEntered

    private void jPanelEmployeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelEmployeeMouseExited
        // TODO add your handling code here:
        jPanelEmployee.setBackground(new Color(51, 52, 72));
    }//GEN-LAST:event_jPanelEmployeeMouseExited

    private void jPanelCustomersMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCustomersMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelCustomersMouseMoved

    private void jPanelCustomersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCustomersMouseEntered
        // TODO add your handling code here:
        jPanelCustomers.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_jPanelCustomersMouseEntered

    private void jPanelCustomersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCustomersMouseExited
        // TODO add your handling code here:
        jPanelCustomers.setBackground(new Color(51, 52, 72));
    }//GEN-LAST:event_jPanelCustomersMouseExited

    private void jPanelProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProductsMouseClicked
        // TODO add your handling code here:
        ThongTinSanPham_Admin supf = new ThongTinSanPham_Admin();
        ManipulateComponents xuli = new ManipulateComponents();

        xuli.ChangeJframe(supf, this);
    }//GEN-LAST:event_jPanelProductsMouseClicked

    private void jPanelProductsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProductsMouseEntered
        // TODO add your handling code here:
        jPanelProducts.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_jPanelProductsMouseEntered

    private void jPanelProductsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProductsMouseExited
        // TODO add your handling code here:
        jPanelProducts.setBackground(new Color(51, 52, 72));
    }//GEN-LAST:event_jPanelProductsMouseExited

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered
        // TODO add your handling code here:
        jPanel9.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_jPanel9MouseEntered

    private void jPanel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseExited
        // TODO add your handling code here:
        jPanel9.setBackground(new Color(51, 52, 72));
    }//GEN-LAST:event_jPanel9MouseExited

    private void jPanelCustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCustomersMouseClicked
        // TODO add your handling code here:
        HoaDon supf = new HoaDon();

        ManipulateComponents xuli = new ManipulateComponents();

        xuli.ChangeJframe(supf, this);
    }//GEN-LAST:event_jPanelCustomersMouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        BangPhanCong_Admin supf = new BangPhanCong_Admin();

        ManipulateComponents xuli = new ManipulateComponents();

        xuli.ChangeJframe(supf, this);

    }//GEN-LAST:event_jPanel9MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        ManipulateComponents navigate = new ManipulateComponents();
        LoginForm login = new LoginForm();
        navigate.ChangeJframe(login, this);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jLabelHanhNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHanhNVMouseClicked
        // TODO add your handling code here:
        NhanVien SanPham = new NhanVien();
        SanPham.uploadImageNhanvien(jLabelHanhNV, this);
    }//GEN-LAST:event_jLabelHanhNVMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongTinNhanVien_ThemNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinNhanVien_ThemNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinNhanVien_ThemNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinNhanVien_ThemNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThongTinNhanVien_ThemNV ThongTinNhanVien_ThemNV = new ThongTinNhanVien_ThemNV();
                ThongTinNhanVien_ThemNV.setVisible(true);

                // ThongTinNhanVien_ThemNV.setHeaderTable();
                //    new ThongTinNhanVien_ThemNV().setVisible(true);
                //  ThongTinNhanVien_ThemNV.show_Nhanvien(); // this method will get the data from database and insert the data to table
            }
        });
    }

    public void getDataNhanVienFromDataBase() {

        PreparedStatement ps;
        Statement st;
        try {

            if (Check_Data()) {
                Connection con = myConnection.getConnection();
                st = con.createStatement();
                //  DefaultTableModel tblModel = (DefaultTableModel) jTableEmployee.getModel();

                String sql = "INSERT INTO `Nhanvien` (`MaNV`, `Hoten`, `CCCD`, `Gioitinh`, `Ngaysinh`, `DiaChi`, `ChucVu`, `SDT`, `Password`, `NgayBatDau`,`hinhanh`) VALUES (?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, jTextFieldMaNV.getText());
                ps.setString(2, jTextFieldHoTen.getText());
                ps.setString(3, jTextFieldCCCD.getText());
                if (jRadioButtonNam.isSelected()) {
                    ps.setString(4, jRadioButtonNam.getText());

                } else {
                    ps.setString(4, jRadioButtonNu.getText());
                }

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(jDateChooserNSNV.getDate());
                ps.setString(5, date);
                ps.setString(6, jTextFieldDiaChi.getText());
                ps.setString(7, (String) jComboBoxChucVu.getSelectedItem());
                ps.setString(8, jTextFieldSDT.getText());
                ps.setString(9, PassWordNv.getText());
                ps.setBytes(11, covertJlabelToByte(jLabelHanhNV));
                

                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                String NgayBD = sdf1.format(jDateChooserNBD.getDate());

                ps.setString(10, NgayBD);
                if (ps.executeUpdate() == 1) {//this function will returns a value other than 0 when it execute suscessf . otherwise it return 
                    JOptionPane.showMessageDialog(this, "You create succesful ");
                    clearTable(); // delete the duplicate because when you click "Thêm  " then it will be duplicate 
                    ClearTextField(); // delete the text on  textfield
                    this.show_Nhanvien("SELECT * FROM Nhanvien");
                } else {
                    JOptionPane.showMessageDialog(this, "Something Wrongs");
                }
            }

        } catch (Exception ex) {

            //Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex);

        }

    }

    private void ClearTextField() {// delete all the text feild
        jTextFieldMaNV.setText(null);
        jTextFieldHoTen.setText(null);
        jTextFieldCCCD.setText(null);
        jRadioButtonNam.setSelected(false);
        jRadioButtonNu.setSelected(false);
        jTextFieldDiaChi.setText(null);
        PassWordNv.setText(null);
        jTextFieldSDT.setText(null);
        jDateChooserNSNV.setDate(null);
        jDateChooserNBD.setDate(null);

    }

    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) jTableEmployee.getModel();
        model.setRowCount(0);
    }

    public ArrayList<NhanVien> userList(String sql) {

        ArrayList<NhanVien> usersList = new ArrayList();
        PreparedStatement ps;
        Statement st;
        ResultSet rs;
        try {
            Connection con = myConnection.getConnection();
            st = con.createStatement();
            String query1 = sql;
            rs = st.executeQuery(query1);
            NhanVien nhanvien;
            while (rs.next()) {
                nhanvien = new NhanVien(rs.getInt("MaNV"), rs.getString("Hoten"), rs.getString("CCCD"), rs.getString("Gioitinh"), rs.getDate("Ngaysinh"), rs.getString("DiaChi"),
                         rs.getString("ChucVu"), rs.getString("SDT"), rs.getString("PassWord"), rs.getDate("NgayBatDau"));
                nhanvien.setImageData(rs.getBytes("hinhanh"));
                usersList.add(nhanvien);//add all data to userlist

            }

        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usersList;

    }

    public boolean checkUserExists() {
        ResultSet rs;
        PreparedStatement ps;
        boolean check = false;
        try {
            // TODO add your handling code here:

            Connection con = myConnection.getConnection();
            ps = con.prepareStatement("SELECT * FROM `Nhanvien` WHERE `MaNV`=? OR `CCCD`=? OR `SDT`=?");
            ps.setString(1, jTextFieldMaNV.getText());
            ps.setString(2, jTextFieldCCCD.getText());
            ps.setString(3, jTextFieldSDT.getText());

            rs = ps.executeQuery();
            if (rs.next()) {//If it matching column is found, it will return true

                check = true;

            } else {

                check = false;
            }

        } catch (Exception ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        return check;

    }

    public boolean Check_Data() {
        Boolean check = true;
        if (!jTextFieldMaNV.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(rootPane, "MaNV  must be a number string");
            check = false;

        } else if (!jTextFieldCCCD.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(rootPane, "CCCD  must be a number string");
            check = false;

        } else if (!jTextFieldSDT.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(rootPane, "SDT  must be a number string");
            check = false;
        } else if (checkUserExists()) {
            JOptionPane.showMessageDialog(rootPane, "ID , CCCD or phone maybe duplicate");
            check = false;
        }
        return check;

    }

    public void show_Nhanvien(String sql) {
        ArrayList<NhanVien> list = userList(sql);

        DefaultTableModel model = (DefaultTableModel) jTableEmployee.getModel();

        Object[] row = new Object[10];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getMaNV();
            row[1] = list.get(i).getHoten();
            row[2] = list.get(i).getCCCD();
            row[3] = list.get(i).getGioitinh();
            row[4] = list.get(i).getNgaySinh();
            row[5] = list.get(i).getDiaChi();
            row[6] = list.get(i).getChucVu();
            row[7] = list.get(i).getSDT();
            row[8] = list.get(i).getPassword();
            row[9] = list.get(i).getNgayBatDau();

            model.addRow(row);

        }
    }

    public void setHeaderTable() {//set header table

        jTableEmployee.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));//set the font and the size for header of table
        DefaultTableCellRenderer head_render = new DefaultTableCellRenderer();
        head_render.setBackground(new Color(204, 153, 255));
        jTableEmployee.getTableHeader().setPreferredSize(new Dimension(jTableEmployee.getTableHeader().getWidth(), 25)); //set the with and height for hearder table

        jTableEmployee.getTableHeader().setDefaultRenderer(head_render);//set color for header table

        jTableEmployee.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    public void getDataFromDatabase() { // get data from database and push data to table (tbmodel)
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        Statement st;
        try {
            st = con.createStatement();
            String sql = "select * from Nhanvien";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String MaNV = String.valueOf(rs.getInt("MaNV"));
                String Hoten = rs.getString("Hoten");
                String CCCD = rs.getString("CCCD");
                String Gioitinh = rs.getString("Gioitinh");
                String NgaySinh = rs.getString("Ngaysinh");
                String DiaChi = rs.getString("DiaChi");
                String ChucVu = rs.getString("ChucVu");
                String SDT = rs.getString("SDT");
                String Password = rs.getString("Password");
                String NgayBD = rs.getString("NgayBatDau");

                String tbData[] = {MaNV, Hoten, CCCD, Gioitinh, NgaySinh, DiaChi, ChucVu, SDT, Password, NgayBD};
                DefaultTableModel tblModel;
                tblModel = (DefaultTableModel) jTableEmployee.getModel();
                tblModel.addRow(tbData);

            }
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IDNhanvien;
    private javax.swing.JTextField PassWordNv;
    private javax.swing.JTextField TextFieldID;
    private javax.swing.JTextField TextFieldhoTen1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonADD;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonSua;
    public javax.swing.JButton jButtonThem;
    private javax.swing.JComboBox<String> jComboBox1ChucVu;
    private javax.swing.JComboBox<String> jComboBoxChucVu;
    private com.toedter.calendar.JDateChooser jDateChooserNBD;
    private com.toedter.calendar.JDateChooser jDateChooserNSNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelHanhNV;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelCustomers;
    private javax.swing.JPanel jPanelEmployee;
    private javax.swing.JPanel jPanelProducts;
    private javax.swing.JPanel jPanelThemNV;
    private javax.swing.JRadioButton jRadioButtonNam;
    private javax.swing.JRadioButton jRadioButtonNu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEmployee;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextFieldCCCD;
    private javax.swing.JTextField jTextFieldDiaChi;
    private javax.swing.JTextField jTextFieldHoTen;
    private javax.swing.JTextField jTextFieldMaNV;
    private javax.swing.JTextField jTextFieldSDT;
    // End of variables declaration//GEN-END:variables
}
