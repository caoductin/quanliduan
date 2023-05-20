
package javaapplication3;

import Database.myConnection;
import dto.ManipulateComponents;
import dto.SanPham;
import java.awt.Color;
import java.awt.Image;
import java.awt.Menu;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.swing.Icon;
import java.sql.*;
import java.text.ParseException;
import java.util.Arrays;

public class ThongTinSanPham_Admin extends javax.swing.JFrame {

    
    public ThongTinSanPham_Admin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.show_SanPham("SELECT * FROM SanPham");
    }
    
    //----------------------------------------------------------------------------------------------
    public byte[] covertJlabelToByte(JLabel name) throws IOException{
                
        Image image1 = getImageFromIcon(name.getIcon());
        byte[] imageBytes1 = imageToBytes(image1);
        
        return imageBytes1;
        
        
        
    }
      //----------------------------------------------------------------------------------------------
    
    public void UpdateSanPham(){// sửa thông tin sản phẩm
                 
    PreparedStatement ps,ps1;
    Statement st;
    try {
        Connection con = myConnection.getConnection();
        String sql = "UPdate sanpham set  MaSanPham =?, TenSanPham = ?, LoaiSanPham=?, ThuongHieu=?, SoLuong=?, GiaBan=?, NgayNhap=? where MaSanPham = ?";
        ps = con.prepareStatement(sql);
        ps.setString(8, jTextFieldMaSanPham.getText());
        ps.setString(1, jTextFieldMaSanPham.getText());
        ps.setString(2,jTextFieldTenSanPham.getText());
        ps.setString(3, (String) jComboBoxLoaiSanPham.getSelectedItem());
        ps.setString(4,jTextFieldThuongHieu.getText());
        ps.setInt(5, Integer.parseInt(jTextFieldSoLuong.getText()));
        ps.setString(6,jTextFieldGia.getText());
        
        
        SimpleDateFormat sdf1 = new SimpleDateFormat(  "yyyy-MM-dd");
        String NgayNhapHang = sdf1.format(jDateChooserNgayNhap.getDate());
        ps.setString(7,NgayNhapHang);
        
        if(jLabelImage1.getIcon() == null){
        System.out.print("cao duc tin");
   
        }
        else{
         System.out.print("get icon khong bi null"); 
          String sql1 = "update Image set TenHinhAnh = ?, hinhanh = ? WHERE MaSanPham = ?";
        ps1 = con.prepareStatement(sql1);
        ps1.setString(3,jTextFieldMaSanPham.getText());
        ps1.setString(1,getDescriptionJlabel(jLabelImage1));
        ps1.setBytes(2,covertJlabelToByte(jLabelImage1) );
        
        
        ps1.executeUpdate();
        }
      
        
        
        if(ps.executeUpdate() == 1){//this function will returns a value other than 0 when it execute suscessf . otherwise it return 
     
            
                 JOptionPane.showMessageDialog(this,"You create succesful ");
                 clearTable(); // delete the duplicate because when you click "Thêm  " then it will be duplicate 
                 this.clearTextField();
                
                 this.show_SanPham("SELECT * FROM SanPham");

                 }
        else {
                      JOptionPane.showMessageDialog(this,"Vui Lòng Không sửa Mã Sản Phẩm");
                 }

    
     } catch (Exception ex) {
         JOptionPane.showMessageDialog( null,"MySQL error: " + ex.getMessage(), "MySQL Error", JOptionPane.ERROR_MESSAGE);
         System.out.println(ex);
        
    }
        
        
        
        
    }
    
     //----------------------------------------------------------------------------------------------
    //xoá hình ảnh của san phẩm khi người dùng xoá hình ảnh sản phẩm
    public void DeleteImage(String idMaSanPham){
        try{
            String sql = "DELETE FROM `quanliduan`.`Image` Where MaSanPham = ?";
            DatabaseHelper dbHelper = new DatabaseHelper();
            dbHelper.deleteData( idMaSanPham,sql);
            dbHelper.close();
            
        }
       catch (Exception ex) {
         JOptionPane.showMessageDialog( null,"MySQL error: " + ex.getMessage(), "MySQL Error", JOptionPane.ERROR_MESSAGE);
         System.out.println(ex);
    }
        
        
        
    }
    
    
      //----------------------------------------------------------------------------------------------
    //Xoa san pham
    public void DeleteSanPham(){
          try {
             int[] rows = jTableSanPham.getSelectedRows();
            if(rows.length != 0){
        String sqlQuery = "DELETE FROM `quanliduan`.`sanpham` WHERE MaSanPham = ?";
        DatabaseHelper dbHelper = new DatabaseHelper();
      

        for (int row : rows) {
            Object obj = jTableSanPham.getModel().getValueAt(row, 0);
            System.out.print(obj);
            String firstColValue = String.valueOf(obj);
            
            dbHelper.deleteData( firstColValue,sqlQuery);
            this.DeleteImage(firstColValue);//xoá hình ảnh của nhưng id san pham bi 
        }
        dbHelper.close();
                // Close the database connection when you are finished
       // System.out.print(jTextFieldMaSanPham.getText());
        dbHelper.close();
        
        //delete the seleted row of the table
        DefaultTableModel  tblModel = (DefaultTableModel) jTableSanPham.getModel();
        
          for (int i = rows.length - 1; i >= 0; i--) {
                int rowIndex = rows[i];
                tblModel.removeRow(rowIndex);
            }
      
         }
        else {
            JOptionPane.showMessageDialog(this, "vui lòng chọn nhân viên để xoá");
        }
        this.clearTextField();
        
    } catch (Exception ex) {
         JOptionPane.showMessageDialog( null,"MySQL error: " + ex.getMessage(), "MySQL Error", JOptionPane.ERROR_MESSAGE);
         System.out.println(ex);
    }
        
        
        
    }
    
    
    public void clearTextField(){
        jTextFieldMaSanPham.setText(null);
        jTextFieldTenSanPham.setText(null);
        jTextFieldThuongHieu.setText(null);
        jTextFieldSoLuong.setText(null);
        jTextFieldGia.setText(null);
        jDateChooserNgayNhap.setDate(null);
        jLabelImage1.setIcon(null);
        jLabelImage2.setIcon(null);
        jLabelImage3.setIcon(null);
        jLabelIamge4.setIcon(null);
        
        
        
        
    }
    //----------------------------------------------------------------------------------------------
    public void insertSanPham(){//chèn dữ liệu
        
     
            PreparedStatement ps1,ps2;
            Statement st;
            try {
                
                if(Check_Data()){
                    
                    Connection con = myConnection.getConnection();
                    
                    con.setAutoCommit(false);
                    st = con.createStatement();
                    //  DefaultTableModel tblModel = (DefaultTableModel) jTableEmployee.getModel();
                    
                    
                    String sql = "INSERT INTO `SanPham` (`MaSanPham`, `TenSanPham`, `LoaiSanPham`, `ThuongHieu`, `SoLuong`, `GiaBan`,`NgayNhap`) VALUES (?, ?,?, ?, ?, ?, ?)";
                    ps1 = con.prepareStatement(sql);
                    ps1.setString(1,jTextFieldMaSanPham.getText());
                    ps1.setString(2,jTextFieldTenSanPham.getText());
                    ps1.setString(3,(String)jComboBoxLoaiSanPham.getSelectedItem());
                    
                    ps1.setString(4,jTextFieldThuongHieu.getText());
                    
                    
                    
                    
                    ps1.setString(5,jTextFieldSoLuong.getText());
                    ps1.setString(6,jTextFieldGia.getText());
                    
                    
                    SimpleDateFormat sdf1 = new SimpleDateFormat(  "yyyy-MM-dd");
                    String NgayNhapHang = sdf1.format(jDateChooserNgayNhap.getDate());
                    
                    
                    
                    
                    ps1.setString(7,NgayNhapHang); 
                    
                   
                    
                    
                    int check = 0, check1 = 0  ,check2 =0  ,check3 = 0 ;
                    
                    // chèn dự liêu hình ảnh vào
                    if(covertJlabelToByte(jLabelImage1) != null){
                        System.out.println("cao duc tin "+getDescriptionJlabel(jLabelImage1));
                        
                  
                         check = this.createImage(con,jTextFieldMaSanPham.getText(),getDescriptionJlabel(jLabelImage1), covertJlabelToByte(jLabelImage1)).executeUpdate() ;
                    
                    }
                   
                    if(covertJlabelToByte(jLabelImage2) != null){
                         check1 = this.createImage(con,jTextFieldMaSanPham.getText(),getDescriptionJlabel(jLabelImage2), covertJlabelToByte(jLabelImage2)).executeUpdate() ;
                    }
                    
                    if(covertJlabelToByte(jLabelImage3) != null){
                      
                         check2 = this.createImage(con,jTextFieldMaSanPham.getText(),getDescriptionJlabel(jLabelImage3), covertJlabelToByte(jLabelImage3)).executeUpdate() ;
                    
                    }
                     if (covertJlabelToByte(jLabelIamge4) != null){
                             check3 = this.createImage(con,jTextFieldMaSanPham.getText(),getDescriptionJlabel(jLabelIamge4), covertJlabelToByte(jLabelIamge4)).executeUpdate() ;
                    
                     }
                    
                    
                    if(ps1.executeUpdate() == 1 ){//this function will returns a value other than 0 when it execute suscessf . otherwise it return
                        
                        con.commit();// Commit transaction
                        this.clearTextField();
                        JOptionPane.showMessageDialog(this,"You create succesful ");
                        clearTable(); // delete the duplicate because when you click "Thêm  " then it will be duplicate
                        // ClearTextField(); // delete the text on  textfield
                        this.show_SanPham("SELECT * FROM SanPham");
                    }
                    else {
                        
                        
                        JOptionPane.showMessageDialog(this,"Something Wrongs");
                    
                    }
                    
                  
                    
                }
                
            } catch (Exception ex) {
                
                //Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog( null,"MySQL error: " + ex.getMessage(), "MySQL Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex);
                
            }   
        
        
    }
    
    //----------------------------------------------------------------------------------------------
    public void GetImageFromDMS(int MaSanPham){
          PreparedStatement ps1,ps2;
            Statement st;
            try {
            jLabelImage1.setIcon(null);
            jLabelImage2.setIcon(null);
            jLabelImage3.setIcon(null);
            jLabelIamge4.setIcon(null);
            
            
            Connection con = myConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Image WHERE MaSanPham = ?");
            ps.setInt(1, MaSanPham); //1 is the id of the image you want to retrieve
            ResultSet rs = ps.executeQuery();
            Image[] image = new Image[4];
            ImageIcon[] icon = new ImageIcon[4];
            int i = 0;
            while (rs.next()) {
                Blob blob = rs.getBlob("hinhanh");
                String nameImage = rs.getString("TenHinhAnh");
                System.out.println(nameImage);
               //Step 3: Create ImageIcon object from Blob
                byte[] data = blob.getBytes(1, (int) blob.length());
                ImageIcon imageIcon = new ImageIcon(data);
                
                // Step 5: Set description for the ImageIcon
                imageIcon.setDescription("Image " + (i+1));
                
                icon[i] = new ImageIcon(data); 
                icon[i].setDescription(nameImage);
                
                image[i] = imageIcon.getImage();
               
                i++;
                
                 
            
            }
       
                 //Step 5: Set image to existing JLabel
            if (image[0] != null) {
                
                jLabelImage1.setIcon((icon[0]));
                
            } 
            if ( image[1] != null) {
                
                jLabelImage2.setIcon((icon[1]));
                
            }
            if (image[2] != null) {
                
                jLabelImage3.setIcon(icon[2]);
            
            }
            if (image[3] != null) {
                
                jLabelIamge4.setIcon(icon[3]);
            
            }
            }
            catch (Exception ex) {
                //Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog( null,"MySQL error: " + ex.getMessage(), "MySQL Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex);
                
            }   
          
        
        
    }
    
    //----------------------------------------------------------------------------------------------
