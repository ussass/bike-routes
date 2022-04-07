CREATE TABLE users
(
    id         BIGSERIAL    NOT NULL PRIMARY KEY,
    login      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(255) NOT NULL
);

CREATE TABLE routes
(
    id            BIGSERIAL    NOT NULL PRIMARY KEY,
    title         VARCHAR(255) NOT NULL,
    description   VARCHAR(255) NOT NULL
);