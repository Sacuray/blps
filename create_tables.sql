DROP TABLE IF EXISTS car CASCADE;
DROP TABLE IF EXISTS user CASCADE;
DROP TABLE IF EXISTS admin CASCADE;
DROP TABLE IF EXISTS superadmin CASCADE;

-- Создание таблицы Car
CREATE TABLE car (
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
CREATE TABLE user (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    phone_number VARCHAR(20),
    registration_date DATE,
);

-- Создание таблицы Admin
CREATE TABLE admin (
    admin_id SERIAL PRIMARY KEY,
    user_id INT UNIQUE REFERENCES User(user_id)
);

-- Создание таблицы Superadmin
CREATE TABLE superadmin (
    superadmin_id SERIAL PRIMARY KEY,
    user_id INT UNIQUE REFERENCES User(user_id)
);

-- Создание таблицы UserCar
CREATE TABLE UserCar (
    user_car_id SERIAL PRIMARY KEY,
    user_id INT,
    car_id INT,
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (car_id) REFERENCES Car(car_id)
);