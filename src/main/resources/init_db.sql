CREATE SCHEMA `taxi` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `taxi`.`manufacturer`
(
                                `manufacturer_id` bigint NOT NULL AUTO_INCREMENT,
                                `manufacturer_name` varchar(225) NOT NULL,
                                `country` varchar(225) NOT NULL,
                                `is_deleted` bit(1) NOT NULL,
                                PRIMARY KEY (`manufacturer_id`)
) ENGINE=InnoDB