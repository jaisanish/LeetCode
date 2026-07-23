# Write your MySQL query statement below
Select ei.unique_id,e.name
From Employees e
Left Join EmployeeUNI ei on e.id=ei.id;