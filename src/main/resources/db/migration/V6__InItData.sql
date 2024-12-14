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

INSERT INTO events (category_id, city_id, image_url, title, description, event_date, total_seats, sold_seats, available_seats)
VALUES
(1, 1, 'https://via.placeholder.com/150', 'Jakarta Jazz Festival', 'An amazing night of jazz performances in the heart of Jakarta.', '2024-12-20T19:00:00Z', 500, 100, 400),
(1, 2, 'https://via.placeholder.com/150', 'Surabaya Rock Night', 'Experience a thrilling rock music event in Surabaya.', '2024-12-22T19:00:00Z', 300, 50, 250),
(2, 3, 'https://via.placeholder.com/150', 'Bandung Art Showcase', 'A celebration of local art and creativity in Bandung.', '2024-12-25T10:00:00Z', 200, 75, 125),
(2, 4, 'https://via.placeholder.com/150', 'Medan Art Festival', 'Discover amazing art and crafts in Medan.', '2024-12-26T11:00:00Z', 150, 30, 120),
(3, 5, 'https://via.placeholder.com/150', 'Makassar Food Expo', 'Explore culinary delights at Makassar food festival.', '2024-12-27T12:00:00Z', 400, 150, 250),
(3, 6, 'https://via.placeholder.com/150', 'Jogja Culinary Carnival', 'Enjoy the best of Jogja food scene at this carnival.', '2024-12-28T13:00:00Z', 350, 120, 230),
(4, 7, 'https://via.placeholder.com/150', 'Bali Marathon', 'Join the most scenic marathon in Bali.', '2024-12-29T06:00:00Z', 1000, 600, 400),
(4, 8, 'https://via.placeholder.com/150', 'Semarang Football Match', 'Cheer on your favorite teams at Semarang premier football match.', '2024-12-30T18:00:00Z', 800, 400, 400);