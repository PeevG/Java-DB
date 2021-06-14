CREATE PROCEDURE `usp_get_employees_salary_above`(above_or_equal DECIMAL(15,4))
BEGIN

SELECT first_name, last_name
FROM employees
WHERE salary >= above_or_equal
ORDER BY first_name, last_name, employee_id;

END