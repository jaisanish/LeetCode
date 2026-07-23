# Write your MySQL query statement below
Select e1.name 
From employee e1 
Join employee e2 on e1.id=e2.managerId
Group by e2.managerId
Having count(e2.managerId)>=5;