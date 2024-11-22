CREATE TABLE referrals (
    referral_id BIGSERIAL CONSTRAINT referrals_pk PRIMARY KEY,
    referral_code VARCHAR(20) NOT NULL CONSTRAINT referrals_code_unique UNIQUE,
    owner_id BIGINT NOT NULL CONSTRAINT referrals_owner_fk REFERENCES users(user_id) ON DELETE CASCADE,
    used_by BIGINT CONSTRAINT referrals_used_by_fk REFERENCES users(user_id),
    points_generated NUMERIC(10, 2) DEFAULT 0 CHECK (points_generated >= 0),
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL,
    used_at TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX referrals_code_uindex ON referrals (referral_code);
CREATE UNIQUE INDEX referrals_owner_used_uindex ON referrals (owner_id, used_by);