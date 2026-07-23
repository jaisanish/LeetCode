# Write your MySQL query statement below
Select m1.machine_id,
        Round(AVG(m2.timestamp-m1.timestamp),3)as processing_time
From activity m1
Join activity m2 on m1.machine_id=m2.machine_id
                and m1.process_id=m2.process_id
Where m1.activity_type='start' and m2.activity_type='end'
Group by m1.machine_id;