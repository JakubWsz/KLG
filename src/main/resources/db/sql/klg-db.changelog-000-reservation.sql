DROP TABLE IF EXISTS reservation;

CREATE TABLE reservation
(
    id                 LONG AUTO_INCREMENT PRIMARY KEY NOT NULL,
    domainId           VARCHAR                            NOT NULL,
    rent_item_id       LONG                            NOT NULL,
    rent_period_start  DATE                            NOT NULL,
    rent_period_finish DATE                            NOT NULL,
    lessor_id          LONG                            NOT NULL,
    renter_id          LONG                            NOT NULL,

    FOREIGN KEY (rent_item_id)
        REFERENCES rent_item (id),
    FOREIGN KEY (lessor_id)
        REFERENCES person (id),
    FOREIGN KEY (renter_id)
        REFERENCES person (id)
);