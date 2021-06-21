CREATE TABLE `people` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(200) NOT NULL,
`picture` BLOB,
`height` FLOAT(5, 2),
`weight` FLOAT(6, 2),
`gender` CHAR(1) NOT NULL,
`birthdate` DATE NOT NULL,
`biography` TEXT
);

INSERT INTO  `people` VALUES
(1, 'Kevin', NULL, 170, 90, 'M', '1992-05-11', 'Nice guy'),
(2, 'BlaBla', NULL, 175, 90.2, 'M', '1993-05-11', 'Nice guy'),
(3, 'Porki', NULL, 180, 101, 'M', '1994-05-11', 'Nice guy'),
(4, 'Alex', NULL, 181, 102, 'M', '1994-05-11', 'Nice guy'),
(5, 'Alena', NULL, 160, 50, 'F', '1994-05-11', 'Nice girl');