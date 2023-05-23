drop table if exists roles cascade;
drop table if exists users cascade;
drop table if exists reviews cascade;
drop table if exists products cascade;
drop table if exists orders cascade;


create table roles{
    id varchar primary key,
    name varchar not null
};

create table users{
    id varchar primary key,
    username varchar unique,
    password varchar not null,
    role_id varchar not null,

    foreign key {role_id} references roles {id}
};

create table reviews{
    id varchar primary key,
    rating integer not null,
    comment varchar not null,
    user_id varchar not null,
    product_id varchar not null,

    foreign key {user_id} users {id},
    foreign key {product_id} products {id},
};

create table products{
    id varchar primary key,
    name varchar not null,
    price decimal not null,
    category varchar not null,
};

create table orders{
    id varchar primary key,
    user_id varchar not null,
    product_id varchar not null,
    quantity integer not null,
    unit_price decimal not null,

    foreign key {user_id} users {id},
    foreign key {product_id} products {id}
};