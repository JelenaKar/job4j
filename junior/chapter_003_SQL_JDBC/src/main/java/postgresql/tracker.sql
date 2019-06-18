CREATE TABLE rules (id serial PRIMARY KEY, permission varchar(64));
CREATE TABLE roles (id serial PRIMARY KEY, "name" varchar(64));
CREATE TABLE category (id serial PRIMARY KEY, "name" varchar(256));
CREATE TABLE state (id serial PRIMARY KEY, "name" varchar(256));
CREATE TABLE rule2role (id serial PRIMARY KEY, rule_id integer REFERENCES rules, role_id integer REFERENCES roles);
CREATE TABLE "user" (id serial PRIMARY KEY, login varchar(64), "password" varchar(64), role_id integer REFERENCES roles);

CREATE TABLE item (
	id serial PRIMARY KEY, 
	title varchar(512), 
	"content" text, 
	category_id integer REFERENCES category,
	state_id integer REFERENCES state,
	user_id integer REFERENCES "user"
);

CREATE TABLE comments (
	id serial PRIMARY KEY, 
	"comment" text, 
	item_id integer REFERENCES item,
	user_id integer REFERENCES "user"
);

CREATE TABLE attaches (
	id serial PRIMARY KEY, 
	link varchar(2048), 
	item_id integer REFERENCES item
);

INSERT INTO rules (permission) VALUES
	('CREATE_USER'),
	('UPDATE_USER'),
	('DELETE_USER'),
	('ADD_ITEM'),
	('EDIT_ANY_ITEM'),
	('DELETE_ANY_ITEM'),
	('EDIT_OWN_ITEM'),
	('DELETE_OWN_ITEM');
	
INSERT INTO roles ("name") VALUES
	('administrator'),
	('moderator'),
	('user');
	
INSERT INTO rule2role (rule_id, role_id) VALUES
	(1, 1),	(2, 1),	(3, 1),
	(4, 1),	(4, 2),	(4, 3),
	(5, 1),	(5, 2),
	(6, 1), (6, 2),
	(7, 3), (8, 3);

INSERT INTO "user" (login, "password", role_id) VALUES
	('ivanov', '123', 1),
	('petrov', '456', 2),
	('sidorov', '678', 3),
	('kotov', '102', 3),
	('timofeev', '643', 3);

INSERT INTO category ("name") VALUES
	('IT-технологии'),
	('Материально-техническое обеспечение'),
	('Транспорт'),
	('Прочее');

INSERT INTO state ("name") VALUES
	('Обрабатывается'),
	('На рассмотрении'),
	('В работе'),
	('Исполнена'),
	('Отклонена');

INSERT INTO item (title, "content", category_id, state_id, user_id) VALUES
	('Предоставление доступов', 'Прошу предоставить доступы новому сотруднику Юридического управления в соответствии с приложениями.', 1, 4, 3),
	('Закупка канцелярского материала', 'Прошу включить наше управление в общий заказ на закупку канцелярских материалов в соответствии с прилоежнием', 3, 5, 4),
	('Закупка канцелярского материала', 'Прошу включить наше управление в общий заказ на закупку канцелярских материалов в соответствии с прилоежнием', 2, 3, 4);

INSERT INTO attaches (link, item_id) VALUES
	('/home/uu/downloads/scan1.jpg', 1),
	('/home/unimto/downloads/scan0002.jpg', 2),
	('/home/unimto/downloads/scan0002.jpg', 3);

INSERT INTO "comments" ("comment", user_id, item_id) VALUES
	('Выбрана неверная категория', 2, 2),
	('Взято в работу. Ожидаемый срок доставки в течение двух недель', 5, 3);