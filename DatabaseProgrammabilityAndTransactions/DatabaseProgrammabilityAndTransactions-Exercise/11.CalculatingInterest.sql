CREATE PROCEDURE usp_calculate_future_value_for_account (acc_id INT, interest DOUBLE(15, 4))
BEGIN

SELECT a.id, ah.first_name, ah.last_name, a.balance AS curr_balance,
ufn_calculate_future_value(a.balance, interest, 5) AS balance_in_5_years
FROM accounts AS a
JOIN account_holders AS ah
ON a.account_holder_id = ah.id
WHERE a.id = acc_id;

END;