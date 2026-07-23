# Write your MySQL query statement below
SELECT name 
FROM CUSTOMER
WHERE REFEREE_ID!=2 OR
REFEREE_ID IS NULL;