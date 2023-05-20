
package dto;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javaapplication3.ThongTinSanPham_Admin;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SanPham {
    private int MaSanPham;
    private String  TenSanPham,LoaiSanPham,ThuongHieu;
    private Date NgayNhap;
    private int SoLuong;
    private double GiaBan;
    public SanPham(){
    
    };
    
    public SanPham(int MaSanPham, String TenSanPham, String LoaiSanPham, String ThuongHieu, Date NgayNhap, int Soluong, Double GiaBan){
        this.MaSanPham = MaSanPham;
        this.TenSanPham = TenSanPham;
        this.LoaiSanPham = LoaiSanPham;
        this.ThuongHieu = ThuongHieu;
        this.NgayNhap = NgayNhap;
        this.SoLuong = Soluong;
        this.GiaBan = GiaBan;
                
    }
    public int getMaSanPham(){
        return this.MaSanPham;
    }
    public String getTenSanPham(){
        return this.TenSanPham;
    }
    public String getLoaiSanPham(){
    return this.LoaiSanPham;
    }
    public String getThuongHieu(){
        return this.ThuongHieu;
    }
    public Date getNgayNhap(){
        return this.NgayNhap;
    }
    public int getSoLuong(){
        return this.SoLuong;
    }
    public double getGia(){
        return this.GiaBan;
    }
    
    
    public PreparedStatement createImage(Connection conn, String arg1, String arg2,byte[] imageBytes ) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO `Image` (`MaSanPham`,`TenHinhAnh`,`hinhanh`) VALUES(?,?,?)");
        pstmt.setString(1, arg1);
        pstmt.setString(2, arg2);
        pstmt.setBytes(3, imageBytes);
        return pstmt;
    }    
    
    
    
     public void uploadImage(JLabel name,JFrame nameFrame){
         
     
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select an image to upload");
    fileChooser.setFileFilter(new FileNameExtensionFilter(
            "Image files", ImageIO.getReaderFileSuffixes()));

    // show the file chooser dialog and get the selected file
    int result = fileChooser.showOpenDialog(nameFrame);
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
            // set the icon of the JLabel to display the selected image
   //         jLabel8.setIcon(new ImageIcon(image));


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(nameFrame,"Error uploading image: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
     }   
        
    
    }
}
