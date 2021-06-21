CREATE  PROCEDURE `usp_deposit_money`(account_id INT, money_amount DECIMAL(17,4) )
BEGIN
	START TRANSACTION;
		IF(SELECT COUNT(*) 
			FROM accounts 
			WHERE account_id = 0)
		OR (money_amount <= 0)
	THEN ROLLBACK;
		ELSE
			UPDATE accounts 
            SET balance = balance + money_amount
            WHERE id = account_id;
    END IF;
    
END