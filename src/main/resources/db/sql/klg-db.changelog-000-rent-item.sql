DROP TABLE IF EXISTS rent_item;


CREATE TABLE rent_item
(
    id      LONG AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name    VARCHAR                         NOT NULL,
    price   DECIMAL(20, 2)                  NOT NULL,
    stretch DOUBLE                          NOT NULL
);