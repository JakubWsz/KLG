DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS rent_item;
DROP TABLE IF EXISTS reservation;

CREATE TABLE person
(
    id          LONG AUTO_INCREMENT PRIMARY KEY NOT NULL,
    firstname   VARCHAR                         NOT NULL,
    surname     VARCHAR                         NOT NULL,
    person_type VARCHAR                         NOT NULL
);

CREATE TABLE rent_item
(
    id        LONG AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name      VARCHAR                         NOT NULL,
    price     DECIMAL(20, 2)                  NOT NULL,
    stretch   DOUBLE                          NOT NULL,
    lessor_id LONG                            NOT NULL,

    FOREIGN KEY (lessor_id)
        REFERENCES person (id)
);

CREATE TABLE reservation
(
    id                 LONG AUTO_INCREMENT PRIMARY KEY NOT NULL,
    domain_id          VARCHAR                         NOT NULL,
    rent_item_id       LONG                            NOT NULL,
    rent_period_start  DATE                            NOT NULL,
    rent_period_finish DATE                            NOT NULL,
    renter_id          LONG                            NOT NULL,

    FOREIGN KEY (rent_item_id)
        REFERENCES rent_item (id),
    FOREIGN KEY (renter_id)
        REFERENCES person (id)
);