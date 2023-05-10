
package dto;

import java.util.Date;


public class NhanVien {
    private int MaNV;
    private String HoTen;
    private String CCCD;
    private String GioiTinh;
    private Date NgaySinh;
    private String DiaChi;
    private String ChucVu;
    private String SDT;
    private String Password;
    private Date NgayBatDau;

    public NhanVien(int MaNV, String HoTen, String CCCD, String GioiTinh, Date NgaySinh, String DiaChi, String ChucVu, String SDT, String Password, Date NgayBatDau) {
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.CCCD = CCCD;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.ChucVu = ChucVu;
        this.SDT = SDT;
        this.Password = Password;
        this.NgayBatDau = NgayBatDau;
    }

    public int getMaNV() {
        return MaNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public String getCCCD() {
        return CCCD;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public String getSDT() {
        return SDT;
    }

    public String getPassword() {
        return Password;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }
    
    
}
