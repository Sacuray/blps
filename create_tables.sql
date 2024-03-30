DROP TABLE IF EXISTS car CASCADE;

CREATE TYPE engineType AS ENUM(
    'DIESEL',
    'PETROL'
);

CREATE TYPE wheelType AS ENUM(
    'LEFT',
    'RIGHT'
);
CREATE TABLE car (
    id serial PRIMARY KEY,
    model VARCHAR(64) NOT NULL,
    adNumber BIGINT NOT NULL UNIQUE ,
    yearOfRelease date NOT NULL,
    colour varchar(64),
    wheel wheelType,
    engine engineType,
    engineVolume FLOAT,
    mileage BIGINT,
    accident BOOLEAN,
    price BIGINT
);