CREATE SCHEMA `taxi` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `taxi`.`manufacturer`
(
                                `manufacturer_id` bigint NOT NULL AUTO_INCREMENT,
                                `manufacturer_name` varchar(225) NOT NULL,
                                `country` varchar(225) NOT NULL,
                                `is_deleted` bit(1) NOT NULL,
                                PRIMARY KEY (`manufacturer_id`)
) ENGINE=InnoDB;
CREATE TABLE `taxi`.`drivers` (
                                  `driver_id` BIGINT(11) NOT NULL,
                                  `driver_name` VARCHAR(225) NOT NULL,
                                  `licence_number` VARCHAR(225) NOT NULL,
                                  `is_deleted` BIT NOT NULL,
                                  PRIMARY KEY (`driver_id`)
) ENGINE = InnoDB;
ALTER TABLE `taxi`.`drivers`
    CHANGE COLUMN `driver_id` `driver_id` BIGINT NOT NULL AUTO_INCREMENT ;