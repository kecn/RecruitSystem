SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `RecruitSystemDB` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `RecruitSystemDB` ;

-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`person`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`person` (
  `personid` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `surname` VARCHAR(45) NOT NULL ,
  `ssn` INT NOT NULL ,
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
    ON DELETE NO ACTION
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
-- Table `RecruitSystemDB`.`competenceprofile`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`competenceprofile` (
  `competenceprofileid` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `personid` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`competenceprofileid`) ,
  UNIQUE INDEX `competenceprofileid_UNIQUE` (`competenceprofileid` ASC) ,
  INDEX `fk_competenceprofile_person1` (`personid` ASC) ,
  CONSTRAINT `fk_competenceprofile_person1`
    FOREIGN KEY (`personid` )
    REFERENCES `RecruitSystemDB`.`person` (`personid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`availability`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`availability` (
  `availabilityid` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `fromdate` DATE NOT NULL ,
  `todate` DATE NULL ,
  `userid` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`availabilityid`) ,
  UNIQUE INDEX `availabilityid_UNIQUE` (`availabilityid` ASC) ,
  INDEX `fk_availability_user1` (`userid` ASC) ,
  CONSTRAINT `fk_availability_user1`
    FOREIGN KEY (`userid` )
    REFERENCES `RecruitSystemDB`.`person` (`personid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`user` (
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `personid` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`username`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) ,
  INDEX `fk_user_person1` (`personid` ASC) ,
  CONSTRAINT `fk_user_person1`
    FOREIGN KEY (`personid` )
    REFERENCES `RecruitSystemDB`.`person` (`personid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`role`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`role` (
  `roleid` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`roleid`) ,
  UNIQUE INDEX `roleid_UNIQUE` (`roleid` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`roletranslation`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`roletranslation` (
  `locale` VARCHAR(8) NOT NULL ,
  `name` VARCHAR(45) NULL ,
  `roleid` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`locale`, `roleid`) ,
  INDEX `fk_roletranslation_role1` (`roleid` ASC) ,
  CONSTRAINT `fk_roletranslation_role1`
    FOREIGN KEY (`roleid` )
    REFERENCES `RecruitSystemDB`.`role` (`roleid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`userinrole`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`userinrole` (
  `username` VARCHAR(45) NOT NULL ,
  `roleid` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`username`, `roleid`) ,
  INDEX `fk_userinrole_role1` (`roleid` ASC) ,
  CONSTRAINT `fk_userinrole_user1`
    FOREIGN KEY (`username` )
    REFERENCES `RecruitSystemDB`.`user` (`username` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userinrole_role1`
    FOREIGN KEY (`roleid` )
    REFERENCES `RecruitSystemDB`.`role` (`roleid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RecruitSystemDB`.`competenceinprofile`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `RecruitSystemDB`.`competenceinprofile` (
  `yearsofexperience` INT NULL ,
  `competenceprofileid` INT UNSIGNED NOT NULL ,
  `competenceid` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`competenceprofileid`, `competenceid`) ,
  INDEX `fk_competenceinprofile_competence1` (`competenceid` ASC) ,
  CONSTRAINT `fk_competenceinprofile_competenceprofile1`
    FOREIGN KEY (`competenceprofileid` )
    REFERENCES `RecruitSystemDB`.`competenceprofile` (`competenceprofileid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_competenceinprofile_competence1`
    FOREIGN KEY (`competenceid` )
    REFERENCES `RecruitSystemDB`.`competence` (`competenceid` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Placeholder table for view `RecruitSystemDB`.`userroleview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RecruitSystemDB`.`userroleview` (`username` INT, `password` INT, `rolename` INT);

-- -----------------------------------------------------
-- View `RecruitSystemDB`.`userroleview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RecruitSystemDB`.`userroleview`;
USE `RecruitSystemDB`;
CREATE  OR REPLACE VIEW `mydb`.`userroleview` AS
SELECT `user`.`username` AS `username`,
`user`.`password` AS `password`,
`role`.`roleid` AS `rolename`
FROM ((`userinrole` JOIN `user` 	
ON ((`userinrole`.`username` = `user`.`username`)))
JOIN `role`
ON ((`userinrole`.`roleid` = `role`.`roleid`)));


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
