CREATE TABLE deleted_employees (
`employee_id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(30),
`last_name` VARCHAR(30),
`middle_name` VARCHAR(30),
`job_title` VARCHAR(30),
`department_id` INT,
`salary` DECIMAL
);

CREATE TRIGGER `soft_uni`.`employees_AFTER_DELETE` AFTER DELETE ON `employees` FOR EACH ROW
BEGIN

INSERT INTO deleted_employees
(employee_id, first_name, last_name, middle_name, job_title, department_id, salary)
VALUES 
(OLD.employee_id, OLD. first_name, OLD. last_name, OLD.middle_name, OLD.job_title, OLD.department_id, OLD.salary);

END