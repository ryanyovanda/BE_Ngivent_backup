CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL, -- 'customer', 'organizer'
    referral_code VARCHAR(50) UNIQUE,
    referred_by VARCHAR(50),
    points_balance INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE events (
    id SERIAL PRIMARY KEY,
    organizer_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    location VARCHAR(255),
    date DATE NOT NULL,
    time TIME NOT NULL,
    price DECIMAL(10, 2),
    is_free BOOLEAN DEFAULT FALSE,
    available_seats INT NOT NULL,
    ticket_type VARCHAR(50), -- 'standard', 'vip', 'premium'
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (organizer_id) REFERENCES users (id)
);

CREATE TABLE tickets (
    id SERIAL PRIMARY KEY,
    event_id INT NOT NULL,
    user_id INT,
    ticket_type VARCHAR(50), -- 'standard', 'vip', 'premium'
    price DECIMAL(10, 2),
    purchased_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES events (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    event_id INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    discount DECIMAL(10, 2) DEFAULT 0.0,
    payment_status VARCHAR(50) DEFAULT 'pending', -- 'pending', 'completed', 'failed'
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (event_id) REFERENCES events (id)
);

CREATE TABLE reviews (
    id SERIAL PRIMARY KEY,
    event_id INT NOT NULL,
    user_id INT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES events (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE coupons (
    id SERIAL PRIMARY KEY,
    code VARCHAR(50) UNIQUE NOT NULL,
    user_id INT NOT NULL,
    discount_percentage DECIMAL(5, 2),
    max_uses INT DEFAULT 1,
    used_count INT DEFAULT 0,
    expiration_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE referral_points (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    points INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expiration_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);