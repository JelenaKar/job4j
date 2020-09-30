CREATE SEQUENCE engine_id_seq;
CREATE TABLE engine (id integer DEFAULT nextval('engine_id_seq') PRIMARY KEY);
ALTER SEQUENCE engine_id_seq OWNED BY engine.id;

CREATE SEQUENCE car_id_seq;
CREATE TABLE car (id integer DEFAULT nextval('car_id_seq') PRIMARY KEY,
                  engine_id int NOT NULL references engine(id) ON DELETE CASCADE);
ALTER SEQUENCE car_id_seq  OWNED BY car.id;

CREATE SEQUENCE driver_id_seq;
CREATE TABLE driver (id integer DEFAULT nextval('driver_id_seq') PRIMARY KEY,
                    name varchar(128) NOT NULL);
ALTER SEQUENCE driver_id_seq OWNED BY driver.id;

CREATE SEQUENCE history_owner_id_seq;
CREATE TABLE history_owner (
                    id integer DEFAULT nextval('history_owner_id_seq') PRIMARY KEY,
                    driver_id int NOT NULL references driver(id) ON DELETE CASCADE,
                    car_id int NOT NULL references car(id) ON DELETE CASCADE
);
ALTER SEQUENCE history_owner_id_seq OWNED BY history_owner.id;