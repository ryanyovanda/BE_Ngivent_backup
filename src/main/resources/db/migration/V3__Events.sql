CREATE TABLE events (
    event_id BIGSERIAL CONSTRAINT events_pk PRIMARY KEY,

    organizer_id BIGINT NOT NULL, -- Foreign key for organizer
        CONSTRAINT fk_organizer FOREIGN KEY (organizer_id) REFERENCES users(user_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,

    category_id BIGINT NOT NULL, -- Foreign key for category
        CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(category_id)
            ON DELETE SET NULL
            ON UPDATE CASCADE,

    -- Event details
    image_url VARCHAR(500) NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    location VARCHAR(100) NOT NULL,
    event_date TIMESTAMP WITH TIME ZONE NOT NULL,

    -- Pricing and ticket details
    is_free BOOLEAN NOT NULL DEFAULT false,
    price NUMERIC(12, 2) DEFAULT 0 CHECK (price >= 0),

    start_date TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT chk_date_range CHECK (end_date > start_date),

    allocated_seats INT NOT NULL CHECK (allocated_seats > 0), -- Total allocated seats must be positive
    available_seats INT NOT NULL CHECK (available_seats >= 0), -- Available seats cannot be negative
    sold_seats INT NOT NULL DEFAULT 0 CHECK (sold_seats >= 0),

    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX events_event_id_uindex ON events (event_id);