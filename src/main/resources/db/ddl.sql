drop table if exists roles cascade;
drop table if exists users cascade;
drop table if exists reviews cascade;
drop table if exists products cascade;
drop table if exists orders cascade;
drop table if exists category cascade;
drop table if exists order_items cascade;
drop table if exists cart cascade;
drop table if exists cart_items cascade;
drop table if exists payment cascade;

create table roles(
    id varchar primary key,
    name varchar not null
);

create table users(
    id varchar primary key,
    username varchar unique,
    password varchar not null,
    role_id varchar not null,

    foreign key (role_id) references roles (id)
);

create table category(
    id int primary key,
    name varchar not null
);

create table products(
    id varchar primary key,
    name varchar not null,
    price decimal not null,
    category int not null,
    
    foreign key (category) REFERENCES category (id)
);



create table reviews(
    id varchar primary key,
    rating integer not null,
    comment varchar not null,
    user_id varchar not null,
    product_id varchar not null,

    foreign key (user_id) references users (id),
    foreign key (product_id) references products (id)
);

create table payment(
    id varchar primary key,
    order_id varchar not NULL,
    payment_num int NOT NULL
);

create table orders(
    id varchar primary key,
    user_id varchar not null,
    payment_id varchar not null,
    total decimal NOT NULL,

    foreign key (user_id) references users (id),
    foreign key (payment_id) references payment (id)
);

create table order_items(
    id varchar primary key,
    order_id varchar not null,
    product_id varchar not null,
    quantity integer not null,

    foreign key (order_id) references orders(id),
    foreign key (product_id) references products (id)
);

create TABLE cart(
    id varchar primary key,
    user_id varchar not null,

    foreign key (user_id) references users (id)
);

create table cart_items(
    id varchar primary key,
    cart_id varchar not null,
    product_id varchar not null,
    quantity integer not null,

    foreign key (cart_id) references cart (id),
    foreign key (product_id) references products (id)
);