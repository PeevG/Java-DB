-- Exer 12
CREATE DATABASE `car_rental`;
USE car_rental;

CREATE TABLE `categories` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`category` VARCHAR(30) NOT NULL,
`daily_rate` DOUBLE NOT NULL,
`weekly_rate` DOUBLE NOT NULL,
`monthly_rate` DOUBLE NOT NULL,
`weekend_rate` DOUBLE NOT NULL
);

INSERT INTO `categories` VALUES 
(1,'daily car', 10.50, 50.50, 203, 15),
(2,'daily car2', 10.60, 50.60, 204, 16),
(3,'daily car3', 10.70, 50.70, 205, 17);

CREATE TABLE `cars` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`plate_number` VARCHAR(15) NOT NULL,
`make` VARCHAR(35),
`model` VARCHAR(25) NOT NULL,
`car_year` YEAR NOT NULL,
`category_id` INT NOT NULL,
`doors` INT NOT NULL,
`picture` BLOB,
`car_condition` VARCHAR(20),
`available` BOOLEAN NOT NULL
);

INSERT into `cars` (`plate_number`, `model`, `car_year`, `category_id`, `doors`, `available`)
VALUES
('SV 1514 GT', 'Focus', '2012', 15, 4, true),
('PV 1214 GT', 'A6', '2018', 12, 4, true),
('A 1114 GT', 'CLK', '2009', 13, 2, true);

CREATE TABLE `employees` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(30) NOT NULL,
`last_name` VARCHAR(30) NOT NULL,
`title` VARCHAR(35),
`notes` TEXT
);

INSERT INTO `employees` (`first_name`, `last_name`, `notes`)
VALUES 
('Martin', 'Petrov', NULL),
('Stilian', 'Petrov', 'middle'),
('Hristo', 'Stoichkov', '8');

CREATE TABLE `customers` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`driver_licence_number` VARCHAR(30) NOT NULL,
`full_name` VARCHAR(50) NOT NULL,
`address` VARCHAR(100) NOT NULL,
`city` VARCHAR(40) NOT NULL,
`zip_code` INT,
`notes` TEXT
);

INSERT INTO customers (`driver_licence_number`, `full_name`, `address`, `city`)
VALUES
('AB312', 'Dimitar Berbatov', 'ul.SDWS12', 'Sofia'),
('SB31A2', 'Rumen Radev', 'ul.KWQUL12', 'Sofia'),
('zBA2vb2', 'Nikoleta Stefanova', 'ul.ask1s', 'Sofia');

CREATE TABLE `rental_orders` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`employee_id` INT NOT NULL,
`customer_id` INT NOT NULL,
`car_id` INT NOT NULL,
`car_condition` VARCHAR(50) NOT NULL,
`tank_level` DOUBLE NOT NULL,
`kilometrage_start` DOUBLE NOT NULL,
`kilometrage_end` DOUBLE NOT NULL,
`total_kilometrage` DOUBLE NOT NULL,
`start_date` DATE NOT NULL,
`end_date` DATE NOT NULL,
`total_days` INT NOT NULL,
`rate_applied` DOUBLE,
`tax_rate` DOUBLE NOT NULL,
`order_status` VARCHAR(30),
`notes` TEXT
);

INSERT INTO `rental_orders` (`employee_id`, `customer_id`, `car_id`, `car_condition`, `tank_level`, `kilometrage_start`,
`kilometrage_end`, `total_kilometrage`, `start_date`, `end_date`, `total_days`, `tax_rate`)
VALUES
(1, 11, 111, 'good', 50.2, 180000, 181000, 1000, '2021-05-15', '2021-05-21', 6, 10),
(2, 12, 112, 'good', 40.2, 180000, 181000, 1000, '2021-05-15', '2021-05-21', 6, 10),
(3, 13, 113, 'good', 60.2, 180000, 181000, 1000, '2021-05-15', '2021-05-21', 6, 10);