# Write your MySQL query statement below
Select v.customer_id,count(v.customer_id) as count_no_trans
From Visits v
Left Join Transactions t on v.visit_id=t.visit_id
Where t.transaction_id is NULL
Group by v.customer_id;