package dto;

import java.util.Date;

public class NhanVien {
        private int MaNV;
        private String Hoten, CCCD, Gioitinh, DiaChi, ChucVu,SDT,PassWord;
        private Date Ngaysinh, NgayBatDau;
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
        
            
}
