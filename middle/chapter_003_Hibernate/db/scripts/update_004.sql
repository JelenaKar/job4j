INSERT INTO engine (id) VALUES (DEFAULT), (DEFAULT), (DEFAULT);

INSERT INTO car (engine_id) VALUES (1), (1), (3), (2), (3);

INSERT INTO driver (name) VALUES ('Иванов'), ('Петров'), ('Сидоров');

INSERT INTO  history_owner (driver_id, car_id) VALUES (1, 2), (1, 5), (1, 4),
                                                          (2, 1), (2, 3),
                                                          (3, 4), (3, 1);

