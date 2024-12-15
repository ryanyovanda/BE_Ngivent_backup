CREATE SEQUENCE categories_id_seq START 1;
CREATE TABLE categories (
    category_id BIGSERIAL CONSTRAINT categories_pk PRIMARY KEY,
    icon_url VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX categories_id_uindex ON categories (category_id ASC);

CREATE SEQUENCE cities_id_seq START 1;
CREATE TABLE cities (
    city_id BIGSERIAL CONSTRAINT cities_pk PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX cities_id_uindex ON cities (city_id ASC);

CREATE SEQUENCE events_id_seq START 25;

CREATE TABLE events (
    event_id BIGSERIAL CONSTRAINT events_pk PRIMARY KEY,
    category_id BIGINT NOT NULL CONSTRAINT events_category_fk REFERENCES categories (category_id) ON DELETE CASCADE,
    city_id BIGINT NOT NULL CONSTRAINT events_city_fk REFERENCES cities (city_id) ON DELETE CASCADE,
    image_url VARCHAR(100) NOT NULL,
    title VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    event_date TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX events_id_uindex ON events (event_id DESC);

CREATE SEQUENCE tickets_id_seq START 25;

CREATE TABLE tickets (
    ticket_id BIGSERIAL CONSTRAINT tickets_pk PRIMARY KEY,
    event_id BIGINT NOT NULL CONSTRAINT tickets_event_fk REFERENCES events (event_id) ON DELETE CASCADE,
    type VARCHAR(10) NOT NULL CHECK (type IN ('REGULAR', 'VIP')),
    is_free BOOLEAN DEFAULT false NOT NULL,
    price NUMERIC(12, 2) DEFAULT 0 CHECK (price >= 0),
    total_ticket INT NOT NULL CHECK (total_ticket > 0),
    sold_ticket INT NOT NULL DEFAULT 0 CHECK (sold_ticket >= 0),
    available_ticket INT NOT NULL DEFAULT 0 CHECK (available_ticket >= 0),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX tickets_id_uindex ON tickets (ticket_id ASC);

CREATE SEQUENCE discounte_id_seq START 25;
CREATE TABLE discount_events (
    discounte_id BIGSERIAL CONSTRAINT discounte_id_pk PRIMARY KEY,
    event_id BIGINT NOT NULL CONSTRAINT discounte_event_fk REFERENCES events (event_id) ON DELETE CASCADE,
    discount_percentage NUMERIC(5, 2) NOT NULL CHECK (discount_percentage >= 0 AND discount_percentage <= 100),
    title VARCHAR(50) NOT NULL,
    description TEXT,
    max_usage INT NOT NULL,
    expired_date TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX discounte_id_uindex ON discount_events (discounte_id ASC);

CREATE SEQUENCE reviews_id_seq START 25;
CREATE TABLE reviews (
    review_id BIGSERIAL CONSTRAINT reviews_pk PRIMARY KEY,
    event_id BIGINT NOT NULL CONSTRAINT reviews_event_fk REFERENCES events (event_id) ON DELETE CASCADE,
    rating SMALLINT NOT NULL DEFAULT 5 CHECK (rating >= 1 AND rating <= 5),
    feedback TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX review_id_uindex ON reviews (review_id DESC);

CREATE SEQUENCE transactions_id_seq START 25;
CREATE TABLE transactions (
    transaction_id BIGSERIAL CONSTRAINT transactions_pk PRIMARY KEY,
    event_id BIGINT NOT NULL CONSTRAINT transactions_event_fk REFERENCES events (event_id) ON DELETE CASCADE,
    ticket_id BIGINT NOT NULL CONSTRAINT transactions_ticket_fk REFERENCES tickets (ticket_id) ON DELETE CASCADE,
    discounte_id BIGINT NOT NULL CONSTRAINT transactions_discounte_fk REFERENCES discount_events (discounte_id) ON DELETE CASCADE,
    quantity INT CHECK (quantity >= 0),
    final_price NUMERIC(12, 2) NOT NULL CHECK (final_price >= 0),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX transactions_id_uindex ON transactions (transaction_id DESC);