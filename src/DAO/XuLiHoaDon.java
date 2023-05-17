
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
import java.util.HashMap;
import java.util.Map;



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
    
    public static Map<SanPham, ChiTietHoaDon> DanhSachHoaDon(String sql){

           Map<SanPham, ChiTietHoaDon> DanhSachHoaDon = new HashMap<>();
                PreparedStatement ps;
                Statement st;
                ResultSet rs ;
            try {
                 Connection con = myConnection.getConnection();
                st = con.createStatement();
                String query1 = sql;
                rs = st.executeQuery(query1);
                while(rs.next()){
                    String TenSP = rs.getString("TenSanPham");
                    Integer MaSP = rs.getInt("MaSanPham");
                    Double GiaBan = rs.getDouble("GiaBan");
                    Integer Soluong = rs.getInt("SoLuong");
                    Float ThanhTien = rs.getFloat("TongTien");
                    SanPham sanPham = new SanPham(MaSP, TenSP, "", "", null, 0, GiaBan);
                    ChiTietHoaDon cthd = new ChiTietHoaDon(0, 0, 0, 0, Soluong, 0, ThanhTien);
                    DanhSachHoaDon.put(sanPham, cthd);
           }
                   } catch (Exception ex) {
            Logger.getLogger(java.awt.Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DanhSachHoaDon;
    }
    
   
}
