
package dto;


public class ChiTietHoaDon {
    private int ID;
    private int MaNV;
    private int MaChiTietHoaDon;
    private int MaSanPham;
    private int SoLuong;
    private float GiaBan;
    private float TongTien;

    public ChiTietHoaDon(int ID, int MaNV, int MaChiTietHoaDon, int MaSanPham, int SoLuong, float GiaBan, float TongTien) {
        this.ID = ID;
        this.MaNV = MaNV;
        this.MaChiTietHoaDon = MaChiTietHoaDon;
        this.MaSanPham = MaSanPham;
        this.SoLuong = SoLuong;
        this.GiaBan = GiaBan;
        this.TongTien = TongTien;
    }

    public int getID() {
        return ID;
    }

    public int getMaNV() {
        return MaNV;
    }

    public int getMaChiTietHoaDon() {
        return MaChiTietHoaDon;
    }

    public int getMaSanPham() {
        return MaSanPham;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public float getGiaBan() {
        return GiaBan;
    }

    public float getTongTien() {
        return TongTien;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public void setMaChiTietHoaDon(int MaChiTietHoaDon) {
        this.MaChiTietHoaDon = MaChiTietHoaDon;
    }

    public void setMaSanPham(int MaSanPham) {
        this.MaSanPham = MaSanPham;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setGiaBan(float GiaBan) {
        this.GiaBan = GiaBan;
    }

    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }
    
    
}
