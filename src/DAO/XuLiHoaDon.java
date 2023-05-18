
package DAO;

import Database.myConnection;
import dto.ChiTietHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication3.HoaDon;
import dto.SanPham;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;



public class XuLiHoaDon extends HoaDon{
    public static ArrayList<String> LoadDataComBoBox(){
        ArrayList<String> list = new ArrayList<>();
        Connection con = myConnection.getConnection();
        String sql = "SELECT MaNV, Hoten FROM NhanVien";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            String maNV = rs.getString("MaNV");
            String hoTen = rs.getString("Hoten");
            String maNVHoTen = maNV + " - " + hoTen;
            list.add(maNVHoTen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static Map<SanPham, ChiTietHoaDon> DanhSachHoaDon(int maHD){

        Map<SanPham, ChiTietHoaDon> danhSachHoaDon = new HashMap<>();

        try {
            Connection con = myConnection.getConnection();
            PreparedStatement ps;
            ResultSet rs;

            String sql = "SELECT * FROM chitiethoadon JOIN sanpham ON chitiethoadon.MaSanPham = sanpham.MaSanPham WHERE chitiethoadon.ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, maHD);
            rs = ps.executeQuery();

            while (rs.next()) {
                String tenSP = rs.getString("TenSanPham");
                Integer maSP = rs.getInt("MaSanPham");
                Double giaBan = rs.getDouble("GiaBan");
                Integer soLuong = rs.getInt("SoLuong");
                Float thanhTien = rs.getFloat("TongTien");

                SanPham sanPham = new SanPham(maSP, tenSP, "", "", null, 0, giaBan);
                ChiTietHoaDon cthd = new ChiTietHoaDon(0, 0, 0, 0, soLuong, 0, thanhTien);

                danhSachHoaDon.put(sanPham, cthd);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (Exception ex) {
            Logger.getLogger(java.awt.Menu.class.getName()).log(Level.SEVERE, null, ex);
        }

        return danhSachHoaDon;
    }
    
    public static void TaoHD(int MaHD, Date NgayTao, JComboBox combobox,int soLuongBan){

            try {
                Connection con = myConnection.getConnection();

                String sql = "INSERT INTO HoaDon (ID, NgayLap) VALUES (?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, MaHD);
                ps.setDate(2, new java.sql.Date(NgayTao.getTime()));
                ps.executeUpdate();

                
                JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công");
            } catch (NumberFormatException | SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi thêm hóa đơn");
            }
        
   
    }
    
    public static void CTHD (int MaHD, JComboBox combobox, int MaSanPham, int SoLuong, Double GiaBan){
        String value = combobox.getSelectedItem().toString();
        if (!value.isEmpty()) {
            String[] parts = value.split(" - ");
            String maNV = parts[0];

            try {
                int maNhanVien = Integer.parseInt(maNV);
                System.out.println(maNhanVien);
                Connection con = myConnection.getConnection();

                String sql = "INSERT INTO chitiethoadon (ID, MaNV, MaSanPham, SoLuong, GiaBan) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, MaHD);
                ps.setInt(2, maNhanVien);
                ps.setInt(3, MaSanPham);
                ps.setInt(4,SoLuong);
                ps.setDouble(5, GiaBan);
                ps.executeUpdate();               
                JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
            } catch (NumberFormatException | SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi thêm thêm sản phẩm");
            }
        }
    }
    
    public static void LoadHD(int maHD){
        
    }
}