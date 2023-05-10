/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

/**
 *
 * @author caoductin
 */
public class SanPham {
    private int MaSanPham;
    private String  TenSanPham,LoaiSanPham,ThuongHieu,NgayNhap;
    private int SoLuong;
    private double Gia;
    public SanPham(int MaSanPham, String TenSanPham, String LoaiSanPham, String ThuongHieu, String NgayNhap, int Soluong, Double Gia){
        this.MaSanPham = MaSanPham;
        this.TenSanPham = TenSanPham;
        this.LoaiSanPham = LoaiSanPham;
        this.ThuongHieu = ThuongHieu;
        this.NgayNhap = NgayNhap;
        this.SoLuong = Soluong;
        this.Gia = Gia;
                
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
        return this.Gia;
    }
    
    
}
