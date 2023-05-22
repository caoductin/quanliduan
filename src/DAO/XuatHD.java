
package DAO;

import Database.myConnection;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class XuatHD {
    public static void xuatHD(int maHD){
        
         try {
            // Kết nối CSDL
            Connection connection = myConnection.getConnection();

            // Truy vấn dữ liệu từ CSDL
            String query = """
                    SELECT
                        hd.ThanhTien,
                        nv.Hoten,
                        sp.MaSanPham,
                        sp.TenSanPham,
                        sp.GiaBan,
                        cthd.SoLuong,
                        nv.Hoten AS TenNguoiBan,
                        cthd.TongTien,
                        hd.NgayLap
                    FROM
                        SanPham sp
                        INNER JOIN ChiTietHoaDon cthd ON sp.MaSanPham = cthd.MaSanPham
                        INNER JOIN Nhanvien nv ON cthd.MaNV = nv.MaNV
                        INNER JOIN HoaDon hd ON cthd.ID = hd.ID
                    WHERE
                        hd.ID = ?;
                    """;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, maHD);
            ResultSet resultSet = statement.executeQuery();

            // Tạo Workbook từ template
            FileInputStream templateFile = new FileInputStream("/Users/caoductin/Desktop/bàitap lon/duangoc/src/Report/HoaDon.xlsx");
            Workbook workbook = new XSSFWorkbook(templateFile);
            Sheet sheet = workbook.getSheet("HoaDon");

            // Định nghĩa map chứa vị trí của các ô trong template
            Map<String, CellAddress> cellPositions = new HashMap<>();

            cellPositions.put("TenSanPham", new CellAddress("B15")); // Ô "Tên hàng" ở cột B, hàng 14
            cellPositions.put("GiaBan", new CellAddress("C15")); // Ô "Giá bán" ở cột C, hàng 14
            cellPositions.put("SoLuong", new CellAddress("D15")); // Ô "Số lượng" ở cột D, hàng 14
            cellPositions.put("TongTien", new CellAddress("E15")); // Ô "Tổng tiền" ở cột F, hàng 14
            cellPositions.put("ID", new CellAddress("B8")); // Ô "ID" ở cột F, hàng 5
            cellPositions.put("NgayLap", new CellAddress("B9")); // Ô "Ngày lập" ở cột H, hàng 5
            cellPositions.put("Hoten", new CellAddress("B10")); // Ô "Họ tên" ở cột F, hàng 8
            cellPositions.put("ThanhTien",new CellAddress("G28"));

            // Lấy dòng dữ liệu cho các trường "ID", "Hoten" và "NgayLap"
            resultSet.next();
            Row headerRow = sheet.getRow(cellPositions.get("ID").getRow());
            if (headerRow == null) {
                headerRow = sheet.createRow(cellPositions.get("ID").getRow());
            }
            Cell idCell = headerRow.createCell(cellPositions.get("ID").getColumn());
            idCell.setCellValue(maHD);

            Row hotenRow = sheet.getRow(cellPositions.get("Hoten").getRow());
            if (hotenRow == null) {
                hotenRow = sheet.createRow(cellPositions.get("Hoten").getRow());
            }
            Cell hotenCell = hotenRow.createCell(cellPositions.get("Hoten").getColumn());
            hotenCell.setCellValue(resultSet.getString("Hoten"));

            Row ngaylapRow = sheet.getRow(cellPositions.get("NgayLap").getRow());
            if (ngaylapRow == null) {
                ngaylapRow = sheet.createRow(cellPositions.get("NgayLap").getRow());
            }   
            Cell ngaylapCell = ngaylapRow.createCell(cellPositions.get("NgayLap").getColumn());
            ngaylapCell.setCellValue(resultSet.getString("NgayLap"));
            
            
            Row thanhTienRow = sheet.getRow(cellPositions.get("ThanhTien").getRow());
            if (thanhTienRow == null) {
                thanhTienRow = sheet.createRow(cellPositions.get("ThanhTien").getRow());
            }
            Cell thanhTienCell = thanhTienRow.createCell(cellPositions.get("ThanhTien").getColumn());
            thanhTienCell.setCellValue(resultSet.getString("ThanhTien"));

            // Đổ dữ liệu vào từng ô trong template
            int rowIndex = cellPositions.get("TenSanPham").getRow(); // Bắt đầu từ hàng có ô "TenSanPham"
            while (resultSet.next()) {
                Row dataRow = sheet.getRow(rowIndex);
                if (dataRow == null) {
                    dataRow = sheet.createRow(rowIndex);
                }
                for (Map.Entry<String, CellAddress> entry : cellPositions.entrySet()) {
                    String columnName = entry.getKey();
                    CellAddress cellAddress = entry.getValue();
                    Cell cell = dataRow.createCell(cellAddress.getColumn());
                    switch (columnName) {
                        case "TenSanPham":
                            cell.setCellValue(resultSet.getString("TenSanPham"));
                            break;
                        case "GiaBan":
                            cell.setCellValue(resultSet.getDouble("GiaBan"));
                            break;
                        case "SoLuong":
                            cell.setCellValue(resultSet.getInt("SoLuong"));
                            break;
                        case "TongTien":
                            cell.setCellValue(resultSet.getString("TongTien"));
                            break;
                    }
                }
                rowIndex++;
            }

            // Tự động điều chỉnh kích thước cột
            for (int i = 0; i < 7; i++) {
                sheet.autoSizeColumn(i);
            }

            // Lưu tệp Excel
            FileOutputStream fileOut = new FileOutputStream("D:\\BTLJava\\quanliduan\\src\\Report\\HoaDon.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            System.out.println("Dữ liệu đã được xuất ra tệp Excel thành công.");

            // Đóng ResultSet, PreparedStatement và Connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void Update(){
        try {
            Connection con = myConnection.getConnection();
            PreparedStatement ps;
            //ResultSet rs;
            
             String sql = "UPDATE HoaDon hd\n" +
                        "INNER JOIN ChiTietHoaDon cthd ON hd.ID = cthd.ID\n" +
                        "INNER JOIN SanPham sp ON cthd.MaSanPham = sp.MaSanPham\n" +
                        "SET hd.ThanhTien = cthd.SoLuong * sp.GiaBan;";
             
             ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}

