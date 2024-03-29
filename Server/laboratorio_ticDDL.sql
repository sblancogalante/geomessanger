SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE DATABASE IF NOT EXISTS `laboratorio_tic` ;
USE `laboratorio_tic` ;

-- -----------------------------------------------------
-- Table `laboratorio_tic`.`Employees`
-- -----------------------------------------------------
CREATE TABLE `Employees` (
	`employeeID` INT(10) NOT NULL AUTO_INCREMENT,
	`document` VARCHAR(30) NOT NULL DEFAULT 'Cedula', 
	`iD` VARCHAR(20) NOT NULL,
	`name` VARCHAR(20),
	`lastName` VARCHAR(20),
	`userName` VARCHAR(20) NOT NULL ,
	`password` VARCHAR(100) NOT NULL,
	`location` VARCHAR(30) NOT NULL,
	`sector` VARCHAR(30) NOT NULL,
	`email` VARCHAR(30) NOT NULL,
	`position` VARCHAR(30),
	`workingHour` VARCHAR(20),
	`profilePicture` LONGBLOB DEFAULT NULL,
	`status` BOOLEAN NOT NULL DEFAULT false,
	`admin` BOOLEAN NOT NULL DEFAULT false,
	PRIMARY KEY (`employeeID`),
	UNIQUE KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `laboratoirio_tic`.`TextMessages`
-- -----------------------------------------------------
CREATE TABLE `TextMessages` (
	`textMessageID` BIGINT(10) NOT NULL AUTO_INCREMENT,
	`text` VARCHAR(300),
	`employeeSenderID` INT(10) NOT NULL,
	`employeeReceiverID` INT(10) NOT NULL,
	`date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`textMessageID`),
	FOREIGN KEY (`employeeSenderID`) REFERENCES `Employees`(`employeeID`) ON DELETE CASCADE ON UPDATE NO ACTION,
	FOREIGN KEY (`employeeReceiverID`) REFERENCES `Employees`(`employeeID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `laboratrorio_tic`.`FileMessages`
-- -----------------------------------------------------
CREATE TABLE `FileMessages` (
	`fileMessageID` INT(10) NOT NULL AUTO_INCREMENT,
	`file` LONGBLOB NOT NULL,
	`fileName` VARCHAR(50) NOT NULL,
	`employeeSenderID` INT(10) NOT NULL,
	`employeeReceiverID` INT(10) NOT NULL,
	`date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`fileMessageID`),
	FOREIGN KEY (`employeeSenderID`) REFERENCES `Employees`(`employeeID`) ON DELETE CASCADE ON UPDATE NO ACTION,
	FOREIGN KEY (`employeeReceiverID`) REFERENCES `Employees`(`employeeID`) ON DELETE CASCADE ON UPDATE NO ACTION	
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- -----------------------------------------------------
-- Table `laboratorio_tic`.`Types`
-- -----------------------------------------------------
CREATE TABLE `Types` (
	`typeID` INT(10) NOT NULL AUTO_INCREMENT, 
	`type` VARCHAR(30) NOT NULL, 
	`value` VARCHAR(30) NOT NULL,	
	PRIMARY KEY (`typeID`),	
	UNIQUE KEY(`value`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
