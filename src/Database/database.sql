Drop database quanliduan;
create database quanliduan;
CREATE TABLE IF NOT EXISTS `quanliduan`.`account` (
  `id` INT  UNSIGNED NOT NULL ,
  `userName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Gender` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(45) NOT NULL,
  `FullName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (id) REFERENCES nhanvien(MaNV)
  );

DROP TABLE phancong;
CREATE TABLE `quanliduan`.`PhanCong` (
  `MaNV` INT UNSIGNED NOT NULL,
  `CaLam` VARCHAR(40) NOT NULL,
  `NgayLam` DATE NOT NULL,
  `TrangThai` VARCHAR(45) DEFAULT 'Chua hoan thanh',
  PRIMARY KEY (`MaNV`, `CaLam`, `NgayLam`),
  FOREIGN KEY (MaNV) REFERENCES nhanvien (MaNV)
  
  );


  
	  CREATE TABLE IF NOT EXISTS `quanliduan`.`Nhanvien` (
	  `MaNV` INT UNSIGNED NOT NULL,
	  `Hoten` VARCHAR(45) NOT NULL,
	  `CCCD` VARCHAR(45) NOT NULL,
	  `Gioitinh` VARCHAR(45) NOT NULL,
	  `Ngaysinh` DATE NOT NULL,
	  `DiaChi` VARCHAR(45) NOT NULL,
	  `ChucVu` VARCHAR(45) NOT NULL,
	  `SDT` VARCHAR(45) NOT NULL,
	  `Password` VARCHAR(45) NOT NULL,
	  `NgayBatDau` DATE NOT NULL,
	  PRIMARY KEY (`MaNV`),
	  UNIQUE INDEX `CCCD_UNIQUE` (`CCCD` ASC) VISIBLE);
	  
	INSERT INTO `quanliduan`.`account` (`id`, `userName`, `Password`, `Gender`, `Phone`, `FullName`) VALUES ('2003', 'TINTIN', '123123', 'NAM', '123124124', 'CAO DUC TIN');

drop table  hoadon;
 CREATE TABLE `quanliduan`.`HoaDon`(
	ID INT PRIMARY KEY,
    NgayLap DATE,
    ThanhTien DECIMAL(30,10)
  );
  
  -- chèn dữ liệu 
  
INSERT INTO `quanliduan`.`hoadon` (`ID`, `NgayLap`) VALUES ('12345', '2023-10-11');
INSERT INTO `quanliduan`.`hoadon` (`ID`, `NgayLap`) VALUES ('12346', '2023-11-12');
INSERT INTO `quanliduan`.`hoadon` (`ID`, `NgayLap`) VALUES ('12347', '2023-9-10');

  
  ALTER TABLE sanpham ADD INDEX idx_giaban (GiaBan);
  drop table chitiethoadon;
  CREATE TABLE `quanliduan`.`ChiTietHoaDon`(
	ID INT,
    MaNV INT UNSIGNED NOT NULL,
    MaChiTietHoaDon INT auto_increment NOT NULL,
    MaSanPham INT NOT NULL,
    SoLuong INT NOT NULL,
    GiaBan DECIMAL(30,10) NOT NULL,
    TongTien DECIMAL(30,10) AS (SoLuong * GiaBan),
    FOREIGN KEY (MaNV) REFERENCES nhanvien(MaNV),
    FOREIGN KEY (ID) REFERENCES hoadon(ID),
    FOREIGN KEY (MaSanPham) REFERENCES sanpham(MaSanPham),
    FOREIGN KEY (GiaBan) REFERENCES sanpham(GiaBan),
    PRIMARY KEY (`MaChiTietHoaDon`, `MaSanPham`, `ID`)
  );
UPDATE ChiTietHoaDon SET TongTien = GiaBan * SoLuong;

INSERT INTO `quanliduan`.`Nhanvien` (`MaNV`, `Hoten`, `CCCD`, `Gioitinh`, `Ngaysinh`, `DiaChi`, `ChucVu`, `SDT`, `Password`, `NgayBatDau`) VALUES ('2003', 'Nguyen thanh tuan', '5998342', 'Nam', '2003-03-02', '7774', 'Nhân viên bán hàng', '384823', '47858974', '2023-04-01');
INSERT INTO `quanliduan`.`Nhanvien` (`MaNV`, `Hoten`, `CCCD`, `Gioitinh`, `Ngaysinh`, `DiaChi`, `ChucVu`, `SDT`, `Password`, `NgayBatDau`) VALUES ('2004', 'Vo van luy', '823848', 'Nữ', '2003-01-01', '83823', 'Không chọn', '23848923', '7818932', '2003-12-12');
INSERT INTO `quanliduan`.`Nhanvien` (`MaNV`, `Hoten`, `CCCD`, `Gioitinh`, `Ngaysinh`, `DiaChi`, `ChucVu`, `SDT`, `Password`, `NgayBatDau`) VALUES ('2005', 'Nguyen thanh huy', '234234', 'Nam', '2006-01-08', '888384', 'Nhân viên bán hàng', '832489212', '889048957', '2023-04-01');

drop table sanpham; 
CREATE TABLE `quanliduan`.`SanPham` (
  `MaSanPham` INT NOT NULL,
  `TenSanPham` VARCHAR(45) NOT NULL,
  `LoaiSanPham` VARCHAR(45) NOT NULL,
  `ThuongHieu` VARCHAR(45) NOT NULL,
  `SoLuong` INT NOT NULL,
  `GiaBan`  DECIMAL(30,10) NOT NULL,
  `NgayNhap` DATE  NOT NULL,
  PRIMARY KEY (`MaSanPham`));
  
  -- chen dữ liều
INSERT INTO `quanliduan`.`sanpham` (`MaSanPham`, `TenSanPham`, `LoaiSanPham`, `ThuongHieu`, `SoLuong`, `GiaBan`, `NgayNhap`) VALUES ('4488', 'chuột gaming', 'chuột', 'logitech', '300', '700000', '2023-05-11');
INSERT INTO `quanliduan`.`sanpham` (`MaSanPham`, `TenSanPham`, `LoaiSanPham`, `ThuongHieu`, `SoLuong`, `GiaBan`, `NgayNhap`) VALUES ('4489', 'bàn phím gaming', 'bàn phím', 'asus', '150', '300000', '2023-05-11');
INSERT INTO `quanliduan`.`sanpham` (`MaSanPham`, `TenSanPham`, `LoaiSanPham`, `ThuongHieu`, `SoLuong`, `GiaBan`, `NgayNhap`) VALUES ('4490', 'tai nghe gaming', 'tai nghe', 'rapoo', '700', '150000', '2023-05-11');  
  
  
  -- chèn dữ liệu vào chitiethoadon

INSERT INTO `quanliduan`.`ChiTietHoaDon` (`MaNV`, `MaChiTietHoaDon`, `MaSanPham`, `SoLuong`, `GiaBan`, `ID`) 
VALUES  
    (2004, 223344, 4488, 5, 700000, 12345), 
    (2004, 223344, 4489, 3, 300000, 12345),
    (2004, 223344, 4490, 2, 150000, 12345);
  


