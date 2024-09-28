DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    role VARCHAR(50) NOT NULL,
    enabled BOOLEAN NOT NULL,
    invaliddate Date NOT NULL
);

DROP TABLE IF EXISTS item;

CREATE TABLE item (
    id LONG PRIMARY KEY,
    itemname VARCHAR(50) NOT NULL,
    price INT NOT NULL,
    itemdescription VARCHAR(100) NOT NULL
);