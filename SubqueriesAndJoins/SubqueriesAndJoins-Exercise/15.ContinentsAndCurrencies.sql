SELECT continent_code, currency_code, COUNT(currency_code) AS 'currency_usage'
FROM countries AS c
GROUP BY continent_code, currency_code
HAVING currency_usage = (
	SELECT COUNT(currency_code) AS 'coun'
	FROM countries AS c1
	WHERE c1.continent_code = c.continent_code
	GROUP BY  currency_code
	ORDER BY coun DESC 
	LIMIT 1
) AND currency_usage > 1
ORDER BY continent_code, currency_code;