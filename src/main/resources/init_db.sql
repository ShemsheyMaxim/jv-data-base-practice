CREATE SCHEMA `taxi` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `taxi`.`manufacturer`
(
                                `manufacturer_id` bigint NOT NULL AUTO_INCREMENT,
                                `manufacturer_name` varchar(225) NOT NULL,
                                `country` varchar(225) NOT NULL,
                                `is_deleted` bit(1) NOT NULL,
                                PRIMARY KEY (`manufacturer_id`)
) ENGINE=InnoDB;
ALTER TABLE `taxi`.`manufacturer`
    RENAME TO  `taxi`.`manufacturers` ;
CREATE TABLE `taxi`.`drivers` (
                                  `driver_id` BIGINT(11) NOT NULL,
                                  `driver_name` VARCHAR(225) NOT NULL,
                                  `licence_number` VARCHAR(225) NOT NULL,
                                  `is_deleted` BIT NOT NULL,
                                  PRIMARY KEY (`driver_id`)
) ENGINE = InnoDB;
ALTER TABLE `taxi`.`drivers`
    CHANGE COLUMN `driver_id` `driver_id` BIGINT NOT NULL AUTO_INCREMENT ;
CREATE TABLE `taxi`.`cars` (
                               `cars_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
                               `model` VARCHAR(225) NOT NULL,
                               `manufacturer_id` BIGINT(11) NOT NULL,
                               `is_deleted` BIT NOT NULL,
                               PRIMARY KEY (`cars_id`),
                               INDEX `cars_manufacturers_id_idx` (`manufacturer_id` ASC) VISIBLE,
                               CONSTRAINT `cars_manufacturers_id`
                                   FOREIGN KEY (`manufacturer_id`)
                                       REFERENCES `taxi`.`manufacturer` (`manufacturer_id`)
                                       ON DELETE NO ACTION
                                       ON UPDATE NO ACTION);
CREATE TABLE `taxi`.`cars_drivers` (
                                       `car_id` BIGINT(11) NOT NULL,
                                       `driver_id` BIGINT(11) NOT NULL,
                                       INDEX `car_id_idx` (`car_id` ASC) VISIBLE,
                                       INDEX `driver_id_idx` (`driver_id` ASC) VISIBLE,
                                       CONSTRAINT `car_id`
                                           FOREIGN KEY (`car_id`)
                                               REFERENCES `taxi`.`cars` (`cars_id`)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION,
                                       CONSTRAINT `driver_id`
                                           FOREIGN KEY (`driver_id`)
                                               REFERENCES `taxi`.`drivers` (`driver_id`)
                                               ON DELETE NO ACTION
                                               ON UPDATE NO ACTION);
ALTER TABLE `taxi`.`cars`
    CHANGE COLUMN `is_deleted` `is_deleted` BIT(1) NOT NULL DEFAULT false ;
ALTER TABLE `taxi`.`drivers`
    CHANGE COLUMN `is_deleted` `is_deleted` BIT(1) NOT NULL DEFAULT false ;
ALTER TABLE `taxi`.`manufacturers`
    CHANGE COLUMN `is_deleted` `is_deleted` BIT(1) NOT NULL DEFAULT false ;