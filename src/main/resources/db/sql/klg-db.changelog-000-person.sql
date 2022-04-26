DROP TABLE IF EXISTS person;



CREATE TABLE person
(
    id         LONG AUTO_INCREMENT PRIMARY KEY NOT NULL,
    firstname  VARCHAR                         NOT NULL,
    surname    VARCHAR                         NOT NULL,
    personType VARCHAR                         NOT NULL
);