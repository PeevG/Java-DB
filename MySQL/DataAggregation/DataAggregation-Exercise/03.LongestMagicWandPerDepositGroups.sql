SELECT `deposit_group`, MAX(`magic_wand_size`) 
AS `Longest_magic_wand`
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `Longest_magic_wand`, `deposit_group`;