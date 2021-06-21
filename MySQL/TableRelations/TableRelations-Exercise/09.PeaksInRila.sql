SELECT `mountain_range`,
`peak_name`, `elevation` AS `peak_elevation`
FROM `mountains` AS m
JOIN `peaks` AS p
ON m.`id` = p.`mountain_id`
WHERE m.`id` = 17
ORDER BY `peak_elevation` DESC;