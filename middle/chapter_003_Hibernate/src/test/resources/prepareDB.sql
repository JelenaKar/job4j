CREATE SEQUENCE engine_id_seq START WITH 1;
CREATE TABLE engine (id integer DEFAULT nextval('engine_id_seq') PRIMARY KEY);

CREATE SEQUENCE car_id_seq START WITH 1;
CREATE TABLE car (id integer DEFAULT nextval('car_id_seq') PRIMARY KEY,
                  engine_id int NOT NULL references engine(id) ON DELETE CASCADE);

CREATE SEQUENCE driver_id_seq START WITH 1;
CREATE TABLE driver (id integer DEFAULT nextval('driver_id_seq') PRIMARY KEY,
                    name varchar(128) NOT NULL);

CREATE SEQUENCE history_owner_id_seq START WITH 1;
CREATE TABLE history_owner (
                    id integer DEFAULT nextval('history_owner_id_seq') PRIMARY KEY,
                    driver_id int NOT NULL references driver(id) ON DELETE CASCADE,
                    car_id int NOT NULL references car(id) ON DELETE CASCADE
);
INSERT INTO engine (id) VALUES (DEFAULT), (DEFAULT), (DEFAULT);

INSERT INTO car (engine_id) VALUES (1), (1), (3), (2), (3);

INSERT INTO driver (name) VALUES ('Иванов'), ('Петров'), ('Сидоров');

INSERT INTO  history_owner (driver_id, car_id) VALUES (1, 2), (1, 5), (1, 4),
                                                      (2, 1), (2, 3),
                                                      (3, 4), (3, 1);