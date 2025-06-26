CREATE TABLE product
(
    id       SERIAL PRIMARY KEY,
    code     VARCHAR(8) UNIQUE NOT NULL,
    name     VARCHAR(100)      NOT NULL,
    quantity INTEGER           NOT NULL CHECK (quantity >= 0)
);

INSERT INTO product (code, name, quantity)
VALUES ('00000000', 'Cadeira Gamer', 15),
       ('99999999', 'Mesa de Escritório', 8),
       ('88888888', 'Monitor 27"', 5);
