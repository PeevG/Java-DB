SELECT `category_id`, 
ROUND(AVG(`price`), 2) AS `Average price`,
ROUND(MIN(`price`), 2) AS `Cheapest product`, 
ROUND(MAX(`price`), 2) AS `Most Expensive product`
FROM `products`
GROUP BY `category_id`;