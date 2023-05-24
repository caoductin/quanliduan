package dto;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class NhanVien {
        private int MaNV;
        private String Hoten, CCCD, Gioitinh, DiaChi, ChucVu,SDT,PassWord;
        private Date Ngaysinh, NgayBatDau;
         private byte[] imageData; // instance variable for image data
        public NhanVien(){};
        public NhanVien(int MaNV, String Hoten ,String CCCD, String Gioitinh, Date Ngaysinh,String DiaChi
                ,String ChucVu, String SDT, String PassWord,Date NgayBatDau){
            this.MaNV = MaNV;
            this.Hoten = Hoten;
            this.CCCD = CCCD;
            this.Gioitinh = Gioitinh;
            this.Ngaysinh = Ngaysinh;
            this.DiaChi = DiaChi;
            this.ChucVu = ChucVu;
            this.SDT = SDT;
            this.PassWord = PassWord;
            this.NgayBatDau = NgayBatDau;
            
                    
        }
        public byte[] getImageData() {
            return this.imageData;
         }
         // setter method for image data
        public void setImageData(byte[] imageData) {
            this.imageData = imageData;
        }
        public int getMaNV ()
        {                
            return this.MaNV;
            
        }
        public String getCCCD(){
            return this.CCCD;
        
        }
        public String getGioitinh(){
            return this.Gioitinh;
        }
        public String getHoten(){
            return this.Hoten;
        }
        public Date getNgaySinh(){
            return this.Ngaysinh;
        }
        public String getSDT(){
            return this.SDT;
        }
        public String getPassword(){
            return this.PassWord;
        }
       
        public Date getNgayBatDau(){
            return this.NgayBatDau;
        }
        public String getDiaChi(){
            return this.DiaChi;
        }
        public String getChucVu(){
            return this.ChucVu;
        }
//        public void changeLabel(){
//             javax.swing.JButton button = ThongTinNhanVien_ThemNV.jButton13;
//        }
//        
    public void uploadImageNhanvien(JLabel name,JFrame nameFrame){
         
     
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
