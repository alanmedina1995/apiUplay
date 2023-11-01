DROP TABLE IF EXISTS USUARIOS;

create table USUARIOS
(
    ID       INTEGER auto_increment
        primary key,
    USERNAME CHARACTER VARYING(255) not null,
    PASSWORD CHARACTER VARYING(255) not null,
    NAME     CHARACTER VARYING(255) not null,
    LASTNAME CHARACTER VARYING(255) not null,
    ADMIN    BOOLEAN                not null
);

INSERT INTO USUARIOS ( USERNAME, PASSWORD, NAME, LASTNAME, ADMIN ) VALUES ( 'alanmedina1995@gmail.com.ar', 'admin1234', 'Alan', 'Medina', true );
INSERT INTO USUARIOS ( USERNAME, PASSWORD, NAME, LASTNAME, ADMIN ) VALUES ( 'zapatajulian79@gmail.com', 'admin1234', 'Julian', 'Zapata', true );
INSERT INTO USUARIOS ( USERNAME, PASSWORD, NAME, LASTNAME, ADMIN ) VALUES ( 'magiaunicornio42@gmail.com', 'user1234', 'Magia', 'Unicornio', false);
INSERT INTO USUARIOS ( USERNAME, PASSWORD, NAME, LASTNAME, ADMIN ) VALUES ( 'dragónvolador87@hotmail.com', 'user1234', 'Dragón', 'Volador', false );
