CREATE TABLE userstore (id bigserial PRIMARY KEY, name varchar(64), login varchar(64), email varchar(128), created bigint);
INSERT INTO userstore (name, login, email, created) VALUES ('Иван', 'ivan', 'ivan@test.ru', 1573993374125), ('Василий', 'vasya','vasya@test.com', 1555312517746);