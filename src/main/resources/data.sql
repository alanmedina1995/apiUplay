CREATE TABLE IF NOT EXISTS users
(
    id          INT auto_increment primary key,
    username    VARCHAR(20) not null,
    email       VARCHAR(50) not null,
    password    VARCHAR(50) not null,
    name        VARCHAR(50) not null,
    lastname    VARCHAR(50) not null,
    phonenumber VARCHAR(50) not null,
    utncoin     INT         not null
);

INSERT INTO users (username, email, password, name, lastname, phonenumber, utncoin)
VALUES ('alanmedina','alanmedina1995@gmail.com', 'admin1234', 'Alan', 'Medina', '2235879632', 5000),
 ('julianzapata','zapatajulian79@gmail.com', 'admin1234', 'Julian', 'Zapata', '2235123487', 5000);

INSERT INTO users (username, email, password, name, lastname, phonenumber, utncoin)
VALUES
    ('luciamartinez','luciamartinez@gmail.com', 'admin1234', 'Lucia', 'Martinez', '2235123456', 50),
    ('marcosrodriguez','marcosrodriguez@hotmail.com', 'admin1234', 'Marcos', 'Rodriguez', '2235987612', 50),
    ('anagarcia','ana_garcia@yahoo.com', 'admin1234', 'Ana', 'Garcia', '2235768901', 50),
    ('juancarloslopez','juancarloslopez@gmail.com', 'admin1234', 'Juan Carlos', 'Lopez', '2235874365', 50),
    ('carolinaramirez','carolinaramirez@hotmail.com', 'admin1234', 'Carolina', 'Ramirez', '2235123789', 50),
    ('pedromartinez','pedromartinez@gmail.com', 'admin1234', 'Pedro', 'Martinez', '2235987432', 50),
    ('lauragonzalez','lauragonzalez@yahoo.com', 'admin1234', 'Laura', 'Gonzalez', '2235769087', 50),
    ('oscarrodriguez','oscarrodriguez@gmail.com', 'admin1234', 'Oscar', 'Rodriguez', '2235876543', 50),
    ('valerialopez','valerialopez@hotmail.com', 'admin1234', 'Valeria', 'Lopez', '2235123901', 50),
    ('estebanperez','estebanperez@gmail.com', 'admin1234', 'Esteban', 'Perez', '2235987654', 50),
    ('marianagonzalez','marianagonzalez@yahoo.com', 'admin1234', 'Mariana', 'Gonzalez', '2235769123', 50),
    ('raulrodriguez','raulrodriguez@gmail.com', 'admin1234', 'Raul', 'Rodriguez', '2235876789', 50),
    ('danielaramos','danielaramos@hotmail.com', 'admin1234', 'Daniela', 'Ramos', '2235123098', 50),
    ('sergiohernandez','sergiohernandez@gmail.com', 'admin1234', 'Sergio', 'Hernandez', '2235987123', 50),
    ('sofiatorres','sofiatorres@yahoo.com', 'admin1234', 'Sofia', 'Torres', '2235769456', 50),
    ('martinromero','martinromero@gmail.com', 'admin1234', 'Martin', 'Romero', '2235876234', 50),
    ('andreamartinez','andreamartinez@hotmail.com', 'admin1234', 'Andrea', 'Martinez', '2235123765', 50),
    ('javierrodriguez','javierrodriguez@gmail.com', 'admin1234', 'Javier', 'Rodriguez', '2235987345', 50);
