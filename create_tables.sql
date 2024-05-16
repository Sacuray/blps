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
    userId SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    phoneNumber VARCHAR(20),
    registrationDate DATE,
    vallet BIGINT
);

-- Создание таблицы Admin
CREATE TABLE myadmin (
    adminId SERIAL PRIMARY KEY,
    userId INT UNIQUE REFERENCES myuser(userId)
);

-- Создание таблицы Superadmin
CREATE TABLE mysuperadmin (
    superAdminId SERIAL PRIMARY KEY,
    userId INT UNIQUE REFERENCES myuser(userId)
);

-- Создание таблицы UserCar
CREATE TABLE myusercar (
    userCarId SERIAL PRIMARY KEY,
    userId INT,
    carId INT,
    status VARCHAR(25),
    FOREIGN KEY (userId) REFERENCES myuser(userId),
    FOREIGN KEY (carId) REFERENCES mycar(id)
);