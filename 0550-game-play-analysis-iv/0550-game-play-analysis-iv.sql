SELECT
    ROUND(
        COUNT(*) /
        (SELECT COUNT(DISTINCT player_id) FROM Activity),
        2
    ) AS fraction
FROM Activity a1
JOIN (
    SELECT
        player_id,
        MIN(event_date) AS ed
    FROM Activity
    GROUP BY player_id
) a2
ON a1.player_id = a2.player_id
AND a1.event_date = DATE_ADD(a2.ed, INTERVAL 1 DAY);