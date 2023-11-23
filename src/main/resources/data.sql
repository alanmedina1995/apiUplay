CREATE TABLE IF NOT EXISTS users
(
    id           INT auto_increment primary key,
    user_name    VARCHAR(20) not null,
    email        VARCHAR(50) not null,
    password     VARCHAR(50) not null,
    name         VARCHAR(50) not null,
    last_name    VARCHAR(50) not null,
    phone_number VARCHAR(50) not null
);

CREATE TABLE IF NOT EXISTS questions
(
    id                   INT auto_increment primary key,
    question_description VARCHAR(255) not null
);

CREATE TABLE IF NOT EXISTS question_Xuser
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    user_id     INT UNIQUE   NOT NULL,
    question_id INT          NOT NULL,
    answer      VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (question_id) REFERENCES questions (id)
);

CREATE TABLE IF NOT EXISTS coins
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    last_value  DOUBLE       NOT NULL,
    last_update TIMESTAMP
);

CREATE TABLE IF NOT EXISTS wallets
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    user_id         INT NOT NULL,
    utncoin_amount  DOUBLE,
    bitcoin_amount  DOUBLE,
    monero_amount   DOUBLE,
    ethereum_amount DOUBLE
);

INSERT INTO coins (description, last_value, last_update)
VALUES ('utncoin', 5000.0, NOW()),
       ('bitcoin', 60000.0, NOW()),
       ('monero', 250.0, NOW()),
       ('ethereum', 4000.0, NOW());

INSERT INTO questions (question_description)
VALUES ('What is the name of your first pet?'),
       ('What is your favorite food?'),
       ('In which city were you born?'),
       ('What is the name of your maternal grandfather?'),
       ('What is your favorite song?'),
       ('What is the name of your best childhood friend?'),
       ('Where was your first job?'),
       ('What is your favorite movie?'),
       ('What is the name of your first teacher?'),
       ('What is your favorite sports team?'),
       ('What is the name of your favorite book?'),
       ('What is your dream vacation destination?'),
       ('What is your lucky number?'),
       ('What is your favorite color?'),
       ('What is your favorite typical dish?');


INSERT INTO users (user_name, email, password, name, last_name, phone_number)
VALUES ('alanmedina', 'alanmedina1995@gmail.com', 'admin1234', 'Alan', 'Medina', '2235879632'),
       ('julianzapata', 'zapatajulian79@gmail.com', 'admin1234', 'Julian', 'Zapata', '2235123487');

INSERT INTO users (user_name, email, password, name, last_name, phone_number)
VALUES ('luciamartinez', 'luciamartinez@gmail.com', 'admin1234', 'Lucia', 'Martinez', '2235123456'),
       ('marcosrodriguez', 'marcosrodriguez@hotmail.com', 'admin1234', 'Marcos', 'Rodriguez', '2235987612'),
       ('anagarcia', 'ana_garcia@yahoo.com', 'admin1234', 'Ana', 'Garcia', '2235768901'),
       ('juancarloslopez', 'juancarloslopez@gmail.com', 'admin1234', 'Juan Carlos', 'Lopez', '2235874365'),
       ('carolinaramirez', 'carolinaramirez@hotmail.com', 'admin1234', 'Carolina', 'Ramirez', '2235123789'),
       ('pedromartinez', 'pedromartinez@gmail.com', 'admin1234', 'Pedro', 'Martinez', '2235987432'),
       ('lauragonzalez', 'lauragonzalez@yahoo.com', 'admin1234', 'Laura', 'Gonzalez', '2235769087'),
       ('oscarrodriguez', 'oscarrodriguez@gmail.com', 'admin1234', 'Oscar', 'Rodriguez', '2235876543'),
       ('valerialopez', 'valerialopez@hotmail.com', 'admin1234', 'Valeria', 'Lopez', '2235123901'),
       ('estebanperez', 'estebanperez@gmail.com', 'admin1234', 'Esteban', 'Perez', '2235987654'),
       ('marianagonzalez', 'marianagonzalez@yahoo.com', 'admin1234', 'Mariana', 'Gonzalez', '2235769123'),
       ('raulrodriguez', 'raulrodriguez@gmail.com', 'admin1234', 'Raul', 'Rodriguez', '2235876789'),
       ('danielaramos', 'danielaramos@hotmail.com', 'admin1234', 'Daniela', 'Ramos', '2235123098'),
       ('sergiohernandez', 'sergiohernandez@gmail.com', 'admin1234', 'Sergio', 'Hernandez', '2235987123'),
       ('sofiatorres', 'sofiatorres@yahoo.com', 'admin1234', 'Sofia', 'Torres', '2235769456'),
       ('martinromero', 'martinromero@gmail.com', 'admin1234', 'Martin', 'Romero', '2235876234'),
       ('andreamartinez', 'andreamartinez@hotmail.com', 'admin1234', 'Andrea', 'Martinez', '2235123765'),
       ('javierrodriguez', 'javierrodriguez@gmail.com', 'admin1234', 'Javier', 'Rodriguez', '2235987345');

INSERT INTO question_Xuser (user_id, question_id, answer)
VALUES (1, 1, 'Rex'),
       (2, 4, 'Carlos'),
       (3, 3, 'Mar del Plata'),
       (4, 5, 'Bohemian Rhapsody'),
       (5, 6, 'McDonalds'),
       (6, 2, 'Las Vegas'),
       (7, 12, 'París'),
       (8, 8, 'Sra. Carmen'),
       (9, 9, 'Boca Juniors'),
       (10, 7, 'McDonalds'),
       (11, 14, 'Verde'),
       (12, 13, '7'),
       (13, 11, 'Nueva York'),
       (14, 15, 'Milanesa con puré'),
       (15, 10, 'Río de Janeiro'),
       (16, 1, 'Luna'),
       (17, 3, 'Córdoba'),
       (18, 14, 'Azul'),
       (19, 5, 'Stairway to Heaven'),
       (20, 10, 'París');

INSERT INTO wallets (user_id, utncoin_amount, bitcoin_amount, monero_amount, ethereum_amount)
VALUES (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0),
       (1, 50, 0, 0, 0);
