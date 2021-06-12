CREATE PROCEDURE `usp_raise_salaries`(dep_name VARCHAR(50))
BEGIN

UPDATE employees AS e
JOIN departments AS d
ON e.department_id = d.department_id
SET salary = salary * 1.05
WHERE (d.`name` = dep_name);

-- SELECT first_name, salary
-- FROM employees AS e
-- JOIN departments AS d
-- ON e.department_id = d.department_id
-- WHERE d.`name` = dep_name
-- ORDER BY first_name, salary
-- LIMIT 3;

END