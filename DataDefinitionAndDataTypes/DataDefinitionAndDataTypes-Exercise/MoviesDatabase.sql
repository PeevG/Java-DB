-- Exer 11
CREATE DATABASE `Movies`;
USE `movies`;

CREATE TABLE `directors` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`director_name` VARCHAR(50) NOT NULL,
`notes` TEXT
);

INSERT INTO `directors` VALUES 
(1, 'Martin Dimitrov', 'Hello i`m Martin.'),
(2, 'Ivan Petrov', 'Hello i`m Ivan.'),
(3, 'Peter Smith', 'Hello i`m Peter.'),
(4, 'John Pit', NULL),
(5, 'Luter King', 'Hello i`m Luter.');

CREATE TABLE `genres` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`genre_name` VARCHAR(50) NOT NULL,
`notes` TEXT
);

INSERT INTO `genres` VALUES 
(1, 'comedy', 'The best movie ever!'),
(2, 'action', 'Almost good'),
(3, 'action', NULL),
(4, 'trailer', 'The best movie ever2!'),
(5, 'drama', NULL);

CREATE TABLE `categories` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`category_name` VARCHAR(30) NOT NULL,
`notes` TEXT
);

INSERT INTO `categories` VALUES
(1, 'biograpfy', 'Film for Stiven King.'),
(2, 'biography', 'Film for Aerton Sena.'),
(3, 'biograpfy', 'Film for Cristiano Ronaldo.'),
(4, 'biograpfy', NULL),
(5, 'history', 'Film about World second war.');

CREATE TABLE `movies` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(65) NOT NULL,
`director_id` INT NOT NULL,
`copyright_year` DATE,
`length` TIME,
`genre_id` INT NOT NULL,
`category_id` INT NOT NULL,
`rating` INT,
`notes` TEXT
);

INSERT INTO `movies` (`title`, `director_id`, `genre_id`, `category_id`) 
VALUES 
('Rambo', 1, 11, 111),
('Rambo2', 2, 12, 112),
('Ramb3', 3, 13, 113),
('Rambo4', 4, 14, 114),
('Ramb5', 5, 15, 115);