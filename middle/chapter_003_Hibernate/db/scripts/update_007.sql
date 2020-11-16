INSERT INTO auto (make_id, model_id, manufactured, body_id, doors, engine_id, driveunit_id, transmission_id, modification, leftwheel, mileage, isbroken, color, owner_number) VALUES
    (13, 78, 1987, 2, 5, 1, 3, 1, '2.2 quattro MT (165 л.с.)', true, 200000, false, 'Серебряный', 2),
    (102, 934, 2013, 1, 4, 1, 2, 2, '1.8 AT (150 л.с.)', true, 50000, true, 'Чёрный', 1);
INSERT INTO seller (name, address, email, phone, regdate, password) VALUES
    ('Иванов Иван', 'Ростов-на-Дону, Будённовский пр-т, 129', 'ivanov@test.ru', '+79051324890', 1573556056781, 'password'),
    ('Петров Пётр', 'Пенза, Ладожская ул., 110 ', 'petrov@test.ru', '+79124305671', 1432301802142, 'password');
INSERT INTO ad (auto_id, price, seller_id, created, issold) VALUES
    (1, 100000, 2, 1531901642623, true),
    (2, 500000, 1, 1602774658841, false);