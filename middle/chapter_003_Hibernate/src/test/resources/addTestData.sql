CREATE TABLE candidates (id bigserial PRIMARY KEY,
                         name varchar(127),
                         experience int DEFAULT 0,
                         salary int);

INSERT INTO candidates (name, experience, salary) VALUES
                        ('Иванов Иван', 5, 200000),
                        ('Петров Петр', 1, 50000),
                        ('Сидорова Марфа', 0, 30000);