CREATE FUNCTION `ufn_get_salary_level`(salary_param DECIMAL(15, 2)) RETURNS varchar(30) CHARSET utf8
    DETERMINISTIC
BEGIN

RETURN (
CASE 
	WHEN salary_param < 30000 THEN 'Low'
	WHEN salary_param BETWEEN 30000 AND 50000 THEN 'Average'
    WHEN salary_param > 50000 THEN 'High'
END
);
END