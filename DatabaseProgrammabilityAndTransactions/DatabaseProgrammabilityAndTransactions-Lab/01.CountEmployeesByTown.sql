CREATE FUNCTION `ufn_count_employees_by_town`(town_name_param VARCHAR(35)) RETURNS int
    DETERMINISTIC
BEGIN

RETURN 
	(SELECT COUNT(employee_id)
	FROM employees AS e
    JOIN addresses AS a
    ON a.address_id = e.address_id
    JOIN towns as t
    ON a.town_id = t.town_id
	WHERE t.`name` = town_name_param);
END