CREATE TABLE events (
    event_id BIGSERIAL PRIMARY KEY,

    -- Category relationship
    category_id BIGINT NOT NULL CONSTRAINT events_category_fk REFERENCES categories(category_id),

    -- Event details
    image_url VARCHAR(500) NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    location VARCHAR(100) NOT NULL,
    event_date TIMESTAMPTZ NOT NULL,

    -- Pricing and ticket details
    is_free BOOLEAN NOT NULL DEFAULT false,
    price NUMERIC(12, 2) DEFAULT 0 CHECK (price >= 0),

    start_date TIMESTAMPTZ NOT NULL,
    end_date TIMESTAMPTZ NOT NULL,
    CONSTRAINT chk_date_range CHECK (end_date > start_date),

    allocated_seats INT NOT NULL CHECK (allocated_seats > 0), -- Total allocated seats must be positive
    available_seats INT NOT NULL DEFAULT 0 CHECK (available_seats >= 0), -- Available seats cannot be negative
    sold_seats INT NOT NULL DEFAULT 0 CHECK (sold_seats >= 0),

    created_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    deleted_at timestamp with time zone
);

CREATE UNIQUE INDEX events_event_id_uindex ON events (event_id);