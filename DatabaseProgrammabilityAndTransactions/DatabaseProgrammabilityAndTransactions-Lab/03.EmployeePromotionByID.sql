CREATE PROCEDURE `usp_raise_salary_by_id`(chosen_employee INT)
BEGIN

UPDATE employees
SET salary = salary * 1.05
WHERE employee_id = chosen_employee;

END