//    public String getDescriptionJlabel(JLabel name){
//         ImageIcon icon = (ImageIcon) name.getIcon(); // get the ImageIcon object
//         String imageName = icon != null ? icon.getDescription() : null; // get the image name
//         return imageName;
//    }
     
    public String getDescriptionJlabel(JLabel name){
    ImageIcon icon = (ImageIcon) name.getIcon(); // Get the ImageIcon object from the JLabel

    if (icon != null && icon.getDescription() != null) { // If ImageIcon and description are not null
        return icon.getDescription(); // Return the description 
    } else {
        return null; // Return null if description is null
    }
}
            
 
    //----------------------------------------------------------------------------------------------           
 public PreparedStatement createImage(Connection conn, String arg1, String arg2,byte[] imageBytes ) throws SQLException {// chen hinh vao dastabase
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `Image` (`MaSanPham`,`TenHinhAnh`,`hinhanh`) VALUES(?,?,?)");
        pstmt.setString(1, arg1);
        pstmt.setString(2, arg2);
        pstmt.setBytes(3, imageBytes);
        return pstmt;
    }    
    
    //----------------------------------------------------------------------------------------------
    
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) jTableSanPham.getModel();
        model.setRowCount(0);
    }
    
    
    
    //----------------------------------------------------------------------------------------------
    // upload the image
    public void uploadImage(JLabel name){
     
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select an image to upload");
    fileChooser.setFileFilter(new FileNameExtensionFilter(
            "Image files", ImageIO.getReaderFileSuffixes()));

    // show the file chooser dialog and get the selected file
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
      

        try {
            // load the selected image file into a BufferedImage object
            BufferedImage image = ImageIO.read(file);

                // set the content alignment of the JLabel
            name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            name.setVerticalAlignment(javax.swing.SwingConstants.CENTER);

            int labelWidth = name.getWidth();
            int labelHeight = name.getHeight();
            Image resizedImage = new ImageIcon(image).getImage().getScaledInstance(
                    labelWidth, labelHeight, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);
            icon.setDescription(file.getName()); // set the name of the ImageIcon to the file name
            name.setIcon(icon);


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error uploading image: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
     }   
        
        
        
    }

    //----------------------------------------------------------------------------------------------
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
                            rs.getString("ThuongHieu"), rs.getDate("NgayNhap"),rs.getInt("SoLuong"), rs.getDouble("GiaBan"));
                    

                    DanhSachSanPham.add(sanPhamTemp);//add all data to userlist


                
                        
           }
                   } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return DanhSachSanPham;
        
        
    }
    
    //----------------------------------------------------------------------------------------------
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
     
    //----------------------------------------------------------------------------------------------
     public boolean Check_Data(){
            boolean check = true;
            if(jTextFieldMaSanPham.getText().equals("") || jTextFieldTenSanPham.getText().equals("") || jTextFieldThuongHieu.getText().equals("")
                    || jTextFieldSoLuong.getText().equals("")||jTextFieldGia.getText().equals("") || !jDateChooserNgayNhap.isEnabled()){
                    JOptionPane.showMessageDialog(null,"Bạn phải điền đầy đủ tất cả các thông tin của Sản Pham");
                     check= false;
            }
            else if (!jTextFieldMaSanPham.getText().matches("[0-9]+")){
              JOptionPane.showMessageDialog(rootPane, "Ma San PHam phải chỉ là kí tự số  ");
              check =  false;

               }
             else if(!jTextFieldSoLuong.getText().matches("[0-9]+")){
                 JOptionPane.showMessageDialog(rootPane, "So Luong  phải là kí tự số ");
              check =  false;
             }

            return check;
     }
     
     
    //----------------------------------------------------------------------------------------------
     public void FindSanPham(){
      String MaSP = jTextFieldMaSP.getText();
      String TenSP = jTextFieldTenSP.getText();
      String LoaiSanPham = (String)jComboBoxLoaiSP.getSelectedItem();
      String ThuongHieu = jTextFieldTH.getText();
        this.clearTable();

        String sql = "SELECT * FROM quanliduan.SanPham WHERE 1=1";
        if (!MaSP.equals("")) {
            sql += " AND MaSanPham LIKE '%" + MaSP + "%'";
        }
        if (!TenSP.equals("")) {
            sql += " AND TenSanPham LIKE '%" + TenSP + "%'";
        }
        if (!LoaiSanPham.equals("ALL")) {
            sql += " AND LoaiSanPham = '" + LoaiSanPham + "'";
        }
        if(!ThuongHieu.equals("")){
          
            sql += " AND ThuongHieu = '" + ThuongHieu + "'";

        }
            
       this.show_SanPham(sql);
         
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
     
     
     
     
    //----------------------------------------------------------------------------------------------
     private static Image getImageFromIcon(Icon icon) {
            if (icon != null && icon instanceof ImageIcon) {
                return ((ImageIcon) icon).getImage();
            }
            return null;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabelSanPham = new javax.swing.JLabel();
        jLabelHoadon = new javax.swing.JLabel();
        jLabelPhanCong = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldMaSP = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldTenSP = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jComboBoxLoaiSP = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jTextFieldTH = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldGia = new javax.swing.JTextField();
        jTextFieldSoLuong = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldMaSanPham = new javax.swing.JTextField();
        jTextFieldTenSanPham = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldThuongHieu = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jComboBoxLoaiSanPham = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jDateChooserNgayNhap = new com.toedter.calendar.JDateChooser();
        jButtonadd = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSanPham = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jButtonThem = new javax.swing.JButton();
        jButtonSua = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabelIamge4 = new javax.swing.JLabel();
        jLabelImage1 = new javax.swing.JLabel();
        jLabelImage2 = new javax.swing.JLabel();
        jLabelImage3 = new javax.swing.JLabel();

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
        setMinimumSize(new java.awt.Dimension(1400, 680));
        setUndecorated(true);
        setSize(new java.awt.Dimension(1200, 680));
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

        jPanel3.setBackground(new java.awt.Color(51, 51, 72));
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

        jLabel34.setBackground(new java.awt.Color(51, 51, 71));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("   Nhân Viên");
        jLabel34.setToolTipText("");
        jLabel34.setOpaque(true);
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel34MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel34MouseExited(evt);
            }
        });

        jLabelSanPham.setBackground(new java.awt.Color(51, 51, 71));
        jLabelSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelSanPham.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSanPham.setText("   Sản Phẩm");
        jLabelSanPham.setToolTipText("");
        jLabelSanPham.setOpaque(true);
        jLabelSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelSanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelSanPhamMouseExited(evt);
            }
        });

        jLabelHoadon.setBackground(new java.awt.Color(51, 51, 71));
        jLabelHoadon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelHoadon.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHoadon.setText("   Hoá Đơn");
        jLabelHoadon.setToolTipText("");
        jLabelHoadon.setOpaque(true);
        jLabelHoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelHoadonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelHoadonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelHoadonMouseExited(evt);
            }
        });

        jLabelPhanCong.setBackground(new java.awt.Color(51, 51, 71));
        jLabelPhanCong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPhanCong.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPhanCong.setText("   Phân Công");
        jLabelPhanCong.setToolTipText("");
        jLabelPhanCong.setOpaque(true);
        jLabelPhanCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPhanCongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelPhanCongMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelPhanCongMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
            .addComponent(jLabelHoadon, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabelPhanCong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabelHoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabelPhanCong, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 74, 190, 730));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel4.setPreferredSize(new java.awt.Dimension(1200, 160));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Sản phẩm");

        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Mã Sản phẩm:");
        jLabel26.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel26.setFocusable(false);
        jLabel26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextFieldMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMaSPActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Tên Sản phẩm:");
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel27.setFocusable(false);
        jLabel27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextFieldTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTenSPActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Loại Sản phẩm:");
        jLabel32.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel32.setFocusable(false);
        jLabel32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBoxLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laptop", "PC", "ALL" }));
        jComboBoxLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLoaiSPActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Thương hiệu:");
        jLabel33.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel33.setFocusable(false);
        jLabel33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addContainerGap(1057, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldTH, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 1210, 160));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/USER1.png"))); // NOI18N
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, 230));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setText("Hình ảnh");
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Số lượng:");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel20.setFocusable(false);
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 110, 25));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Giá:");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel21.setFocusable(false);
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 110, 25));

        jTextFieldGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldGiaActionPerformed(evt);
            }
        });
        jPanel6.add(jTextFieldGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 200, 30));
        jPanel6.add(jTextFieldSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 200, 25));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("Mã Sản phẩm:");
        jLabel24.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel24.setFocusable(false);
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 110, 25));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("Tên sản phẩm:");
        jLabel25.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel25.setFocusable(false);
        jLabel25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 110, 25));

        jTextFieldMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMaSanPhamActionPerformed(evt);
            }
        });
        jPanel6.add(jTextFieldMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 200, 25));

        jTextFieldTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTenSanPhamActionPerformed(evt);
            }
        });
        jPanel6.add(jTextFieldTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 200, 25));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Thương hiệu:");
        jLabel29.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel29.setFocusable(false);
        jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 110, 25));
        jPanel6.add(jTextFieldThuongHieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 200, 25));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Ngày nhập:");
        jLabel30.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel30.setFocusable(false);
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 110, 25));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Loại sản phẩm:");
        jLabel31.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel31.setFocusable(false);
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 110, 25));

        jComboBoxLoaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PC", "Laptop" }));
        jComboBoxLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLoaiSanPhamActionPerformed(evt);
            }
        });
        jPanel6.add(jComboBoxLoaiSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 200, -1));

        jButton6.setText("Thêm nhân viên");
        jPanel6.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 570, 120, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setText("Thông tin sản phẩm");
        jPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));
        jPanel6.add(jDateChooserNgayNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 200, -1));

        jButtonadd.setText("Clear");
        jButtonadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaddActionPerformed(evt);
            }
        });
        jPanel6.add(jButtonadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 450, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 240, 600, 300));

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
        jTableSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableSanPham);
        if (jTableSanPham.getColumnModel().getColumnCount() > 0) {
            jTableSanPham.getColumnModel().getColumn(0).setResizable(false);
            jTableSanPham.getColumnModel().getColumn(1).setResizable(false);
            jTableSanPham.getColumnModel().getColumn(2).setResizable(false);
            jTableSanPham.getColumnModel().getColumn(3).setResizable(false);
            jTableSanPham.getColumnModel().getColumn(4).setResizable(false);
            jTableSanPham.getColumnModel().getColumn(5).setResizable(false);
            jTableSanPham.getColumnModel().getColumn(6).setResizable(false);
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 610, 560));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jButtonThem.setText("Thêm");
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });

        jButtonSua.setText("Sửa");
        jButtonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Xóa");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButton13.setText("Đóng");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButtonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSua, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 710, 610, 100));

        jLabelIamge4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIamge4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabelIamge4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelIamge4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabelIamge4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 550, 110, 140));

        jLabelImage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImage1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabelImage1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelImage1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 550, 110, 140));

        jLabelImage2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImage2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabelImage2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelImage2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 550, 110, 140));

        jLabelImage3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImage3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabelImage3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelImage3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 550, 110, 140));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void jTextFieldTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTenSanPhamActionPerformed

    private void jTextFieldMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMaSPActionPerformed

    private void jComboBoxLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLoaiSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxLoaiSPActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.FindSanPham();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
                                          
            // TODO add your handling code here:
       this.insertSanPham();
        
        
        
    }//GEN-LAST:event_jButtonThemActionPerformed

    private void jButtonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaActionPerformed
        // TODO add your handling code here:
        this.UpdateSanPham();
    }//GEN-LAST:event_jButtonSuaActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
       this.DeleteSanPham();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTextFieldMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMaSanPhamActionPerformed

    private void jTextFieldTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTenSPActionPerformed

    private void jLabelImage1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelImage1MouseClicked
        // TODO add your handling code here:
        SanPham SanPham = new SanPham();
        SanPham.uploadImage(jLabelImage1, this);
    }//GEN-LAST:event_jLabelImage1MouseClicked

    private void jLabelImage2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelImage2MouseClicked
        // TODO add your handling code here:
        SanPham SanPham = new SanPham();
        SanPham.uploadImage(jLabelImage2, this);
    }//GEN-LAST:event_jLabelImage2MouseClicked

    private void jLabelImage3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelImage3MouseClicked
        SanPham SanPham = new SanPham();
        SanPham.uploadImage(jLabelImage3, this);
    }//GEN-LAST:event_jLabelImage3MouseClicked

    private void jLabelIamge4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelIamge4MouseClicked
        // TODO add your handling code here:
   
        SanPham SanPham = new SanPham();
        SanPham.uploadImage(jLabelIamge4, this);
    }//GEN-LAST:event_jLabelIamge4MouseClicked

    private void jTableSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSanPhamMouseClicked
        // TODO add your handling code here:  int row = jTableEmployee.rowAtPoint(evt.getPoint());
         int row = jTableSanPham.rowAtPoint(evt.getPoint());
        jTextFieldMaSanPham.setText(jTableSanPham.getValueAt(row, 0).toString());

        jTextFieldTenSanPham.setText(jTableSanPham.getValueAt(row, 1).toString());
   
        jComboBoxLoaiSanPham.setSelectedItem(jTableSanPham.getValueAt(row,6).toString());
        jTextFieldThuongHieu.setText(jTableSanPham.getValueAt(row,3).toString());
        jTextFieldSoLuong.setText(jTableSanPham.getValueAt(row,4).toString());
    
        
        
        jTextFieldGia.setText(jTableSanPham.getValueAt(row,5).toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateNSSV =  jTableSanPham.getValueAt(row,6).toString();
   

        try {
            java.util.Date date = dateFormat.parse(dateNSSV);
            jDateChooserNgayNhap.setDate((date));
        } catch (ParseException ex) {
            Logger.getLogger(ThongTinNhanVien_ThemNV.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.GetImageFromDMS(Integer.parseInt(jTableSanPham.getValueAt(row, 0).toString()));

      
                                             
    }//GEN-LAST:event_jTableSanPhamMouseClicked

    private void jButtonaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaddActionPerformed
        // TODO add your handling code here:
           
            this.clearTextField();
    }//GEN-LAST:event_jButtonaddActionPerformed

    private void jComboBoxLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLoaiSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxLoaiSanPhamActionPerformed

    private void jTextFieldGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGiaActionPerformed

    private void jLabel34MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseEntered
        // TODO add your handling code here:
        jLabel34.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_jLabel34MouseEntered

    private void jLabel34MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseExited
        // TODO add your handling code here:
         jLabel34.setBackground(new Color(51,51,71));
    }//GEN-LAST:event_jLabel34MouseExited

    private void jLabelSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSanPhamMouseEntered
        // TODO add your handling code here:
        jLabelSanPham.setBackground(new Color(0,0,0));
        
    }//GEN-LAST:event_jLabelSanPhamMouseEntered

    private void jLabelSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSanPhamMouseExited
        // TODO add your handling code here:
        jLabelSanPham.setBackground(new Color(51,51,71));
        
        
    }//GEN-LAST:event_jLabelSanPhamMouseExited

    private void jLabelHoadonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHoadonMouseEntered
        // TODO add your handling code here:
        jLabelHoadon.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_jLabelHoadonMouseEntered

    private void jLabelHoadonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHoadonMouseExited
        // TODO add your handling code here:
        jLabelHoadon.setBackground(new Color(51,51,71));
    }//GEN-LAST:event_jLabelHoadonMouseExited

    private void jLabelPhanCongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPhanCongMouseEntered
        // TODO add your handling code here:
        jLabelPhanCong.setBackground(new Color(0,0,0));
    }//GEN-LAST:event_jLabelPhanCongMouseEntered

    private void jLabelPhanCongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPhanCongMouseExited
        // TODO add your handling code here:
        
         jLabelPhanCong.setBackground(new Color(51,51,71));
    }//GEN-LAST:event_jLabelPhanCongMouseExited

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        // TODO add your handling code here:
           ThongTinNhanVien_ThemNV supf = new ThongTinNhanVien_ThemNV();
           ManipulateComponents xuli = new ManipulateComponents();
               
           xuli.ChangeJframe(supf, this);
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabelSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSanPhamMouseClicked
        // TODO add your handling code here:   
             
    }//GEN-LAST:event_jLabelSanPhamMouseClicked

    private void jLabelHoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelHoadonMouseClicked
        // TODO add your handling code here:
           HoaDon supf = new HoaDon();
                ManipulateComponents xuli = new ManipulateComponents();
               
                xuli.ChangeJframe(supf, this);
    }//GEN-LAST:event_jLabelHoadonMouseClicked

    private void jLabelPhanCongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPhanCongMouseClicked
        // TODO add your handling code here:
          BangPhanCong_Admin supf = new BangPhanCong_Admin();
                ManipulateComponents xuli = new ManipulateComponents();
               
                xuli.ChangeJframe(supf, this);
    }//GEN-LAST:event_jLabelPhanCongMouseClicked

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
            java.util.logging.Logger.getLogger(ThongTinSanPham_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinSanPham_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinSanPham_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinSanPham_Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ThongTinSanPham_Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonSua;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonadd;
    private javax.swing.JComboBox<String> jComboBoxLoaiSP;
    private javax.swing.JComboBox<String> jComboBoxLoaiSanPham;
    private com.toedter.calendar.JDateChooser jDateChooserNgayNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabelHoadon;
    private javax.swing.JLabel jLabelIamge4;
    private javax.swing.JLabel jLabelImage1;
    private javax.swing.JLabel jLabelImage2;
    private javax.swing.JLabel jLabelImage3;
    private javax.swing.JLabel jLabelPhanCong;
    private javax.swing.JLabel jLabelSanPham;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableSanPham;
    private javax.swing.JTextField jTextFieldGia;
    private javax.swing.JTextField jTextFieldMaSP;
    private javax.swing.JTextField jTextFieldMaSanPham;
    private javax.swing.JTextField jTextFieldSoLuong;
    private javax.swing.JTextField jTextFieldTH;
    private javax.swing.JTextField jTextFieldTenSP;
    private javax.swing.JTextField jTextFieldTenSanPham;
    private javax.swing.JTextField jTextFieldThuongHieu;
    // End of variables declaration//GEN-END:variables
}
