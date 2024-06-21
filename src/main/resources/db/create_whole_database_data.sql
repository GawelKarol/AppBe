CREATE TABLE mechanic_service
(
    id               BIGSERIAL PRIMARY KEY,
    service_name     VARCHAR(255)   NOT NULL,
    status           VARCHAR(255)   NOT NULL,
    service_cost     DECIMAL(19, 2) NOT NULL,
    appointment_date TIMESTAMP      NOT NULL,
    partner_name     VARCHAR(255)   NOT NULL,
    client_name      VARCHAR(255)   NOT NULL,
    used_parts       TEXT           NOT NULL,
    created_date     TIMESTAMP      NOT NULL
);

-- Tworzenie tabeli
CREATE TABLE documents
(
    id           SERIAL PRIMARY KEY,
    number       VARCHAR(50)  NOT NULL,
    name         VARCHAR(255) NOT NULL,
    partner_name VARCHAR(255) NOT NULL,
    type         VARCHAR(10)  NOT NULL,
    date         DATE         NOT NULL
);

-- Tworzenie tabeli
CREATE TABLE inventory
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    stock INTEGER      NOT NULL,
    price INTEGER      NOT NULL
);

-- Wstawienie przykładowych wpisów
INSERT INTO inventory (name, stock, price)
VALUES ('Olej', 10, 7);
INSERT INTO inventory (name, stock, price)
VALUES ('Filtr', 20, 10);
INSERT INTO inventory (name, stock, price)
VALUES ('Opona', 30, 50);
INSERT INTO inventory (name, stock, price)
VALUES ('Klocki hamulcowe', 40, 200);
INSERT INTO inventory (name, stock, price)
VALUES ('Świece zapłonowe', 50, 50);
INSERT INTO inventory (name, stock, price)
VALUES ('Filtr powietrza', 60, 50);
INSERT INTO inventory (name, stock, price)
VALUES ('Akumulator', 70, 200);
INSERT INTO inventory (name, stock, price)
VALUES ('Płyn do skrzyni biegów', 80, 80);


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
VALUES ('Karol', 'admin', '2024-06-05', 'karol@example.com', 'password1'),
       ('Anna', 'client', '2024-06-06', 'anna@example.com', 'password2'),
       ('Wylezalek', 'serwis', '2024-06-07', 'wylezalek@example.com', 'wylezalek'),
       ('AutoParts', 'serwis', '2024-06-07', 'AutoParts@example.com', 'AutoParts'),
       ('RondoMechanics', 'serwis', '2024-06-07', 'RondoMechanics@example.com', 'RondoMechanics'),
       ('Serwis', 'serwis', '2024-06-07', 'serwis', 'serwis'),
       ('Piotr', 'client', '2024-06-08', 'piotr@example.com', 'piotr'),
       ('Client', 'client', '2024-06-08', 'client', 'client'),
       ('admin', 'admin', '2024-06-08', 'admin', 'admin');

