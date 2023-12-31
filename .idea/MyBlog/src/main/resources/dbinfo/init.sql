CREATE TABLE IF NOT EXISTS types
(
    id SERIAL PRIMARY KEY ,
    name TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS countries
(
    id SERIAL PRIMARY KEY,
    name TEXT
);
CREATE TABLE IF NOT EXISTS products
(
    id SERIAL PRIMARY KEY ,
    name TEXT NOT NULL ,
    price INTEGER ,
    weight INTEGER ,
    description TEXT ,
    countries_id INTEGER ,
    type_id INTEGER NOT NULL,
    FOREIGN KEY (type_id) REFERENCES types (id) ON DELETE CASCADE ,
    FOREIGN KEY (countries_id) REFERENCES countries (id) ON DELETE CASCADE
);
create table if not exists users
(
    id serial primary key,
    email text,
    username text,
    password text,
    role text
);
create table if not exists basket
(
    id serial primary key,
    user_id integer,
    product_id integer,
    product_count integer,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);
create table if not exists roles
(
    id serial primary key,
    username text
)