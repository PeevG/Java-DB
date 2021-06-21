SELECT t.`town_id`, 
t.`name` AS 'town_name',
a.address_text AS 'address_text'
FROM towns AS t
JOIN addresses AS a
ON a.town_id = t.town_id
WHERE t.`name` IN ('San Francisco', 'Carnation', 'Sofia')
ORDER BY town_id, address_id;