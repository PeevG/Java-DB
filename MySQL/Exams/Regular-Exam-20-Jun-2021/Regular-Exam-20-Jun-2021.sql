-- Regular Exam 20.06.2021

-- 01. Table Design

CREATE TABLE addresses (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100) NOT NULL
);

CREATE TABLE clients (
id INT PRIMARY KEY AUTO_INCREMENT,
full_name VARCHAR(50) NOT NULL,
phone_number VARCHAR(20) NOT NULL
);

CREATE TABLE categories (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(10) NOT NULL
);

CREATE TABLE cars (
id INT PRIMARY KEY AUTO_INCREMENT,
make VARCHAR(20) NOT NULL,
model VARCHAR(20),
year INT NOT NULL DEFAULT 0,
mileage INT DEFAULT 0,
`condition` CHAR(1) NOT NULL,
category_id INT NOT NULL,
CONSTRAINT fk_cars_categories
FOREIGN KEY (category_id)
REFERENCES categories(id)
);

CREATE TABLE courses (
id INT PRIMARY KEY AUTO_INCREMENT,
from_address_id INT NOT NULL,
`start` DATETIME NOT NULL,
car_id INT NOT NULL,
client_id INT NOT NULL,
bill DECIMAL(10, 2) DEFAULT 0,
CONSTRAINT fk_courses_addressses
FOREIGN KEY (from_address_id)
REFERENCES addresses(id),
CONSTRAINT fk_courses_clients
FOREIGN KEY (client_id)
REFERENCES clients(id),
CONSTRAINT fk_courses_cars
FOREIGN KEY (car_id)
REFERENCES cars(id)
);

CREATE TABLE drivers (
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL,
age INT NOT NULL,
rating FLOAT DEFAULT 5.5
);

CREATE TABLE cars_drivers (
car_id INT,
driver_id INT,
CONSTRAINT fk_cars_drivers_cars
FOREIGN KEY (car_id)
REFERENCES cars(id),
CONSTRAINT fk_cars_drivers_drivers
FOREIGN KEY (driver_id)
REFERENCES drivers(id),
CONSTRAINT pk_composite_key
PRIMARY KEY (car_id, driver_id)	
);

-- 02.INSERT

INSERT INTO clients (full_name, phone_number)

SELECT CONCAT(first_name, ' ', last_name)
,CONCAT('(088) 9999', id * 2)
FROM drivers 
WHERE id BETWEEN 10 AND 20;

-- 03.UPDATE

UPDATE cars
SET `condition` = 'C'
WHERE mileage >= 800000 
OR mileage IS NULL 
AND year <= 2010 
AND make NOT IN('Mercedes-Benz');

-- 04. DELETE

DELETE FROM clients
WHERE char_length(full_name) > 3
AND id NOT IN (SELECT client_id FROM courses);

-- 05. CARS

SELECT make, model, `condition`
FROM cars
ORDER BY cars.	id;

-- 06. Drivers and Cars

SELECT d.first_name, d.last_name,
c.make, c.model, c.mileage
FROM drivers AS d
JOIN cars_drivers AS cd
ON d.id = cd.driver_id
JOIN cars AS c
ON cd.car_id = c.id
WHERE c.mileage IS NOT NULL
ORDER BY c.mileage DESC, d.first_name ASC;

-- 07.Number of courses

SELECT c.id, c.make, c.mileage, COUNT(co.car_id) AS count_of_courses, ROUND(AVG(co.bill), 2 ) AS avg_bill
FROM cars AS c
LEFT JOIN courses AS co
ON c.id = co.car_id
GROUP BY c.id
HAVING count_of_courses != 2
ORDER BY count_of_courses DESC, c.id;

-- 08. Regular clients

SELECT cl.full_name, COUNT(ca.id) AS count_of_cars, SUM(co.bill) AS total_sum
FROM clients AS cl
JOIN courses AS co
ON cl.id = co.client_id
JOIN cars AS ca
ON co.car_id = ca.id
GROUP BY cl.id
HAVING cl.full_name LIKE ('_a%')
AND count_of_cars > 1
ORDER BY cl.full_name;


-- 09. Full info for courses

SELECT a.name, (
	CASE
		 WHEN HOUR(co.start) BETWEEN 6 AND 20 THEN 'Day'
		 ELSE 'Night'
         END
		) AS day_time, 
co.bill, cl.full_name, ca.make, ca.model, cat.name
FROM addresses AS a
JOIN courses AS co
ON co.from_address_id = a.id
JOIN clients AS cl
ON co.client_id = cl.id
JOIN cars AS ca
ON co.car_id = ca.id
JOIN categories AS cat
ON ca.category_id = cat.id
GROUP BY co.id
ORDER BY co.id;

-- 10. Find all courses by client’s phone number

DELIMITER $$

CREATE FUNCTION `udf_courses_by_client`(phone_num VARCHAR (20)) RETURNS int
    DETERMINISTIC
BEGIN


RETURN (SELECT COUNT(cl.id) AS count
	FROM courses AS co
	JOIN clients AS cl
	ON co.client_id = cl.id
	WHERE (cl.phone_number = phone_num));
END $$

DELIMITER ;

-- 11. Full info for address

DELIMITER $$

CREATE PROCEDURE `udp_courses_by_address` (address_name VARCHAR (100))
BEGIN

SELECT a.name, cl.full_name,
(	CASE
		WHEN co.bill < 21 THEN 'Low'
        WHEN co.bill < 31 THEN 'Medium'
        ELSE 'High'
	END
) AS level_of_bill, 
ca.make, ca.`condition`, cat.name
FROM addresses AS a
JOIN courses AS co
ON co.from_address_id = a.id
JOIN clients AS cl
ON co.client_id = cl.id
JOIN cars AS ca
ON co.car_id = ca.id
JOIN categories AS cat
ON ca.category_id = cat.id
WHERE a.name = address_name
ORDER BY ca.make, cl.full_name;

END $$

DELIMITER ;








