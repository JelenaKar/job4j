INSERT INTO users (id, email, "name", password) VALUES
                (1, 'ivan@test.ru', 'Иван Иванов', '12345'),
                (2, 'marfa@ro.ru','Марфа Васильевна', '54321');

INSERT INTO item (id, description, user_id, created, done) VALUES
                (1, 'Купить авиабилеты', 1, 1576930357251, true),
                (2, 'Сделать отчёт', 2, 1596878312839, false);