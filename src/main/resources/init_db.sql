CREATE SCHEMA `taxi` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `manufacturers` (
                                 `manufacturer_id` bigint NOT NULL AUTO_INCREMENT,
                                 `manufacturer_name` varchar(225) NOT NULL,
                                 `country` varchar(225) NOT NULL,
                                 `is_deleted` bit(1) NOT NULL DEFAULT b'0',
                                 PRIMARY KEY (`manufacturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `drivers` (
                           `driver_id` bigint NOT NULL AUTO_INCREMENT,
                           `driver_name` varchar(225) NOT NULL,
                           `licence_number` varchar(225) NOT NULL,
                           `is_deleted` bit(1) NOT NULL DEFAULT b'0',
                           `login` varchar(225) NOT NULL,
                           `password` varchar(225) NOT NULL,
                           PRIMARY KEY (`driver_id`),
                           UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `cars` (
                        `cars_id` bigint NOT NULL AUTO_INCREMENT,
                        `model` varchar(225) NOT NULL,
                        `manufacturer_id` bigint NOT NULL,
                        `is_deleted` bit(1) NOT NULL DEFAULT b'0',
                        PRIMARY KEY (`cars_id`),
                        KEY `cars_manufacturers_id_idx` (`manufacturer_id`),
                        CONSTRAINT `cars_manufacturers_id` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturers` (`manufacturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `cars_drivers` (
                                `car_id` bigint NOT NULL,
                                `driver_id` bigint NOT NULL,
                                KEY `car_id_idx` (`car_id`),
                                KEY `driver_id_idx` (`driver_id`),
                                CONSTRAINT `car_id`
                                    FOREIGN KEY (`car_id`)
                                        REFERENCES `cars` (`cars_id`),
                                CONSTRAINT `driver_id`
                                    FOREIGN KEY (`driver_id`)
                                        REFERENCES `drivers` (`driver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
