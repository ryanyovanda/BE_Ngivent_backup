CREATE TABLE transactions (
    transaction_id BIGSERIAL CONSTRAINT transactions_pk PRIMARY KEY,
    user_id BIGINT NOT NULL CONSTRAINT transactions_user_fk REFERENCES users(user_id),
    event_id BIGINT NOT NULL CONSTRAINT transactions_event_fk REFERENCES events(event_id),
    promotion_id BIGINT CONSTRAINT transactions_promotion_fk REFERENCES promotions(promotion_id),
    total_price NUMERIC(12, 2) NOT NULL CHECK (total_price >= 0),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX transactions_transaction_id_uindex ON transactions (transaction_id);