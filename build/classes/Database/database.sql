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
