-- Tworzenie tabeli
CREATE TABLE services
(
    id           SERIAL PRIMARY KEY,
    service_date TIMESTAMP,
    user_name    VARCHAR(100),
    service_name VARCHAR(100),
    price        NUMERIC,
    status       VARCHAR(50),
    created_date TIMESTAMP
);

-- Wstawianie danych
INSERT INTO services (service_date, user_name, service_name, price, status, created_date)
VALUES ('2024-06-01 16:30:00', 'Jan Kowalski', 'Wymiana oleju', 200, 'Zakończone', '2024-05-25 15:45:21'),
       ('2024-06-02 10:00:00', 'Anna Nowak', 'Przegląd techniczny', 150, 'W trakcie', '2024-05-26 09:30:00'),
       ('2024-06-03 12:00:00', 'Piotr Wiśniewski', 'Naprawa hamulców', 300, 'Zakończone', '2024-05-27 11:45:00'),
       ('2024-06-04 14:00:00', 'Katarzyna Wójcik', 'Wymiana opon', 100, 'Oczekujące', '2024-05-28 13:15:00'),
       ('2024-06-05 08:30:00', 'Michał Lewandowski', 'Naprawa silnika', 500, 'W trakcie', '2024-05-29 08:00:00');

-- Tworzenie tabeli
CREATE TABLE documents
(
    id     SERIAL PRIMARY KEY,
    number VARCHAR(50)  NOT NULL,
    "user" VARCHAR(255) NOT NULL,
    type   VARCHAR(10)  NOT NULL,
    date   DATE         NOT NULL
);

-- Wstawienie przykładowych wpisów
INSERT INTO documents (id, number, "user", type, date)
VALUES (1, '12345', 'Jan Kowalski', 'WZ', '2024-06-01'),
       (2, '23456', 'Anna Nowak', 'WW', '2024-06-02'),
       (3, '34567', 'Piotr Wiśniewski', 'ZL', '2024-06-03'),
       (4, '45678', 'Katarzyna Wójcik', 'ZM', '2024-06-04'),
       (5, '56789', 'Michał Lewandowski', 'FV', '2024-06-05');

-- Tworzenie tabeli
CREATE TABLE inventory
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    type  VARCHAR(50)  NOT NULL,
    stock INTEGER      NOT NULL,
    price INTEGER      NOT NULL
);

-- Wstawienie przykładowych wpisów
INSERT INTO inventory (id, name, type, stock, price)
VALUES (1, 'Śruba M4', 'Śruba', 100, 5),
       (2, 'Nakładka M4', 'Nakładka', 150, 3),
       (3, 'Podkładka M4', 'Podkładka', 200, 2),
       (4, 'Śruba M6', 'Śruba', 80, 7),
       (5, 'Nakładka M6', 'Nakładka', 120, 4);

-- Tworzenie tabeli
CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    role          VARCHAR(50)  NOT NULL,
    register_date DATE         NOT NULL,
    email         VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL
);

-- Wstawienie przykładowego wpisu
INSERT INTO users (name, role, register_date, email, password)
VALUES
    ('Karol', 'admin', '2024-06-05', 'karol@example.com', 'password1'),
    ('Anna', 'client', '2024-06-06', 'anna@example.com', 'password2'),
    ('Jan', 'user', '2024-06-07', 'jan@example.com', 'password3'),
    ('Piotr', 'client', '2024-06-08', 'piotr@example.com', 'password4'),
    ('admin', 'admin', '2024-06-08', 'admin', 'admin');

