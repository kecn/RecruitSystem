SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `RecruitSystemDB` DEFAULT CHARACTER SET utf8 ;
USE `RecruitSystemDB` ;

-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`person`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`person` (
  `personid` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `surname` VARCHAR(45) NOT NULL ,
  `birthdate` DATE NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`personid`) ,
  UNIQUE INDEX `userid_UNIQUE` (`personid` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`application`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`application` (
  `applicationid` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `applicationdate` DATETIME NOT NULL ,
  `personid` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`applicationid`) ,
  INDEX `fk_application_user` (`personid` ASC) ,
  CONSTRAINT `fk_application_user`
    FOREIGN KEY (`personid` )
    REFERENCES `RecruitSystemDB`.`person` (`personid` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`competence`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`competence` (
  `competenceid` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`competenceid`) ,
  UNIQUE INDEX `competenceid_UNIQUE` (`competenceid` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`competencetranslation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`competencetranslation` (
  `locale` VARCHAR(8) NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `competenceid` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`locale`, `competenceid`) ,
  INDEX `fk_competencetranslation_competence1` (`competenceid` ASC) ,
  CONSTRAINT `fk_competencetranslation_competence1`
    FOREIGN KEY (`competenceid` )
    REFERENCES `RecruitSystemDB`.`competence` (`competenceid` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`availability`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`availability` (
  `availabilityid` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `fromdate` DATE NOT NULL ,
  `todate` DATE NULL ,
  `applicationid` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`availabilityid`) ,
  UNIQUE INDEX `availabilityid_UNIQUE` (`availabilityid` ASC) ,
  INDEX `fk_availability_application1` (`applicationid` ASC) ,
  CONSTRAINT `fk_availability_application1`
    FOREIGN KEY (`applicationid` )
    REFERENCES `RecruitSystemDB`.`application` (`applicationid` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`user` (
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(64) NOT NULL ,
  `email` VARCHAR(45) NULL ,
  PRIMARY KEY (`username`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`role`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`role` (
  `rolename` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`rolename`) ,
  UNIQUE INDEX `roleid_UNIQUE` (`rolename` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`userinrole`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`userinrole` (
  `username` VARCHAR(45) NOT NULL ,
  `rolename` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`username`, `rolename`) ,
  INDEX `fk_userinrole_role1` (`rolename` ASC) ,
  CONSTRAINT `fk_userinrole_user1`
    FOREIGN KEY (`username` )
    REFERENCES `RecruitSystemDB`.`user` (`username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userinrole_role1`
    FOREIGN KEY (`rolename` )
    REFERENCES `RecruitSystemDB`.`role` (`rolename` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`competenceinapplication`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`competenceinapplication` (
  `yearsofexperience` INT NULL ,
  `competenceid` INT UNSIGNED NOT NULL ,
  `applicationid` INT UNSIGNED NOT NULL ,
  `competenceinapplicationid` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`competenceinapplicationid`) ,
  INDEX `fk_competenceinprofile_competence1` (`competenceid` ASC) ,
  INDEX `fk_competenceinprofile_application1` (`applicationid` ASC) ,
  UNIQUE INDEX `competenceinapplicationid_UNIQUE` (`competenceinapplicationid` ASC) ,
  CONSTRAINT `fk_competenceinprofile_competence1`
    FOREIGN KEY (`competenceid` )
    REFERENCES `RecruitSystemDB`.`competence` (`competenceid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_competenceinprofile_application1`
    FOREIGN KEY (`applicationid` )
    REFERENCES `RecruitSystemDB`.`application` (`applicationid` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Inserting data into the database
-- -----------------------------------------------------

INSERT INTO `RecruitSystemDB`.`competence` (`competenceid`) VALUES (1);
INSERT INTO `RecruitSystemDB`.`competence` (`competenceid`) VALUES (2);
INSERT INTO `RecruitSystemDB`.`competence` (`competenceid`) VALUES (3);
INSERT INTO `RecruitSystemDB`.`competence` (`competenceid`) VALUES (4);

INSERT INTO `RecruitSystemDB`.`competencetranslation` (`locale`, `name`, `competenceid`) VALUES ('en', 'Java', 1);
INSERT INTO `RecruitSystemDB`.`competencetranslation` (`locale`, `name`, `competenceid`) VALUES ('en', 'MySQL', 2);
INSERT INTO `RecruitSystemDB`.`competencetranslation` (`locale`, `name`, `competenceid`) VALUES ('en', 'C++', 3);
INSERT INTO `RecruitSystemDB`.`competencetranslation` (`locale`, `name`, `competenceid`) VALUES ('en', 'Architectural Design', 4);
INSERT INTO `RecruitSystemDB`.`competencetranslation` (`locale`, `name`, `competenceid`) VALUES ('se', 'Java', 1);
INSERT INTO `RecruitSystemDB`.`competencetranslation` (`locale`, `name`, `competenceid`) VALUES ('se', 'MySQL', 2);
INSERT INTO `RecruitSystemDB`.`competencetranslation` (`locale`, `name`, `competenceid`) VALUES ('se', 'C++', 3);
INSERT INTO `RecruitSystemDB`.`competencetranslation` (`locale`, `name`, `competenceid`) VALUES ('se', 'Arkitekturell Design', 4);

INSERT INTO `RecruitSystemDB`.`role` (`rolename`) VALUES ('recruiter');
INSERT INTO `RecruitSystemDB`.`user` (`username`, `password`, `email`) VALUES ('miikka', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'miikka@com.com');
INSERT INTO `RecruitSystemDB`.`userinrole` (`username`, `rolename`) VALUES ('miikka', 'recruiter');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
