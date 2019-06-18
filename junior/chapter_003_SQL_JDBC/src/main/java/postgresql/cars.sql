CREATE TABLE transmission (
	id serial PRIMARY KEY,
	name varchar(128)
);
CREATE TABLE garbage (
	id serial PRIMARY KEY,
	name varchar(128)
);

CREATE TABLE engine (
	id serial PRIMARY KEY,
	name varchar(128)
);

CREATE TABLE cars (
	id serial PRIMARY KEY,
	name varchar(128),
	trans_id integer REFERENCES transmission,
	garbage_id integer REFERENCES garbage,
	engine_id integer REFERENCES engine
);

INSERT INTO transmission (name) VALUES
	('ручная'),
	('автоматическая'),
	('робот');

INSERT INTO garbage (name) VALUES
	('хэтчбэк'),
	('седан'),
	('внедорожник'),
	('универсал');

INSERT INTO engine (name) VALUES
	('бензиновый'),
	('дизельный'),
	('электрический'),
	('гибрид');

INSERT INTO cars (name, trans_id, garbage_id, engine_id) VALUES
	('Chevrolet Cruze', 1, 1, 1),
	('Audi A6', 3, 2, 2),
	('Honda Shuttle', 3, 4, 4);

-- Список всех авто с использованными деталями	
SELECT c.name, t.name, g.name, e.name FROM cars as c INNER JOIN transmission as t ON c.trans_id = t.id
INNER JOIN engine AS e ON c.engine_id = e.id INNER JOIN garbage AS g ON c.garbage_id = g.id;

-- Список неиспользуемых коробок передач
SELECT t.name FROM cars as c RIGHT OUTER JOIN transmission as t ON c.trans_id = t.id WHERE c.id IS NULL;

-- Список неиспользуемых кузовов
SELECT g.name FROM cars as c RIGHT OUTER JOIN garbage as g ON c.garbage_id = g.id WHERE c.id IS NULL;

-- Список неиспользуемых двигателей
SELECT e.name FROM cars as c RIGHT OUTER JOIN engine as e ON c.engine_id = e.id WHERE c.id IS NULL;