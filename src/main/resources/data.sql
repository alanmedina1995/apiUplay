CREATE TABLE IF NOT EXISTS users(
    id       INT auto_increment primary key,
    username VARCHAR(255) not null,
    password VARCHAR(255) not null,
    name     VARCHAR(255) not null,
    lastname VARCHAR(255) not null,
    admin    BOOLEAN      not null,
    utncoin  INT          not null
    );

INSERT INTO users (username, password, name, lastname, admin, utncoin)
VALUES ('alanmedina1995@gmail.com', 'admin1234', 'Alan', 'Medina', true, 5000);

INSERT INTO users (username, password, name, lastname, admin, utncoin)
VALUES ('zapatajulian79@gmail.com', 'admin1234', 'Julian', 'Zapata', true, 5000);

INSERT INTO users (username, password, name, lastname, admin, utncoin)
VALUES ('magiaunicornio42@gmail.com', 'user1234', 'Magia', 'Unicornio', false, 250);

INSERT INTO users (username, password, name, lastname, admin, utncoin)
VALUES ('dragónvolador87@hotmail.com', 'user1234', 'Dragón', 'Volador', false, 250);