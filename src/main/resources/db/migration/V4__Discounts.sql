CREATE TABLE discounts (
    discount_id BIGSERIAL CONSTRAINT discount_pk PRIMARY KEY,
    event_id BIGINT NOT NULL CONSTRAINT discount_event_fk REFERENCES events(event_id) ON DELETE CASCADE,
    discount_percentage NUMERIC(5, 2) NOT NULL CHECK (discount_percentage >= 0 AND discount_percentage <= 100),  -- Percentage between 0 and 100
    description TEXT,
    start_date TIMESTAMP WITH TIME ZONE NOT NULL,
    end_date TIMESTAMP WITH TIME ZONE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX discount_discount_id_uindex ON discount (discount_id);
CREATE INDEX discount_event_id_idx ON discount (event_id);