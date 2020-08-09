ALTER TABLE userstore ADD password varchar(256);
UPDATE userstore SET password = '123456' WHERE login = 'ivan';
UPDATE userstore SET password = '654321' WHERE login = 'vasya';