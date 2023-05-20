
package DAO;

import Database.myConnection;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.text.NumberFormat;
import java.util.Locale;



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
            FileInputStream templateFile = new FileInputStream("D:\\BTLJava\\quanliduan\\src\\Report\\HoaDon.xlsx");
            Workbook workbook = new XSSFWorkbook(templateFile);
            Sheet sheet = workbook.getSheet("HoaDon");

            // Định nghĩa map chứa vị trí của các ô trong template
            Map<String, CellAddress> cellPositions = new HashMap<>();

            cellPositions.put("TenSanPham", new CellAddress(14, 1)); // Ô "Tên hàng" ở cột B, hàng 15
            cellPositions.put("GiaBan", new CellAddress(14, 2)); // Ô "Giá bán" ở cột C, hàng 15
            cellPositions.put("SoLuong", new CellAddress(14, 3)); // Ô "Số lượng" ở cột D, hàng 15
            cellPositions.put("TongTien", new CellAddress(14, 4)); // Ô "Tổng tiền" ở cột E, hàng 15
            cellPositions.put("ID", new CellAddress(7, 1)); // Ô "ID" ở cột B, hàng 8
            cellPositions.put("NgayLap", new CellAddress(8, 1)); // Ô "Ngày lập" ở cột B, hàng 9
            cellPositions.put("Hoten", new CellAddress(9, 1)); // Ô "Họ tên" ở cột B, hàng 10
            cellPositions.put("ThanhTien", new CellAddress(27, 6)); // Ô "Thành tiền" ở cột G, hàng 28

            // Lấy dòng dữ liệu cho các trường "ID", "Hoten" và "NgayLap"
            if (!resultSet.next()) {
                 JOptionPane.showMessageDialog(null, "Vui lòng thêm sản phẩm");
                System.out.println("Không tìm thấy dữ liệu trong ResultSet.");
                // Xử lý lỗi hoặc thoát khỏi phương thức
                return;
            }
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
            double thanhTienValue = resultSet.getDouble("ThanhTien");
            DecimalFormat currencyFormat1 = new DecimalFormat("#,### VND");
            String formattedThanhTien = currencyFormat1.format(thanhTienValue);
            thanhTienCell.setCellValue(formattedThanhTien);
            
            resultSet.next();

            // Đổ dữ liệu vào từng ô trong template
            DecimalFormat currencyFormat = new DecimalFormat("#,### VND");
            int rowIndex = cellPositions.get("TenSanPham").getRow(); // Bắt đầu từ hàng có ô "TenSanPham"
            do {
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
                            double giaBan = resultSet.getDouble("GiaBan");
                            cell.setCellValue(currencyFormat.format(giaBan));
                            break;
                        case "SoLuong":
                            cell.setCellValue(resultSet.getInt("SoLuong"));
                            break;
                        case "TongTien":
                             double tongTien = resultSet.getDouble("TongTien");
                             cell.setCellValue(currencyFormat.format(tongTien));
                            break;
                    }
                }
                    rowIndex++;
                } while (resultSet.next());
             while (resultSet.next()) {
                String tenSanPham = resultSet.getString("TenSanPham");
                System.out.println(tenSanPham);
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
            
             JOptionPane.showMessageDialog(null, "Xuất Thành Công");

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
            
             String sql ="UPDATE HoaDon\n" +
                        "SET ThanhTien = (\n" +
                        "    SELECT SUM(TongTien)\n" +
                        "    FROM ChiTietHoaDon\n" +
                        "    WHERE ChiTietHoaDon.ID = HoaDon.ID\n" +
                        ")";
             
             ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}

