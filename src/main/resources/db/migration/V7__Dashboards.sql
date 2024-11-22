CREATE VIEW dashboard_stats AS
SELECT
    u.user_id AS organizer_id,
    COUNT(e.event_id) AS total_events,
    SUM(CASE WHEN t.transaction_id IS NOT NULL THEN 1 ELSE 0 END) AS total_attendees,
    SUM(t.total_price) AS total_revenue
FROM
    users u
LEFT JOIN events e ON e.created_by = u.user_id
LEFT JOIN transactions t ON t.event_id = e.event_id
WHERE
    u.role = 'organizer'
GROUP BY
    u.user_id;