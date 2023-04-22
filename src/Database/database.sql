create database quanliduan;
CREATE TABLE IF NOT EXISTS `quanliduan`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Gender` VARCHAR(45) NOT NULL,
  `Phone` VARCHAR(45) NOT NULL,
  `FullName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));
  
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



INSERT INTO `quanliduan`.`Nhanvien` (`MaNV`, `Hoten`, `CCCD`, `Gioitinh`, `Ngaysinh`, `DiaChi`, `ChucVu`, `SDT`, `Password`, `NgayBatDau`) VALUES ('2003', 'Nguyen thanh tuan', '5998342', 'Nam', '2003-03-02', '7774', 'Nhân viên bán hàng', '384823', '47858974', '2023-04-01');
INSERT INTO `quanliduan`.`Nhanvien` (`MaNV`, `Hoten`, `CCCD`, `Gioitinh`, `Ngaysinh`, `DiaChi`, `ChucVu`, `SDT`, `Password`, `NgayBatDau`) VALUES ('2004', 'Vo van luy', '823848', 'Nữ', '2003-01-01', '83823', 'Không chọn', '23848923', '7818932', '2003-12-12');
INSERT INTO `quanliduan`.`Nhanvien` (`MaNV`, `Hoten`, `CCCD`, `Gioitinh`, `Ngaysinh`, `DiaChi`, `ChucVu`, `SDT`, `Password`, `NgayBatDau`) VALUES ('2005', 'Nguyen thanh huy', '234234', 'Nam', '2006-01-08', '888384', 'Nhân viên bán hàng', '832489212', '889048957', '2023-04-01');
