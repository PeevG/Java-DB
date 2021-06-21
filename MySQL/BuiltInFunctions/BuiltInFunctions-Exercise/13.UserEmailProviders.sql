SELECT `user_name`, SUBSTRING(`email`, LOCATE('@', `email`) + 1) AS 'Email provider'
FROM `users`
ORDER BY `Email provider`, `user_name`;