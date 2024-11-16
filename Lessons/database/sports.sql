-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sports
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sports
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sports` DEFAULT CHARACTER SET utf8 ;
USE `sports` ;

-- -----------------------------------------------------
-- Table `sports`.`USER_TYPES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sports`.`USER_TYPES` ;

CREATE TABLE IF NOT EXISTS `sports`.`USER_TYPES` (
  `user_type_code` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NULL,
  PRIMARY KEY (`user_type_code`),
  UNIQUE INDEX `USER_TYPE_ID_UNIQUE` (`user_type_code` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sports`.`USERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sports`.`USERS` ;

CREATE TABLE IF NOT EXISTS `sports`.`USERS` (
  `user_id` VARCHAR(32) NOT NULL,
  `email` VARCHAR(128) NULL,
  `phone` VARCHAR(16) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `password` VARCHAR(32) NULL,
  `user_type_code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_USERS_USER_TYPE_idx` (`user_type_code` ASC),
  CONSTRAINT `fk_USERS_USER_TYPE`
    FOREIGN KEY (`user_type_code`)
    REFERENCES `sports`.`USER_TYPES` (`user_type_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sports`.`CITIES`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sports`.`CITIES` ;

CREATE TABLE IF NOT EXISTS `sports`.`CITIES` (
  `city_code` VARCHAR(45) NOT NULL,
  `city_name` VARCHAR(45) NULL,
  `province` VARCHAR(45) NULL,
  PRIMARY KEY (`city_code`),
  UNIQUE INDEX `city_code_UNIQUE` (`city_code` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sports`.`SPECIALIZATIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sports`.`SPECIALIZATIONS` ;

CREATE TABLE IF NOT EXISTS `sports`.`SPECIALIZATIONS` (
  `specialization_code` VARCHAR(45) NOT NULL,
  `tite` VARCHAR(45) NULL,
  `duration` INT NULL,
  PRIMARY KEY (`specialization_code`),
  UNIQUE INDEX `specialization_code_UNIQUE` (`specialization_code` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sports`.`LOCATIONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sports`.`LOCATIONS` ;

CREATE TABLE IF NOT EXISTS `sports`.`LOCATIONS` (
  `location_code` VARCHAR(45) NOT NULL,
  `title` VARCHAR(60) NULL,
  `city_code` VARCHAR(45) NOT NULL,
  `specialization_code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`location_code`),
  INDEX `fk_LOCATIONS_CITIES1_idx` (`city_code` ASC),
  INDEX `fk_LOCATIONS_SPECIALIZATIONS1_idx` (`specialization_code` ASC),
  CONSTRAINT `fk_LOCATIONS_CITIES1`
    FOREIGN KEY (`city_code`)
    REFERENCES `sports`.`CITIES` (`city_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LOCATIONS_SPECIALIZATIONS1`
    FOREIGN KEY (`specialization_code`)
    REFERENCES `sports`.`SPECIALIZATIONS` (`specialization_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sports`.`TIME_SLOTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sports`.`TIME_SLOTS` ;

CREATE TABLE IF NOT EXISTS `sports`.`TIME_SLOTS` (
  `time_slot_id` INT NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NULL,
  `end_time` DATETIME NULL,
  `day` DATE NULL,
  `location_code` VARCHAR(45) NULL,
  PRIMARY KEY (`time_slot_id`),
  INDEX `fk_TIME_SLOTS_LOCATIONS1_idx` (`location_code` ASC),
  CONSTRAINT `fk_TIME_SLOTS_LOCATIONS1`
    FOREIGN KEY (`location_code`)
    REFERENCES `sports`.`LOCATIONS` (`location_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sports`.`LESSONS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sports`.`LESSONS` ;

CREATE TABLE IF NOT EXISTS `sports`.`LESSONS` (
  `lesson_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `specialization_code` VARCHAR(45) NOT NULL,
  `group_size` INT NULL,
  `time_slot_id` INT NOT NULL,
  PRIMARY KEY (`lesson_id`),
  INDEX `fk_LESSONS_SPECIALIZATIONS1_idx` (`specialization_code` ASC),
  INDEX `fk_LESSONS_TIME_SLOTS1_idx` (`time_slot_id` ASC),
  CONSTRAINT `fk_LESSONS_SPECIALIZATIONS1`
    FOREIGN KEY (`specialization_code`)
    REFERENCES `sports`.`SPECIALIZATIONS` (`specialization_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LESSONS_TIME_SLOTS1`
    FOREIGN KEY (`time_slot_id`)
    REFERENCES `sports`.`TIME_SLOTS` (`time_slot_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sports`.`INSTRUCTOR`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sports`.`INSTRUCTOR` ;

CREATE TABLE IF NOT EXISTS `sports`.`INSTRUCTOR` (
  `instuctor_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(32) NOT NULL,
  `cities_availability` VARCHAR(60) NULL,
  `specialization_code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`instuctor_id`),
  INDEX `fk_INSTRUCTOR_USERS1_idx` (`user_id` ASC),
  INDEX `fk_INSTRUCTOR_SPECIALIZATIONS1_idx` (`specialization_code` ASC),
  CONSTRAINT `fk_INSTRUCTOR_USERS1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sports`.`USERS` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_INSTRUCTOR_SPECIALIZATIONS1`
    FOREIGN KEY (`specialization_code`)
    REFERENCES `sports`.`SPECIALIZATIONS` (`specialization_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sports`.`CLIENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sports`.`CLIENT` ;

CREATE TABLE IF NOT EXISTS `sports`.`CLIENT` (
  `client_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(32) NOT NULL,
  `age` INT NOT NULL DEFAULT 0,
  `guardian_name` VARCHAR(60) NULL,
  PRIMARY KEY (`client_id`),
  INDEX `fk_CLIENT_USERS1_idx` (`user_id` ASC),
  CONSTRAINT `fk_CLIENT_USERS1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sports`.`USERS` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sports`.`BOOKINGS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sports`.`BOOKINGS` ;

CREATE TABLE IF NOT EXISTS `sports`.`BOOKINGS` (
  `booking_id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `lesson_id` INT NOT NULL,
  PRIMARY KEY (`booking_id`),
  INDEX `fk_BOOKINGS_CLIENT1_idx` (`client_id` ASC),
  INDEX `fk_BOOKINGS_LESSONS1_idx` (`lesson_id` ASC),
  CONSTRAINT `fk_BOOKINGS_CLIENT1`
    FOREIGN KEY (`client_id`)
    REFERENCES `sports`.`CLIENT` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BOOKINGS_LESSONS1`
    FOREIGN KEY (`lesson_id`)
    REFERENCES `sports`.`LESSONS` (`lesson_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sports`.`REGISTER_OFFER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sports`.`REGISTER_OFFER` ;

CREATE TABLE IF NOT EXISTS `sports`.`REGISTER_OFFER` (
  `register_offer_id` INT NOT NULL AUTO_INCREMENT,
  `instuctor_id` INT NOT NULL,
  `lesson_id` INT NOT NULL,
  PRIMARY KEY (`register_offer_id`),
  INDEX `fk_REGISTER_OFFER_INSTRUCTOR1_idx` (`instuctor_id` ASC),
  INDEX `fk_REGISTER_OFFER_LESSONS1_idx` (`lesson_id` ASC),
  CONSTRAINT `fk_REGISTER_OFFER_INSTRUCTOR1`
    FOREIGN KEY (`instuctor_id`)
    REFERENCES `sports`.`INSTRUCTOR` (`instuctor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_REGISTER_OFFER_LESSONS1`
    FOREIGN KEY (`lesson_id`)
    REFERENCES `sports`.`LESSONS` (`lesson_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `sports`.`USER_TYPES`
-- -----------------------------------------------------
START TRANSACTION;
USE `sports`;
INSERT INTO `sports`.`USER_TYPES` (`user_type_code`, `title`) VALUES ('ADMIN', 'Administrator');
INSERT INTO `sports`.`USER_TYPES` (`user_type_code`, `title`) VALUES ('INSTRUCTOR', 'Instructor');
INSERT INTO `sports`.`USER_TYPES` (`user_type_code`, `title`) VALUES ('CLIENT', 'Client');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sports`.`USERS`
-- -----------------------------------------------------
START TRANSACTION;
USE `sports`;
INSERT INTO `sports`.`USERS` (`user_id`, `email`, `phone`, `first_name`, `last_name`, `password`, `user_type_code`) VALUES ('admin', 'admin@example.com', '000-000-0001', 'Admin', 'Admin', 'admin', 'ADMIN');
INSERT INTO `sports`.`USERS` (`user_id`, `email`, `phone`, `first_name`, `last_name`, `password`, `user_type_code`) VALUES ('instructor01', 'instructor01@eample.com', '000-000-0002', 'instructor01', 'instructor01', 'instructor01', 'INSTRUCTOR');
INSERT INTO `sports`.`USERS` (`user_id`, `email`, `phone`, `first_name`, `last_name`, `password`, `user_type_code`) VALUES ('client01', 'client01@example.com', '000-000-0003', 'client01', 'client01', 'client01', 'CLIENT');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sports`.`CITIES`
-- -----------------------------------------------------
START TRANSACTION;
USE `sports`;
INSERT INTO `sports`.`CITIES` (`city_code`, `city_name`, `province`) VALUES ('MONTREAL', 'Montreal', 'Quebec');
INSERT INTO `sports`.`CITIES` (`city_code`, `city_name`, `province`) VALUES ('QUEBEC', 'Quebec City', 'Quebec');
INSERT INTO `sports`.`CITIES` (`city_code`, `city_name`, `province`) VALUES ('LAVAL', 'Laval', 'Quebec');
INSERT INTO `sports`.`CITIES` (`city_code`, `city_name`, `province`) VALUES ('LONGUEUIL', 'Longueuil', 'Quebec');
INSERT INTO `sports`.`CITIES` (`city_code`, `city_name`, `province`) VALUES ('GATINEAU', 'Gatineau', 'Quebec');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sports`.`SPECIALIZATIONS`
-- -----------------------------------------------------
START TRANSACTION;
USE `sports`;
INSERT INTO `sports`.`SPECIALIZATIONS` (`specialization_code`, `tite`, `duration`) VALUES ('JUDO', 'Judo', 60);
INSERT INTO `sports`.`SPECIALIZATIONS` (`specialization_code`, `tite`, `duration`) VALUES ('SWIMMING', 'Swimming', 30);
INSERT INTO `sports`.`SPECIALIZATIONS` (`specialization_code`, `tite`, `duration`) VALUES ('YOGA', 'Yoga', 60);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sports`.`LOCATIONS`
-- -----------------------------------------------------
START TRANSACTION;
USE `sports`;
INSERT INTO `sports`.`LOCATIONS` (`location_code`, `title`, `city_code`, `specialization_code`) VALUES ('GYM001', 'Gym 001', 'MONTREAL', 'JUDO');
INSERT INTO `sports`.`LOCATIONS` (`location_code`, `title`, `city_code`, `specialization_code`) VALUES ('GYM002', 'Gym 002', 'MONTREAL', 'YOGA');
INSERT INTO `sports`.`LOCATIONS` (`location_code`, `title`, `city_code`, `specialization_code`) VALUES ('SPOOL001', 'Swimming Pool 001', 'LAVAL', 'SWIMMING');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sports`.`TIME_SLOTS`
-- -----------------------------------------------------
START TRANSACTION;
USE `sports`;
INSERT INTO `sports`.`TIME_SLOTS` (`time_slot_id`, `start_time`, `end_time`, `day`, `location_code`) VALUES (1, '2024-11-15 13:00:00	', '2024-11-15 14:00:00	', '2024-11-15', 'GYM001');
INSERT INTO `sports`.`TIME_SLOTS` (`time_slot_id`, `start_time`, `end_time`, `day`, `location_code`) VALUES (2, '2024-11-15 14:00:00	', '2024-11-15 15:00:00	', '2024-11-15', 'GYM001');
INSERT INTO `sports`.`TIME_SLOTS` (`time_slot_id`, `start_time`, `end_time`, `day`, `location_code`) VALUES (3, '2024-11-15 15:00:00	', '2024-11-15 16:00:00	', '2024-11-15', 'GYM001');
INSERT INTO `sports`.`TIME_SLOTS` (`time_slot_id`, `start_time`, `end_time`, `day`, `location_code`) VALUES (4, '2024-11-15 14:00:00	', '2024-11-15 17:00:00	', '2024-11-15	', 'GYM001');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sports`.`LESSONS`
-- -----------------------------------------------------
START TRANSACTION;
USE `sports`;
INSERT INTO `sports`.`LESSONS` (`lesson_id`, `title`, `specialization_code`, `group_size`, `time_slot_id`) VALUES (1, 'Judo Class Group', 'JUDO', 10, 1);
INSERT INTO `sports`.`LESSONS` (`lesson_id`, `title`, `specialization_code`, `group_size`, `time_slot_id`) VALUES (2, 'Judo Class Group', 'JUDO', 10, 2);
INSERT INTO `sports`.`LESSONS` (`lesson_id`, `title`, `specialization_code`, `group_size`, `time_slot_id`) VALUES (3, 'Judo Class Private', 'JUDO', 1, 3);
INSERT INTO `sports`.`LESSONS` (`lesson_id`, `title`, `specialization_code`, `group_size`, `time_slot_id`) VALUES (4, 'Judo Class Private', 'JUDO', 1, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sports`.`INSTRUCTOR`
-- -----------------------------------------------------
START TRANSACTION;
USE `sports`;
INSERT INTO `sports`.`INSTRUCTOR` (`instuctor_id`, `user_id`, `cities_availability`, `specialization_code`) VALUES (1, 'instructor01', 'MONTREAL,LAVAL', 'JUDO');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sports`.`CLIENT`
-- -----------------------------------------------------
START TRANSACTION;
USE `sports`;
INSERT INTO `sports`.`CLIENT` (`client_id`, `user_id`, `age`, `guardian_name`) VALUES (1, 'client01', false, NULL);

COMMIT;

