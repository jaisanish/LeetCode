# Write your MySQL query statement below
Select Date_format(trans_date,'%Y-%m') as month,
        country,count(*) as trans_count,
        Sum(Case when state='approved' then 1 else 0 end)as approved_count,
        Sum(amount) as trans_total_amount,
        Sum(Case when state='approved' then amount else 0 end)as approved_total_amount
from transactions
group by Date_format(trans_date,'%Y-%m') , country;