-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema moe_sounds
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `moe_sounds` ;

-- -----------------------------------------------------
-- Schema moe_sounds
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `moe_sounds` DEFAULT CHARACTER SET utf8 ;
USE `moe_sounds` ;

-- -----------------------------------------------------
-- Table `moe_sounds`.`page`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `moe_sounds`.`page` ;

CREATE TABLE IF NOT EXISTS `moe_sounds`.`page` (
  `page_id` INT(11) NOT NULL AUTO_INCREMENT,
  `page_name` VARCHAR(45) NOT NULL,
  `css` MEDIUMTEXT NULL DEFAULT NULL,
  `default_background` VARCHAR(45) NULL DEFAULT NULL,
  `click_count` BIGINT(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`page_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 48
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `moe_sounds`.`media`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `moe_sounds`.`media` ;

CREATE TABLE IF NOT EXISTS `moe_sounds`.`media` (
  `media_id` INT(11) NOT NULL AUTO_INCREMENT,
  `page_id` INT(11) NOT NULL,
  `media_type` VARCHAR(45) NOT NULL,
  `file_name` VARCHAR(255) NOT NULL,
  `file_type` VARCHAR(45) NOT NULL,
  `file_data` MEDIUMBLOB NOT NULL,
  `file_size` INT(11) NOT NULL,
  PRIMARY KEY (`media_id`),
  INDEX `page_id_idx` (`page_id` ASC),
  UNIQUE INDEX `media_unique` (`page_id` ASC, `media_type` ASC),
  CONSTRAINT `page_id`
    FOREIGN KEY (`page_id`)
    REFERENCES `moe_sounds`.`page` (`page_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 70
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `moe_sounds`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `moe_sounds`.`user` ;

CREATE TABLE IF NOT EXISTS `moe_sounds`.`user` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(45) NOT NULL,
  `user_role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `moe_sounds`.`user_api_identity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `moe_sounds`.`user_api_identity` ;

CREATE TABLE IF NOT EXISTS `moe_sounds`.`user_api_identity` (
  `user_api_identity_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `api_type` VARCHAR(45) NOT NULL,
  `api_user_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_api_identity_id`),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `moe_sounds`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO moesounds;
 DROP USER moesounds;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'moesounds' IDENTIFIED BY 'password-goes-here';

GRANT ALL ON `moe_sounds`.* TO 'moesounds';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
