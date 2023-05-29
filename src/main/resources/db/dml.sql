INSERT INTO roles (id, name) VALUES ('b7228352-5541-4826-9ef6-6c4c1f5a8dac', 'ADMIN');
INSERT INTO roles (id, name) VALUES ('cc812463-1e4e-464b-9a56-f38d4711d0b0', 'USER');

INSERT INTO category (id, name) VALUES(1, 'RPG');
INSERT INTO category (id, name) VALUES(2, 'Shooter');
INSERT INTO category (id, name) VALUES(3, 'Tactics');
INSERT INTO category (id, name) VALUES(4, 'Casual');

INSERT INTO products (id, name, description, price, category, stock) values ('a353098a-fb53-11ed-be56-0242ac120002', 'The Elder Scrolls', 'Fus ro Dah', '9.99', '1', '42');
INSERT INTO products (id, name, description, price, category, stock) values ('a3531022-fb53-11ed-be56-0242ac120002', 'Legend of Zelda', 'His name is Link, you are to defeat Ganon, NOTHING ELSE', '69.99', '1', '42');
INSERT INTO products (id, name, description, price, category, stock) values ('a3531182-fb53-11ed-be56-0242ac120002', 'Diablo', 'Another beta will be out soon... jk', '69.99', '1', '42');
INSERT INTO products (id, name, description, price, category, stock) values ('1aff46ce-fb54-11ed-be56-0242ac120002', 'Halo', '360 no scope', '2.50', '2', '42');
INSERT INTO products (id, name, description, price, category, stock) values ('1aff4e1c-fb54-11ed-be56-0242ac120002', 'Overwatch', 'Pocket healer', '3.50', '2', '42');
INSERT INTO products (id, name, description, price, category, stock) values ('1aff4666-fb54-11ed-be56-0242ac120002', 'Fallout', 'Fus ro Dah... with guns', '19.99', '2', '42');
INSERT INTO products (id, name, description, price, category, stock) values ('94653ee2-fb54-11ed-be56-0242ac120002', 'Advance Wars', 'Welcome to 2001', '42.99', '3', '42');
INSERT INTO products (id, name, description, price, category, stock) values ('946533ab-fb54-11ed-be56-0242ac120002', 'Civilization', 'Touch grass', '104.99', '3', '42');
INSERT INTO products (id, name, description, price, category, stock) values ('f3d77236-fb54-11ed-be56-0242ac120002', 'Stardew Valley', 'Mine craft... but with dating', '2.99', '4', '42');
INSERT INTO products (id, name, description, price, category, stock) values ('f3d78df8-fb54-11ed-be56-0242ac120002', 'MineCraft', 'Everyone else has a redstone computer... you have a rock', '25.99', '4', '42');
INSERT INTO products (id, name, description, price, category, stock) values ('f3d78dgg-fb54-11ed-be56-0242ac120002', 'The Sims', 'Check price', '9999.99', '4', '42');

INSERT INTO users (id, username, password, role_id) VALUES('bcbc968f-d38e-41e2-a98b-4e04d7ad0114', 'register1', '$2a$10$rumobNxwjx73CDNwqDdgEuHmGGsSifOxs.4BJ24RM2xPNF1yEXDhi', 'cc812463-1e4e-464b-9a56-f38d4711d0b0');

INSERT INTO cart (id, user_id) values ('1a353098a-fb53-11ed-be56-0242ac120002', 'bcbc968f-d38e-41e2-a98b-4e04d7ad0114');

INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('a353098a-fb53-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', 'a353098a-fb53-11ed-be56-0242ac120002', 56);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('a3531022-fb53-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', 'a3531022-fb53-11ed-be56-0242ac120002', 12);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('a3531182-fb53-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', 'a3531182-fb53-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('1aff46ce-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', '1aff46ce-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('1aff4e1c-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', '1aff4e1c-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('1aff4666-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', '1aff4666-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('94653ee2-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', '94653ee2-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('946533ab-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', '946533ab-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('f3d77236-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', 'f3d77236-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('f3d78df8-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', 'f3d78df8-fb54-11ed-be56-0242ac120002', 34);