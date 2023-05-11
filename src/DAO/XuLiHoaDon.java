
package DAO;

import Database.myConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication3.HoaDon;
import dto.SanPham;



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
    
    public static ArrayList<SanPham> DanhSachSanPham(String sql){

            ArrayList<SanPham> DanhSachSanPham = new ArrayList();
                PreparedStatement ps;
                Statement st;
                ResultSet rs ;
            try {
                 Connection con = myConnection.getConnection();
                st = con.createStatement();
                String query1 = sql;
                rs = st.executeQuery(query1);
                SanPham sanPhamTemp;
                while(rs.next()){
                    sanPhamTemp = new SanPham(rs.getInt("MaSanPham"),rs.getString("TenSanPham"), rs.getString("LoaiSanPham"),
                            rs.getString("ThuongHieu"), rs.getString("NgayNhap"),rs.getInt("SoLuong"), rs.getDouble("GiaBan"));
                    

                    DanhSachSanPham.add(sanPhamTemp);//add all data to userlist


                
                        
           }
                   } catch (Exception ex) {
            Logger.getLogger(java.awt.Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        return DanhSachSanPham;
    }
    
    public static void NhapMaHD(){
        PreparedStatement ps;
        Statement st;
        
        try {
            Connection con = myConnection.getConnection();
            String sql = "INSER INTO `hoadon` ";
            
        } catch (Exception e) {
        }
    }
}
