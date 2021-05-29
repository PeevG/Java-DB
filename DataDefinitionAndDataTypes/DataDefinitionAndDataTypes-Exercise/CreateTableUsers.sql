CREATE TABLE `users` (
`id` INT UNIQUE PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(30) NOT NULL UNIQUE,
`password` VARCHAR(26) NOT NULL UNIQUE,
`profile_picture` BLOB,
`last_login_time` TIMESTAMP,
`is_deleted` BOOLEAN 
);

INSERT INTO `users` VALUES 
(1, 'Peter', '123', NULL, NULL, false),
(2, 'Ivan', '124', NULL, NULL, true),
(3, 'John', '125', NULL, NULL, false),
(4, 'Luter', '126', NULL, NULL, true),
(5, 'Rich', '127', NULL, NULL, false);