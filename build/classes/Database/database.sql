drop database quanliduan;
create database quanliduan;
CREATE TABLE IF NOT EXISTS `quanliduan`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Gender` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(45) NOT NULL,
  `FullName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

SELECT * FROM quanliduan.Nhanvien;
CREATE TABLE `quanliduan`.`PhanCong` (
  `MaNV` INT NOT NULL,
  `CaLam` VARCHAR(40) NOT NULL,
  `NgayLam` DATE NOT NULL,
  `TrangThai` VARCHAR(45) DEFAULT 'Chua hoan thanh',
  PRIMARY KEY (`MaNV`, `CaLam`, `NgayLam`));

  
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
  
INSERT INTO `quanliduan`.`account` (`id`, `userName`, `Password`, `Gender`, `Phone`, `FullName`) VALUES ('3', 'TINTIN', '123123', 'NAM', '123124124', 'CAO DUC TIN');

 CREATE TABLE `quanliduan`.`HoaDon`(
	ID INT PRIMARY KEY,
    NgayLap DATE
  );
  CREATE TABLE `quanliduan`.`ChiTietHoaDon`(
	ID INT PRIMARY KEY,
    MaNV INT UNSIGNED NOT NULL,
    MaChiTietHoaDon INT NOT NULL,
    MaSanPham INT NOT NULL,
    SoLuong INT NOT NULL,
    GiaBan DECIMAL(10,2) NOT NULL,
    TongTien DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (MaNV) REFERENCES nhanvien(MaNV),
    FOREIGN KEY (ID) REFERENCES hoadon(ID),
    FOREIGN KEY (MaSanPham) REFERENCES sanpham(MaSanPham)
  );
UPDATE ChiTietHoaDon SET TongTien = GiaBan * SoLuong;

INSERT INTO `quanliduan`.`Nhanvien` (`MaNV`, `Hoten`, `CCCD`, `Gioitinh`, `Ngaysinh`, `DiaChi`, `ChucVu`, `SDT`, `Password`, `NgayBatDau`) VALUES ('2003', 'Nguyen thanh tuan', '5998342', 'Nam', '2003-03-02', '7774', 'Nhân viên bán hàng', '384823', '47858974', '2023-04-01');
INSERT INTO `quanliduan`.`Nhanvien` (`MaNV`, `Hoten`, `CCCD`, `Gioitinh`, `Ngaysinh`, `DiaChi`, `ChucVu`, `SDT`, `Password`, `NgayBatDau`) VALUES ('2004', 'Vo van luy', '823848', 'Nữ', '2003-01-01', '83823', 'Không chọn', '23848923', '7818932', '2003-12-12');
INSERT INTO `quanliduan`.`Nhanvien` (`MaNV`, `Hoten`, `CCCD`, `Gioitinh`, `Ngaysinh`, `DiaChi`, `ChucVu`, `SDT`, `Password`, `NgayBatDau`) VALUES ('2005', 'Nguyen thanh huy', '234234', 'Nam', '2006-01-08', '888384', 'Nhân viên bán hàng', '832489212', '889048957', '2023-04-01');
CREATE TABLE `quanliduan`.`SanPham` (
  `MaSanPham` INT NOT NULL,
  `TenSanPham` VARCHAR(45) NOT NULL,
  `LoaiSanPham` VARCHAR(45) NOT NULL,
  `ThuongHieu` VARCHAR(45) NOT NULL,
  `SoLuong` INT NOT NULL,
  `Gia` VARCHAR(45) NOT NULL,
  `NgayNhap` date  NOT NULL,
  PRIMARY KEY (`MaSanPham`));
  ALTER TABLE sanpham MODIFY Gia DECIMAL(10,2) NOT NULL;
  
-- UPDATE ChiTietHoaDon SET TongTien = GiaBan * SoLuong WHERE SoLuong >0;
UPDATE ChiTietHoaDon
JOIN SanPham ON ChiTietHoaDon.MaSanPham = SanPham.MaSanPham
SET ChiTietHoaDon.TongTien = SanPham.Gia * ChiTietHoaDon.SoLuong;

ALTER TABLE HoaDon ADD COLUMN Tong DECIMAL(10,2) NOT NULL AFTER NgayLap;
UPDATE HoaDon
SET Tong = (SELECT SUM(TongTien) FROM ChiTietHoaDon)