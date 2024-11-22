CREATE TABLE promotions (
    promotion_id BIGSERIAL CONSTRAINT promotions_pk PRIMARY KEY,
    event_id BIGINT NOT NULL CONSTRAINT promotions_event_fk REFERENCES events(event_id) ON DELETE CASCADE,
    discount_percentage NUMERIC(5, 2) NOT NULL CHECK (discount_percentage BETWEEN 0 AND 100),
    max_uses INT CHECK (max_uses > 0),
    referral_code VARCHAR(20) CONSTRAINT promotions_referral_fk REFERENCES referrals(referral_code),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX promotions_promotion_id_uindex ON promotions (promotion_id);