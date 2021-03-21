CREATE TABLE IF NOT EXISTS orders (
    id serial,
    name VARCHAR(64),
    description VARCHAR(256),
    created timestamp,
    PRIMARY KEY (id)
);