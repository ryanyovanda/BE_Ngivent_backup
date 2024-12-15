INSERT INTO categories (name, icon_url)
VALUES
    ('Music', 'https://fontawesome.com/icons/music?f=classic&s=solid'),
    ('Arts', 'https://fontawesome.com/icons/masks-theater?f=classic&s=solid'),
    ('Food', 'https://fontawesome.com/icons/utensils?f=classic&s=solid'),
    ('Sports', 'https://fontawesome.com/icons/heart-pulse?f=classic&s=solid');

INSERT INTO cities (city_id, name)
VALUES
(1, 'Jakarta'),
(2, 'Surabaya'),
(3, 'Bandung'),
(4, 'Medan'),
(5, 'Makassar'),
(6, 'Yogyakarta'),
(7, 'Denpasar'),
(8, 'Semarang');

INSERT INTO events (category_id, city_id, image_url, title, description, event_date)
VALUES
(1, 1, 'https://via.placeholder.com/150', 'Jakarta Jazz Festival', 'An amazing night of jazz performances in the heart of Jakarta.', '2024-12-20T19:00:00Z'),
(1, 2, 'https://via.placeholder.com/150', 'Surabaya Rock Night', 'Experience a thrilling rock music event in Surabaya.', '2024-12-22T19:00:00Z'),
(2, 3, 'https://via.placeholder.com/150', 'Bandung Art Showcase', 'A celebration of local art and creativity in Bandung.', '2024-12-25T10:00:00Z'),
(2, 4, 'https://via.placeholder.com/150', 'Medan Art Festival', 'Discover amazing art and crafts in Medan.', '2024-12-26T11:00:00Z'),
(3, 5, 'https://via.placeholder.com/150', 'Makassar Food Expo', 'Explore culinary delights at Makassar food festival.', '2024-12-27T12:00:00Z'),
(3, 6, 'https://via.placeholder.com/150', 'Jogja Culinary Carnival', 'Enjoy the best of Jogja food scene at this carnival.', '2024-12-28T13:00:00Z'),
(4, 7, 'https://via.placeholder.com/150', 'Bali Marathon', 'Join the most scenic marathon in Bali.', '2024-12-29T06:00:00Z'),
(4, 8, 'https://via.placeholder.com/150', 'Semarang Football Match', 'Cheer on your favorite teams at Semarang premier football match.', '2024-12-30T18:00:00Z');

-- Inserting tickets for Event ID 1 (Regular with free ticket)
INSERT INTO tickets (event_id, type, is_free, price, total_ticket, sold_ticket, available_ticket)
VALUES
(1, 'REGULAR', false, 15.0, 30, 20, 10),
(1, 'VIP', true, 0.0, 20, 10, 10);

-- Inserting tickets for Event ID 2 (Regular)
INSERT INTO tickets (event_id, type, is_free, price, total_ticket, sold_ticket, available_ticket)
VALUES
(2, 'REGULAR', false, 10.0, 100, 100, 0);

-- Inserting tickets for Event ID 3 (VIP)
INSERT INTO tickets (event_id, type, is_free, price, total_ticket, sold_ticket, available_ticket)
VALUES
(3, 'VIP', false, 20.0, 50, 50, 0);

-- Inserting tickets for Event ID 4 (Regular + VIP)
INSERT INTO tickets (event_id, type, is_free, price, total_ticket, sold_ticket, available_ticket)
VALUES
(4, 'REGULAR', false, 10.0, 50, 25, 25),
(4, 'VIP', false, 30.0, 50, 40, 10);

-- Inserting tickets for Event ID 5 (Regular, VIP, and none)
INSERT INTO tickets (event_id, type, is_free, price, total_ticket, sold_ticket, available_ticket)
VALUES
(5, 'REGULAR', false, 10.0, 80, 40, 40),
(5, 'VIP', false, 30.0, 80, 0, 80);

-- Inserting tickets for Event ID 6 (No tickets available)
INSERT INTO tickets (event_id, type, is_free, price, total_ticket, sold_ticket, available_ticket)
VALUES
(6, 'VIP', true, 0.0, 20, 5, 15);

-- Inserting tickets for Event ID 7 (Regular)
INSERT INTO tickets (event_id, type, is_free, price, total_ticket, sold_ticket, available_ticket)
VALUES
(7, 'REGULAR', false, 10.0, 120, 60, 60);

-- Inserting tickets for Event ID 8 (VIP)
INSERT INTO tickets (event_id, type, is_free, price, total_ticket, sold_ticket, available_ticket)
VALUES
(8, 'VIP', false, 25.0, 100, 91, 0);

INSERT INTO discount_events (event_id, discount_percentage, title, description, max_usage, expired_date)
VALUES
    (1, 20, 'Early Bird Discount', '20% off for early registrants', 10, '2024-12-31 23:59:59'),
    (1, 15, 'Student Discount', '15% off for students with valid ID', 0, '2024-12-31 23:59:59');

INSERT INTO reviews (event_id, rating, feedback)
VALUES
    (1, 5, 'Amazing event! Everything was well-organized.'),
    (1, 4, 'Great experience, but the seating arrangement could be better.');

-- Insert dummy reviews for event ID 2
INSERT INTO reviews (event_id, rating, feedback)
VALUES
    (2, 5, 'Loved the atmosphere and the performances.'),
    (2, 3, 'The event was decent but started late.');