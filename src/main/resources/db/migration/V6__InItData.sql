INSERT INTO categories (name)
VALUES ('Musik');

INSERT INTO events (
    category_id, image_url, title, description, location, event_date,
    is_free, price, start_date, end_date, allocated_seats, sold_seats, created_at, updated_at
) VALUES (
    1, 'https://raw.githubusercontent.com/Welk-dy/Companyprofile/main/public/arrow_icon.png', 'Musik Event', 'musik description',
    'jakarta Location', '2024-12-10 15:00:00+00', false, 10000.00,
    '2024-12-8 15:00:00+00', '2024-12-9 15:00:00+00', 100, 5,
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
);