CREATE TABLE reviews (
    review_id BIGSERIAL CONSTRAINT reviews_pk PRIMARY KEY,
    user_id BIGINT NOT NULL CONSTRAINT reviews_user_fk REFERENCES users(user_id) ON DELETE CASCADE,
    event_id BIGINT NOT NULL CONSTRAINT reviews_event_fk REFERENCES events(event_id) ON DELETE CASCADE,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    feedback TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX reviews_review_id_uindex ON reviews (review_id);
CREATE UNIQUE INDEX reviews_user_event_uindex ON reviews (user_id, event_id);