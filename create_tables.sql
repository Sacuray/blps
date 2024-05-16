DROP TABLE IF EXISTS mycar CASCADE;
DROP TABLE IF EXISTS myuser CASCADE;
DROP TABLE IF EXISTS myadmin CASCADE;
DROP TABLE IF EXISTS mysuperadmin CASCADE;
DROP TABLE IF EXISTS myusercar CASCADE;

-- Создание таблицы Car
CREATE TABLE mycar (
     id serial PRIMARY KEY,
     brand VARCHAR(64) NOT NULL,
     model VARCHAR(64) NOT NULL,
     adNumber BIGINT NOT NULL UNIQUE ,
     yearOfRelease date NOT NULL,
     colour varchar(64),
     wheel varchar(64),
     engine varchar(64),
     engineVolume FLOAT,
     mileage BIGINT,
     accident BOOLEAN,
     price BIGINT
);

-- Создание таблицы User
CREATE TABLE myuser (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    phone_number VARCHAR(20),
    registration_date DATE,
    vallet BIGINT
);

-- Создание таблицы Admin
CREATE TABLE myadmin (
    admin_id SERIAL PRIMARY KEY,
    user_id INT UNIQUE REFERENCES myuser(user_id)
);

-- Создание таблицы Superadmin
CREATE TABLE mysuperadmin (
    superadmin_id SERIAL PRIMARY KEY,
    user_id INT UNIQUE REFERENCES myuser(user_id)
);

-- Создание таблицы UserCar
CREATE TABLE myusercar (
    user_car_id SERIAL PRIMARY KEY,
    user_id INT,
    car_id INT,
    status VARCHAR(25),
    FOREIGN KEY (user_id) REFERENCES myuser(user_id),
    FOREIGN KEY (car_id) REFERENCES mycar(id)
);