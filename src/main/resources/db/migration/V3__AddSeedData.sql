INSERT INTO users (email, username, password, referral_code, created_at, updated_at)
VALUES
    ('john.doe@example.com', 'john_doe', 'hashed_password_123', 'REF123ABC', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('jane.smith@example.com', 'jane_smith', 'hashed_password_456', 'REF456DEF', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('alice.jones@example.com', 'alice_jones', 'hashed_password_789', 'REF789GHI', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO
  roles (name, created_at, updated_at)
VALUES
  ('ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (
    'MODERATOR',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
  );
