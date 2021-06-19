-- Exam 17 Oct 2020

-- Table Design

CREATE TABLE towns(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE addresses (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL UNIQUE,
town_id INT NOT NULL,
CONSTRAINT fk_addresses_towns
FOREIGN KEY (town_id)
REFERENCES towns(id)
);

CREATE TABLE employees (
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(15) NOT NULL,
middle_name CHAR(1),
last_name VARCHAR(20) NOT NULL,
salary DECIMAL(19, 2) NOT NULL DEFAULT 0,
hire_date DATE NOT NULL,
manager_id INT,
store_id INT NOT NULL,
CONSTRAINT fk_id_manager_id
FOREIGN KEY (manager_id)
REFERENCES employees(id)
);

CREATE TABLE pictures (
id INT PRIMARY KEY AUTO_INCREMENT,
url VARCHAR(100) NOT NULL,
added_on DATETIME NOT NUll
);

CREATE TABLE categories (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE products (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(40) NOT NULL UNIQUE,
best_before DATE,
price DECIMAL (10, 2) NOT NULL,
description TEXT,
category_id INT NOT NULL,
picture_id INT NOT NULL,
CONSTRAINT fk_products_categories
FOREIGN KEY (category_id)
REFERENCES categories(id),
CONSTRAINT fk_products_pictures
FOREIGN KEY (picture_id)
REFERENCES pictures(id)
);

CREATE TABLE stores (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(20) NOT NULL UNIQUE,
rating FLOAT NOT NULL,
has_parking TINYINT(1) DEFAULT 0,
address_id INT NOT NULL,
CONSTRAINT fk_stores_addresses
FOREIGN KEY (address_id)
REFERENCES addresses(id)
);

ALTER TABLE employees
ADD CONSTRAINT fk_employees_stores
FOREIGN KEY (store_id)
REFERENCES stores(id);

CREATE TABLE products_stores (
product_id INT,
store_id INT,
CONSTRAINT fk_product_stores_products
FOREIGN KEY (product_id)
REFERENCES products(id),
CONSTRAINT fk_product_stores_stores
FOREIGN KEY (store_id)
REFERENCES stores(id),
CONSTRAINT pk_products_stores_composite
PRIMARY KEY (product_id, store_id)
);

-- 02.Insert

-- SELECT p.id, ps.product_id  FROM products AS p
-- LEFT JOIN products_stores AS ps
-- ON p.id = ps.product_id;

INSERT INTO products_stores (product_id, store_id)
VALUES
(9, 1),
(10, 1),
(13, 1),
(16, 1),
(18, 1);

-- 03. UPDATE

UPDATE employees e 
		JOIN stores AS s
        ON e.store_id = s.id
        SET manager_id = 3
WHERE YEAR(e.hire_date) > '2003' 
AND s.name NOT IN ('Cardguard', 'Veribet');

UPDATE employees e 
		JOIN stores AS s
        ON e.store_id = s.id
        SET salary = salary - 500
WHERE YEAR(e.hire_date) > '2003' 
AND s.name NOT IN ('Cardguard', 'Veribet');

-- 04. DELETE

DELETE FROM employees
WHERE manager_id IS NOT NULL
AND salary >= 6000;

-- 05. Employees

SELECT first_name, middle_name, last_name,
salary, hire_date
FROM employees
ORDER BY hire_date DESC;

-- 06. Products with old pictures

SELECT p.name, p.price, p.best_before,
(SELECT CONCAT(LEFT(p.`description`, 10), '...')) AS short_description,
url
FROM products AS p
JOIN pictures AS pic
ON p.picture_id = pic.id
WHERE char_length(p.description) > 100
AND 
YEAR(pic.added_on) < '2019'
AND p.price > 20
ORDER BY p.price DESC;

-- 07 Counts of products in stores

SELECT s.name, COUNT(ps.product_id) AS product_count,
ROUND(AVG(p.price), 2) AS `avg`
FROM stores AS s
LEFT JOIN products_stores AS ps
ON s.id = ps.store_id
LEFT JOIN products AS p
ON p.id = ps.product_id
GROUP BY s.id
ORDER BY product_count DESC, `avg` DESC, s.id ;

-- 08 Specific employee

SELECT CONCAT(e.first_name, ' ', e.last_name) AS Full_name,
s.name AS Store_name,
a.name AS address,
e.salary
FROM employees AS e
JOIN stores AS s
ON e.store_id = s.id
JOIN addresses AS a
ON s.address_id = a.id
WHERE e.salary < 4000
AND 
a.name LIKE ('%5%')
AND char_length(s.name) > 8 
AND RIGHT(e.last_name, 1) = 'n';

-- 09. Find all information of stores

SELECT `reverse`(s.name) AS reversed_name, 
(SELECT CONCAT( UPPER(t.name), '-', a.name)) AS full_address,
(SELECT COUNT(e.id)) AS count
FROM stores AS s
JOIN addresses AS a
ON s.address_id = a.id
JOIN employees AS e
ON e.store_id = s.id
JOIN towns AS t
on a.town_id = t.id
GROUP BY s.name
HAVING count >= 1
ORDER BY full_address;

-- 10.	Find full name of top paid employee by store name
DELIMITER $$

CREATE FUNCTION `udf_top_paid_employee_by_store`(store_name VARCHAR(50)) RETURNS varchar(100)
    DETERMINISTIC
BEGIN

RETURN (SELECT (CONCAT(e.first_name, ' ', e.middle_name,'. ', e.last_name, ' works in store for ', YEAR('2020-10-18') - Year(e.hire_date), ' years' )) AS Full_info
FROM employees AS e
JOIN stores AS s
ON e.store_id = s.id
WHERE s.name = store_name
ORDER BY e.salary DESC
LIMIT 1
);
END $$

DELIMITER ;

-- 011.Update product price by address

DELIMITER $$

CREATE PROCEDURE `udp_update_product_price`(address_name VARCHAR (50))
BEGIN
	
    DECLARE increase_amount INT;
    
	IF left(address_name, 1) = '0'
    THEN SET increase_amount = 100;
		ELSE SET increase_amount =200;
	END IF;
    
    UPDATE products AS p SET price = price + increase_amount
    WHERE p.id IN (
					SELECT ps.product_id FROM addresses AS a
					JOIN stores AS s
                    ON a.id =s.address_id
                    JOIN products_stores AS ps 
                    ON ps.store_id = s.id
                    WHERE a.name = address_name
    );

END $$

DELIMITER ;







