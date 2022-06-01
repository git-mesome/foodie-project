DROP TABLE IF EXISTS ACCOUNT;

CREATE TABLE ACCOUNT
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(40)  NOT NULL,
    email            VARCHAR(100) NOT NULL,
    password         VARCHAR(100) NOT NULL
);