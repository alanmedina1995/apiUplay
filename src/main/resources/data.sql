CREATE TABLE IF NOT EXISTS users
(
    id           INT auto_increment primary key,
    user_name    VARCHAR(20) not null,
    email        VARCHAR(50) not null,
    password     VARCHAR(50) not null,
    name         VARCHAR(50) not null,
    last_name    VARCHAR(50) not null,
    phone_number VARCHAR(50) not null,
    utn_coin     INT         not null
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


INSERT INTO questions (question_description)
VALUES ('¿Cuál es el nombre de tu primera mascota?'),
       ('¿Cuál es tu comida favorita?'),
       ('¿En qué ciudad naciste?'),
       ('¿Cuál es el nombre de tu abuelo materno?'),
       ('¿Cuál es tu canción favorita?'),
       ('¿Cuál es el nombre de tu mejor amigo de la infancia?'),
       ('¿Dónde fue tu primer trabajo?'),
       ('¿Cuál es tu película favorita?'),
       ('¿Cuál es el nombre de tu primer maestro?'),
       ('¿Cuál es tu equipo deportivo favorito?'),
       ('¿Cuál es el nombre de tu libro favorito?'),
       ('¿Cuál es tu destino de vacaciones soñado?'),
       ('¿Cuál es tu número de suerte?'),
       ('¿Cuál es tu color favorito?'),
       ('¿Cuál es tu plato típico preferido?');

INSERT INTO users (user_name, email, password, name, last_name, phone_number, utn_coin)
VALUES ('alanmedina', 'alanmedina1995@gmail.com', 'admin1234', 'Alan', 'Medina', '2235879632', 5000),
       ('julianzapata', 'zapatajulian79@gmail.com', 'admin1234', 'Julian', 'Zapata', '2235123487', 5000);

INSERT INTO users (user_name, email, password, name, last_name, phone_number, utn_coin)
VALUES ('luciamartinez', 'luciamartinez@gmail.com', 'admin1234', 'Lucia', 'Martinez', '2235123456', 50),
       ('marcosrodriguez', 'marcosrodriguez@hotmail.com', 'admin1234', 'Marcos', 'Rodriguez', '2235987612', 50),
       ('anagarcia', 'ana_garcia@yahoo.com', 'admin1234', 'Ana', 'Garcia', '2235768901', 50),
       ('juancarloslopez', 'juancarloslopez@gmail.com', 'admin1234', 'Juan Carlos', 'Lopez', '2235874365', 50),
       ('carolinaramirez', 'carolinaramirez@hotmail.com', 'admin1234', 'Carolina', 'Ramirez', '2235123789', 50),
       ('pedromartinez', 'pedromartinez@gmail.com', 'admin1234', 'Pedro', 'Martinez', '2235987432', 50),
       ('lauragonzalez', 'lauragonzalez@yahoo.com', 'admin1234', 'Laura', 'Gonzalez', '2235769087', 50),
       ('oscarrodriguez', 'oscarrodriguez@gmail.com', 'admin1234', 'Oscar', 'Rodriguez', '2235876543', 50),
       ('valerialopez', 'valerialopez@hotmail.com', 'admin1234', 'Valeria', 'Lopez', '2235123901', 50),
       ('estebanperez', 'estebanperez@gmail.com', 'admin1234', 'Esteban', 'Perez', '2235987654', 50),
       ('marianagonzalez', 'marianagonzalez@yahoo.com', 'admin1234', 'Mariana', 'Gonzalez', '2235769123', 50),
       ('raulrodriguez', 'raulrodriguez@gmail.com', 'admin1234', 'Raul', 'Rodriguez', '2235876789', 50),
       ('danielaramos', 'danielaramos@hotmail.com', 'admin1234', 'Daniela', 'Ramos', '2235123098', 50),
       ('sergiohernandez', 'sergiohernandez@gmail.com', 'admin1234', 'Sergio', 'Hernandez', '2235987123', 50),
       ('sofiatorres', 'sofiatorres@yahoo.com', 'admin1234', 'Sofia', 'Torres', '2235769456', 50),
       ('martinromero', 'martinromero@gmail.com', 'admin1234', 'Martin', 'Romero', '2235876234', 50),
       ('andreamartinez', 'andreamartinez@hotmail.com', 'admin1234', 'Andrea', 'Martinez', '2235123765', 50),
       ('javierrodriguez', 'javierrodriguez@gmail.com', 'admin1234', 'Javier', 'Rodriguez', '2235987345', 50);

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

