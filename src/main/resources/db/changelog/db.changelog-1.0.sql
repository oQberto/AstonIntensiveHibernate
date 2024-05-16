--liquibase formatted sql
-- Прикольео что есть liquibase так сразу понятно что за сущности, можно еще резделить логику на ddl/dml операции и поделить папки на сущности а в changelog указывать просто файлы сущностей
--changeset alexander_ermakov:1
CREATE TABLE IF NOT EXISTS manufacturer
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL UNIQUE
);

--changeset alexander_ermakov:2
CREATE TABLE IF NOT EXISTS country_of_origin
(
    id         BIGSERIAL PRIMARY KEY,
    short_name CHAR(3)      NOT NULL UNIQUE,
    full_name  VARCHAR(128) NOT NULL UNIQUE
);

--changeset alexander_ermakov:3
CREATE TABLE IF NOT EXISTS item
(
    id                   BIGSERIAL PRIMARY KEY,
    manufacturer_id      BIGINT REFERENCES manufacturer (id)      NOT NULL,
    country_of_origin_id BIGINT REFERENCES country_of_origin (id) NOT NULL,
    name                 VARCHAR(128)                             NOT NULL UNIQUE,
    type                 VARCHAR(128)                             NOT NULL,
    price                NUMERIC                                  NOT NULL,
    quantity             INTEGER                                  NOT NULL,
    discount             DECIMAL CHECK (discount <= 1.0)
);

--changeset alexander_ermakov:4
CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(56)  NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(64)  NOT NULL
);

--changeset alexander_ermakov:5
CREATE TABLE IF NOT EXISTS orders
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users (id) ON DELETE CASCADE,
    item_id BIGINT REFERENCES item (id) ON DELETE CASCADE,
    status  VARCHAR(64) NOT NULL,
    time    TIMESTAMP   NOT NULL
);

--changeset alexander_ermakov:6
CREATE TABLE IF NOT EXISTS item_order
(
    item_id  BIGINT REFERENCES item (id),
    order_id BIGINT REFERENCES orders (id),
    PRIMARY KEY (item_id, order_id)
);

--changeset alexander_ermakov:7
CREATE TABLE IF NOT EXISTS item_attribute
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE
);

--changeset alexander_ermakov:8
CREATE TABLE IF NOT EXISTS item_attribute_value
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE
);

--changeset alexander_ermakov:9
CREATE TABLE IF NOT EXISTS item_item_attribute
(
    item_id                 BIGINT REFERENCES item (id),
    item_attribute_id       BIGINT REFERENCES item_attribute (id),
    item_attribute_value_id BIGINT REFERENCES item_attribute_value (id),
    PRIMARY KEY (item_id, item_attribute_id)
);
