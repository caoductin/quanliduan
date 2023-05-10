
package dto;


public class SanPham {
    private int MaSanPham;
    private String  TenSanPham,LoaiSanPham,ThuongHieu,NgayNhap;
    private int SoLuong;
    private double GiaBan;
    public SanPham(int MaSanPham, String TenSanPham, String LoaiSanPham, String ThuongHieu, String NgayNhap, int Soluong, Double GiaBan){
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
    public String getNgayNhap(){
        return this.NgayNhap;
    }
    public int getSoLuong(){
        return this.SoLuong;
    }
    public double getGia(){
        return this.GiaBan;
    }
    
    
}
