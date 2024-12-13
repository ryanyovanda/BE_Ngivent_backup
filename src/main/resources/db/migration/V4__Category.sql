CREATE TABLE categories (
    category_id BIGSERIAL CONSTRAINT categories_pk PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    created_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    updated_at timestamp with time zone default CURRENT_TIMESTAMP not null,
    deleted_at timestamp with time zone
);

CREATE UNIQUE INDEX categories_category_id_uindex ON categories (category_id);