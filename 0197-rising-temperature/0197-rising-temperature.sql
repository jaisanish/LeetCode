# Write your MySQL query statement below
Select w.id 
From Weather w
Join Weather e on w.recordDate=DATE_ADD(e.recordDate,Interval 1 DAY)
Where w.temperature>e.temperature;