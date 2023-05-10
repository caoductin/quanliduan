
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javaapplication3.NhanVien;
import java.sql.Connection;
import javaapplication3.myConnection;


public class DAONhanVien {
    public ArrayList<NhanVien> DanhSachNhanVien(String sql){
        ArrayList<NhanVien> DanhSachNhanVien = new ArrayList<>();
        PreparedStatement ps;
        Statement st;
        ResultSet rs ;
        
        try {
           Connection con = myConnection.getConnection();
           st = con.createStatement();
           String query1 = sql;
           rs = st.executeQuery(query1);
           NhanVien NhanVienTemp;
           while(rs.next()){
               NhanVienTemp = new NhanVien(rs.getInt("MaNV"), rs.getString("Hoten"), rs.getString("CCCD"), rs.getString("Gioitinh"), rs.getDate("Ngaysinh"), rs.getString("DiaChi"), rs.getString("ChucVu"), rs.getString("SDT"), rs.getString("Password"), rs.getDate("NgayBatDau"));
               DanhSachNhanVien.add(NhanVienTemp);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return DanhSachNhanVien;
    }
    
    public void getData(String sql){
        ArrayList<NhanVien> list = DanhSachNhanVien(sql);
    }
}
