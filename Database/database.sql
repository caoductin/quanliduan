create database quanliduan;
CREATE TABLE IF NOT EXISTS `quanliduan`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Gender` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(45) NOT NULL,
  `FullName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
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
  
INSERT INTO `quanliduan`.`account` (`id`, `userName`, `Password`, `Gender`, `Phone`, `FullName`) VALUES ('3', 'TINTIN', '123123', 'NAM', '123124124', 'CAO DUC TIN');

CREATE TABLE IF NOT EXISTS `quanliduan`.`Sanpham`(
	`MaSanPham` INT PRIMARY KEY NOT NULL,
    `TenSanPham`VARCHAR(45) NOT NULL,
	`GiaSanPham` FLOAT NOT NULL,
    `SoLuongSanPham` INT NOT NULL,
    `LoaiSanPham` VARCHAR(10) NOT NULL,
    `ThuongHieu` VARCHAR(10) NOT NULL
);
CREATE TABLE IF NOT EXISTS `quanliduan`.`HoaDon`(
	`MaHoaDon` INT PRIMARY KEY NOT NULL,
    `SoDienThoai` INT(10) NOT NULL,
    `MaNV` INT UNSIGNED NOT NULL,
    `NgayMua` DATE,
    `TongTien` FLOAT NOT NULL,
    FOREIGN KEY (`MaNV`) REFERENCES `nhanvien`(`MaNV`)
);
CREATE TABLE IF NOT EXISTS `quanliduan`.`ChiTietHoaDon`(
	`MaChiTietHoaDon` INT NOT NULL,
    `MaHoaDon` INT NOT NULL,
    `MaSanPham` INT NOT NULL,
    PRIMARY KEY (`MaChiTietHoaDon`,`MaSanPham`),
    FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon`(`MaHoaDon`)
  --  FOREIGN KEY (`MaSanPham`) REFERENCES `sanpham`(`MaSanPham`)
);
