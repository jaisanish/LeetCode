# Write your MySQL query statement below
Select unique_id,name
From Employees e
Left Join EmployeeUNI ei on e.id=ei.id;