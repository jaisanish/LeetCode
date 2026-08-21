# Write your MySQL query statement below
Select machine_id,Round( 
    SUM(CASE WHEN activity_type='start' then -timestamp else timestamp end)/(Count(machine_id)/2.0)
    ,3) as processing_time
From Activity 
Group by machine_id;