CREATE TABLE rules (id serial PRIMARY KEY, permission varchar(64));
CREATE TABLE roles (id serial PRIMARY KEY, "name" varchar(64));
CREATE TABLE category (id serial PRIMARY KEY, "name" varchar(256));
CREATE TABLE state (id serial PRIMARY KEY, "name" varchar(256));
CREATE TABLE rule2role (id serial PRIMARY KEY, rule_id integer REFERENCES rules ON UPDATE CASCADE ON DELETE CASCADE, role_id integer REFERENCES roles ON UPDATE CASCADE ON DELETE CASCADE);
CREATE TABLE "user" (id serial PRIMARY KEY, login varchar(64), "password" varchar(64), role_id integer REFERENCES roles ON UPDATE CASCADE ON DELETE SET NULL);
CREATE TABLE item (id serial PRIMARY KEY, name varchar(512), description text, category_id integer REFERENCES category ON UPDATE CASCADE ON DELETE SET NULL, state_id integer REFERENCES state ON UPDATE CASCADE ON DELETE SET NULL, user_id integer REFERENCES "user" ON UPDATE CASCADE ON DELETE SET NULL, created bigint, uniqhash character varying(128));
CREATE TABLE comments (id serial PRIMARY KEY, "comment" text, item_id integer REFERENCES item ON UPDATE CASCADE ON DELETE CASCADE, user_id integer REFERENCES "user" ON UPDATE CASCADE ON DELETE SET NULL);
CREATE TABLE attaches (id serial PRIMARY KEY, link varchar(2048), item_id integer REFERENCES item ON UPDATE CASCADE ON DELETE CASCADE);
INSERT INTO rules (permission) VALUES ('CREATE_USER'), ('UPDATE_USER'), ('DELETE_USER'), ('ADD_ITEM'), ('EDIT_ANY_ITEM'), ('DELETE_ANY_ITEM'), ('EDIT_OWN_ITEM'), ('DELETE_OWN_ITEM');
INSERT INTO roles ("name") VALUES ('administrator'), ('moderator'), ('user');
INSERT INTO rule2role (rule_id, role_id) VALUES (1, 1), (2, 1), (3, 1), (4, 1), (4, 2), (4, 3), (5, 1), (5, 2), (6, 1), (6, 2), (7, 3), (8, 3);
INSERT INTO category ("name") VALUES ('IT-технологии'), ('Материально-техническое обеспечение'), ('Транспорт'), ('Прочее');
INSERT INTO state ("name") VALUES ('Обрабатывается'), ('На рассмотрении'), ('В работе'), ('Исполнена'), ('Отклонена');
INSERT INTO "user" (login, "password", role_id) VALUES ('default user', 'default', 3);