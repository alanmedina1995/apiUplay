DROP TABLE IF EXISTS users;

create table users
(
    id       INTEGER auto_increment
        primary key,
    username CHARACTER VARYING(255) not null,
    password CHARACTER VARYING(255) not null,
    name     CHARACTER VARYING(255) not null,
    lastname CHARACTER VARYING(255) not null,
    admin    BOOLEAN                not null
);

INSERT INTO users (username, password, name, lastname, admin)
VALUES ('alanmedina1995@gmail.com.ar', 'admin1234', 'Alan', 'Medina', true);
INSERT INTO users (username, password, name, lastname, admin)
VALUES ('zapatajulian79@gmail.com', 'admin1234', 'Julian', 'Zapata', true);
INSERT INTO users (username, password, name, lastname, admin)
VALUES ('magiaunicornio42@gmail.com', 'user1234', 'Magia', 'Unicornio', false);
INSERT INTO users (username, password, name, lastname, admin)
VALUES ('dragónvolador87@hotmail.com', 'user1234', 'Dragón', 'Volador', false);
