CREATE FUNCTION `ufn_is_word_comprised`(set_of_letters varchar(50), word varchar(50)) RETURNS bit(1)
    DETERMINISTIC
BEGIN

RETURN (SELECT word REGEXP(CONCAT('^[', set_of_letters, ']+$')));
END