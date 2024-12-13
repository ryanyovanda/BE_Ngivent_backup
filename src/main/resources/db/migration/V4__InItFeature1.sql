CREATE TABLE categories (
    category_id BIGSERIAL CONSTRAINT categories_pk PRIMARY KEY,
    icon_url VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX categories_id_uindex ON categories (category_id ASC);
COMMENT ON COLUMN categories.icon_url IS 'URL for the category icon';
COMMENT ON COLUMN categories.name IS 'Name of the category';
COMMENT ON COLUMN categories.created_at IS 'Timestamp when the category was created';
COMMENT ON COLUMN categories.updated_at IS 'Timestamp when the category was last updated';
COMMENT ON COLUMN categories.deleted_at IS 'Timestamp when the category was marked as deleted';

CREATE TABLE cities (
    city_id BIGSERIAL CONSTRAINT cities_pk PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX cities_id_uindex ON cities (city_id ASC);
COMMENT ON COLUMN cities.name IS 'Name of the Cities';
COMMENT ON COLUMN cities.created_at IS 'Timestamp when the cities was created';
COMMENT ON COLUMN cities.updated_at IS 'Timestamp when the cities was last updated';
COMMENT ON COLUMN cities.deleted_at IS 'Timestamp when the cities was marked as deleted';

CREATE TABLE discount_tickets (
    discount_tickets_id BIGSERIAL CONSTRAINT discount_tickets_pk PRIMARY KEY,
    discount_percentage NUMERIC(5, 2) NOT NULL CHECK (discount_percentage >= 0 AND discount_percentage <= 100),
    title VARCHAR(50) NOT NULL,
    description TEXT,
    discount_code VARCHAR(50) NOT NULL,
    max_usage INT NOT NULL,
    start_date TIMESTAMP WITH TIME ZONE NOT NULL,
    expired_date TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX discount_tickets_id_uindex ON discount_tickets (discount_ticket_id ASC);
COMMENT ON COLUMN discount_tickets.discount_percentage IS 'Discount percentage (0-100)';
COMMENT ON COLUMN discount_tickets.title IS 'Title of the discount ticket';
COMMENT ON COLUMN discount_tickets.description IS 'Description of the discount ticket';
COMMENT ON COLUMN discount_tickets.discount_code IS 'Unique code for applying the discount';
COMMENT ON COLUMN discount_tickets.max_usage IS 'Maximum usage count for the discount';
COMMENT ON COLUMN discount_tickets.start_date IS 'Start date and time of the discount';
COMMENT ON COLUMN discount_tickets.expired_date IS 'Expiration date and time of the discount';
COMMENT ON COLUMN discount_tickets.created_at IS 'Timestamp when the discount ticket was created';
COMMENT ON COLUMN discount_tickets.updated_at IS 'Timestamp when the discount ticket was last updated';
COMMENT ON COLUMN discount_tickets.deleted_at IS 'Timestamp when the discount ticket was marked as deleted';

CREATE TABLE events (
    event_id BIGSERIAL CONSTRAINT events_pk PRIMARY KEY,
    category_id BIGINT NOT NULL CONSTRAINT events_category_fk REFERENCES categories (category_id) ON DELETE CASCADE,
    city_id BIGINT NOT NULL CONSTRAINT events_city_fk REFERENCES city (city_id) ON DELETE CASCADE,
    image_url VARCHAR(100) NOT NULL,
    title VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    event_date TIMESTAMP WITH TIME ZONE NOT NULL,
    start_date TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date TIMESTAMP WITH TIME ZONE NOT NULL,
    total_seats INT,
    sold_seats INT,
    available_seats INT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE,
);

CREATE UNIQUE INDEX events_id_uindex ON events (event_id DESC);
COMMENT ON COLUMN events.event_id IS 'Primary key for events';
COMMENT ON COLUMN events.category_id IS 'Foreign key referencing categories';
COMMENT ON COLUMN events.city_id IS 'Foreign key referencing cities';
COMMENT ON COLUMN events.image_url IS 'URL for the event image';
COMMENT ON COLUMN events.title IS 'Title of the event';
COMMENT ON COLUMN events.description IS 'Description of the event';
COMMENT ON COLUMN events.event_date IS 'Date and time of the event';
COMMENT ON COLUMN events.start_date IS 'Start date and time for booking';
COMMENT ON COLUMN events.end_date IS 'End date and time for booking';
COMMENT ON COLUMN events.total_seats IS 'Total number of seats available';
COMMENT ON COLUMN events.sold_seats IS 'Number of seats sold';
COMMENT ON COLUMN events.available_seats IS 'Number of seats still available';
COMMENT ON COLUMN events.created_at IS 'Timestamp when the event was created';
COMMENT ON COLUMN events.updated_at IS 'Timestamp when the event was last updated';
COMMENT ON COLUMN events.deleted_at IS 'Timestamp when the event was marked as deleted';

CREATE TABLE reviews (
    review_id BIGSERIAL CONSTRAINT reviews_pk PRIMARY KEY,
    event_id BIGINT NOT NULL CONSTRAINT reviews_event_fk REFERENCES events (event_id) ON DELETE CASCADE,
    rating SMALLINT NOT NULL DEFAULT 5 CHECK (rating >= 1 AND rating <= 5),
    feedback TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE,
);

CREATE UNIQUE INDEX review_id_uindex ON reviews (review_id DESC);
COMMENT ON COLUMN reviews.review_id IS 'Primary key for reviews';
COMMENT ON COLUMN reviews.event_id IS 'Foreign key referencing events';
COMMENT ON COLUMN reviews.rating IS 'Rating given to the event, from 1 to 5';
COMMENT ON COLUMN reviews.feedback IS 'Feedback provided by the reviewer';
COMMENT ON COLUMN reviews.created_at IS 'Timestamp when the review was created';
COMMENT ON COLUMN reviews.updated_at IS 'Timestamp when the review was last updated';
COMMENT ON COLUMN reviews.deleted_at IS 'Timestamp when the review was marked as deleted';

CREATE TABLE tickets (
    ticket_id BIGSERIAL CONSTRAINT tickets_pk PRIMARY KEY,
    event_id BIGINT NOT NULL CONSTRAINT tickets_event_fk REFERENCES events (event_id) ON DELETE CASCADE,
    type VARCHAR(50) NOT NULL,
    is_free BOOLEAN DEFAULT false NOT NULL,
    price NUMERIC(12, 2) DEFAULT 0 CHECK (price >= 0),
    total_ticket INT NOT NULL CHECK (total_ticket > 0),
    sold_ticket INT NOT NULL DEFAULT 0 CHECK (sold_ticket >= 0),
    available_ticket INT NOT NULL DEFAULT 0 CHECK (available_ticket >= 0),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE,
);

CREATE UNIQUE INDEX tickets_id_uindex ON tickets (ticket_id ASC);
COMMENT ON COLUMN tickets.ticket_id IS 'Primary key for tickets';
COMMENT ON COLUMN tickets.event_id IS 'Foreign key referencing event';
COMMENT ON COLUMN tickets.type IS 'Type of ticket';
COMMENT ON COLUMN tickets.is_free IS 'Ticket is free or no';
COMMENT ON COLUMN tickets.price IS 'Ticket price';
COMMENT ON COLUMN tickets.total_ticket IS 'Total ticket allocated';
COMMENT ON COLUMN tickets.sold_ticket IS 'Sold ticket count';
COMMENT ON COLUMN tickets.available_ticket IS 'Available ticket count';
COMMENT ON COLUMN tickets.created_at IS 'Timestamp when the review was created';
COMMENT ON COLUMN tickets.updated_at IS 'Timestamp when the review was last updated';
COMMENT ON COLUMN tickets.deleted_at IS 'Timestamp when the review was marked as deleted';

CREATE TABLE transactions (
    transaction_id BIGSERIAL CONSTRAINT transactions_pk PRIMARY KEY,
    event_id BIGINT NOT NULL CONSTRAINT transactions_event_fk REFERENCES events (event_id) ON DELETE CASCADE,
    ticket_id BIGINT NOT NULL CONSTRAINT transactions_ticket_fk REFERENCES ticket (ticket_id) ON DELETE CASCADE,
    invoice_number VARCHAR(255) NOT NULL UNIQUE,
    ticket_quantity INT NOT NULL,
    discount_price NUMERIC(12, 2),
    final_price NUMERIC(12, 2),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITH TIME ZONE,
);

CREATE UNIQUE INDEX transactions_id_uindex ON transactions (transaction_id DESC);
COMMENT ON COLUMN transactions.transaction_id IS 'Primary key for transactions';
COMMENT ON COLUMN transactions.event_id IS 'Primary key for event';
COMMENT ON COLUMN transactions.ticket_id IS 'Foreign key referencing ticket';
COMMENT ON COLUMN transactions.invoice_number IS 'Invoice Number';
COMMENT ON COLUMN transactions.ticket_quantity IS 'Ticket quantity bought';
COMMENT ON COLUMN transactions.discount_price IS 'Discount Price calculated';
COMMENT ON COLUMN transactions.final_price IS 'Total Price calculated';
COMMENT ON COLUMN transactions.created_at IS 'Timestamp when the review was created';
COMMENT ON COLUMN transactions.updated_at IS 'Timestamp when the review was last updated';
COMMENT ON COLUMN transactions.deleted_at IS 'Timestamp when the review was marked as deleted';