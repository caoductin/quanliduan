package javaapplication3;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author caoductin
 */
public class NhanVien {
        private int MaNV;
        private String Hoten, CCCD, Gioitinh, DiaChi, ChucVu,SDT,PassWord,NgayBatdau, Ngaysinh;
        public NhanVien(int MaNV, String Hoten ,String CCCD, String Gioitinh, String Ngaysinh,String DiaChi
                ,String ChucVu, String SDT, String PassWord,String NgayBatDau){
            this.MaNV = MaNV;
            this.Hoten = Hoten;
            this.CCCD = CCCD;
            this.Gioitinh = Gioitinh;
            this.Ngaysinh = Ngaysinh;
            this.DiaChi = DiaChi;
            this.ChucVu = ChucVu;
            this.SDT = SDT;
            this.PassWord = PassWord;
            this.NgayBatdau = NgayBatDau;
            
                    
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
        public String getNgaySinh(){
            return this.Ngaysinh;
        }
        public String getSDT(){
            return this.SDT;
        }
        public String getPassword(){
            return this.PassWord;
        }
       
        public String getNgayBatDau(){
            return this.NgayBatdau;
        }
        public String getDiaChi(){
            return this.DiaChi;
        }
        public String getChucVu(){
            return this.ChucVu;
        }
        
        
            
}
