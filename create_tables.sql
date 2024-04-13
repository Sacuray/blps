DROP TABLE IF EXISTS car CASCADE;

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