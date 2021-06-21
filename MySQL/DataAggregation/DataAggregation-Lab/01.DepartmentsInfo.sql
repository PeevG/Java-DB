SELECT `department_id`, COUNT(`id`) AS `number of employees`
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id`, `number of employees`;