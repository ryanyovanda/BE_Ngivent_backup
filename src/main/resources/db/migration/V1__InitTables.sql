CREATE TABLE users (
    user_id BIGSERIAL CONSTRAINT users_pk PRIMARY KEY,
    email VARCHAR(50) NOT NULL CONSTRAINT users_email_unique UNIQUE,
    username VARCHAR(50) NOT NULL CONSTRAINT users_username_unique UNIQUE,
    password TEXT NOT NULL,
    referral_code VARCHAR(20) CONSTRAINT users_referral_code_unique UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE UNIQUE INDEX users_email_uindex ON users (email);
CREATE UNIQUE INDEX users_username_uindex ON users (username);
CREATE UNIQUE INDEX users_referral_code_uindex ON users (referral_code);