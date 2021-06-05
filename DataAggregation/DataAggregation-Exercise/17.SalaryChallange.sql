SELECT E.`first_name`,
E.`last_name`, 
E.`department_id`
FROM `employees` AS E
WHERE E.`salary` >
(
	SELECT AVG(E2.`salary`)
    FROM `employees` AS E2
    WHERE E2.`department_id` = E.`department_id`
)
ORDER BY E.`department_id`, E.`employee_id`
LIMIT 10;