
package DAO;

import Database.myConnection;
import java.sql.Connection;
import dto.SanPham;
import java.awt.Menu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class DAOSanPham  {
    public static ArrayList<SanPham> DanhSachSanPham(String sql){
    
        ArrayList<SanPham> DanhSachSanPham = new ArrayList<>();
        
        PreparedStatement ps;
        Statement st;
        ResultSet rs ;
        
        try {
            Connection con = myConnection.getConnection();
            st = con.createStatement();
            String query1 = sql;
            rs = st.executeQuery(query1);
            
            SanPham sp;
            
            while(rs.next()){
                sp = new SanPham(rs.getInt("MaSanPham"), rs.getString("TenSanPham"), rs.getString("LoaiSanPham"), rs.getString("ThuongHieu"), rs.getDate("NgayNhap"), rs.getInt("SoLuong"), rs.getDouble("GiaBan"));
                DanhSachSanPham.add(sp);
                
            }
        } catch (Exception e) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return DanhSachSanPham;
    }
    
    public static ArrayList<String> LoadDataCategoryComBoBox(){
        ArrayList<String> list = new ArrayList<>();
        Connection con = myConnection.getConnection();
        String sql = "SELECT DISTINCT LoaiSanPham FROM SanPham";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            String category = rs.getString("LoaiSanPham");
            list.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static ArrayList<String> LoadDataTrademarkComBoBox(){
        ArrayList<String> list = new ArrayList<>();
        Connection con = myConnection.getConnection();
        String sql = "SELECT DISTINCT ThuongHieu FROM SanPham";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            String category = rs.getString("thuongHieu");
            list.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
   public static void filter(JTable table, JComboBox<String> categoryComboBox, JComboBox<String> brandComboBox, JTextField nameTextField) {
        String category = categoryComboBox.getSelectedItem().toString();
        String brand = brandComboBox.getSelectedItem().toString();
        String searchTerm = nameTextField.getText().trim();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        // Filter by category
        if (!category.equals("Tất cả")) {
            RowFilter<Object, Object> categoryFilter = RowFilter.regexFilter("(?i)" + category, 1);
            filters.add(categoryFilter);
        }

        // Filter by brand
        if (!brand.equals("Tất cả")) {
            RowFilter<Object, Object> brandFilter = RowFilter.regexFilter("(?i)" + brand, 3);
            filters.add(brandFilter);
        }

        // Filter by name
        if (!searchTerm.isEmpty()) {
            RowFilter<Object, Object> nameFilter = RowFilter.regexFilter("(?i)" + searchTerm, 2);
            filters.add(nameFilter);
        }

        // Combine filters
        if (filters.size() > 0) {
            RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filters);
            sorter.setRowFilter(combinedFilter);
        } else {
            sorter.setRowFilter(null);
        }
    }
}

