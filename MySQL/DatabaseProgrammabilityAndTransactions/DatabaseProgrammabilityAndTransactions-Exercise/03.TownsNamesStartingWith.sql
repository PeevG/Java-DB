CREATE DEFINER=`root`@`localhost` PROCEDURE `usp_get_towns_starting_with`(Str_param VARCHAR(20))
BEGIN

SELECT `name`
FROM towns
WHERE `name` LIKE concat(Str_param, '%')
ORDER BY `name`;

END