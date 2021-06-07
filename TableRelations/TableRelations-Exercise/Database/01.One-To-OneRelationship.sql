CREATE TABLE `people` (
`person_id` INT,
`first_name` VARCHAR(20),
`salary` DECIMAL (10, 2),
`passport_id` INT UNIQUE
);

CREATE TABLE `passports` (
`passport_id` INT PRIMARY KEY AUTO_INCREMENT,
`passport_number` VARCHAR(30) UNIQUE
);

INSERT INTO `people` 
VALUES
(1,'Roberto', 43300, 102),
(2,'Tom', 56100, 103),
(3,'Yana', 60200, 101);

ALTER TABLE `passports` AUTO_INCREMENT = 101;

INSERT INTO `passports` (`passport_number`)
VALUES 
('N34FG21B'),
('K65LO4R7'),
('ZE657QP2');

ALTER TABLE `people`
MODIFY `person_id` INT PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE `people`
ADD CONSTRAINT fk_people_passports
FOREIGN KEY (`passport_id`)
REFERENCES `passports`(`passport_id`);