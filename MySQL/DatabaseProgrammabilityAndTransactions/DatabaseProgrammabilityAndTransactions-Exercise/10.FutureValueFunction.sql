CREATE FUNCTION `ufn_calculate_future_value`(sum DECIMAL (15,4), R DOUBLE, T INT) RETURNS decimal(10,4)
    DETERMINISTIC
BEGIN

RETURN (sum * ( POW(1 + R, T)));
END