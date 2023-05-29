INSERT INTO roles (id, name) VALUES ('b7228352-5541-4826-9ef6-6c4c1f5a8dac', 'ADMIN');
INSERT INTO roles (id, name) VALUES ('cc812463-1e4e-464b-9a56-f38d4711d0b0', 'USER');

INSERT INTO category (id, name) VALUES(1, 'RPG');
INSERT INTO category (id, name) VALUES(2, 'Shooter');
INSERT INTO category (id, name) VALUES(3, 'Tactics');
INSERT INTO category (id, name) VALUES(4, 'Casual');

INSERT INTO products (id, name, price, category) values ('a353098a-fb53-11ed-be56-0242ac120002', 'The Elder Scrolls', '9.99', '1');
INSERT INTO products (id, name, price, category) values ('a3531022-fb53-11ed-be56-0242ac120002', 'Legend of Zelda', '69.99', '1');
INSERT INTO products (id, name, price, category) values ('a3531182-fb53-11ed-be56-0242ac120002', 'Final Fantasy', '69.99', '1');
INSERT INTO products (id, name, price, category) values ('1aff46ce-fb54-11ed-be56-0242ac120002', 'PUBG', '2.50', '2');
INSERT INTO products (id, name, price, category) values ('1aff4e1c-fb54-11ed-be56-0242ac120002', 'Overwatch', '3.50', '2');
INSERT INTO products (id, name, price, category) values ('1aff4666-fb54-11ed-be56-0242ac120002', 'Destiny 2', '19.99', '2');
INSERT INTO products (id, name, price, category) values ('94653ee2-fb54-11ed-be56-0242ac120002', 'Advance Wars', '49.99', '3');
INSERT INTO products (id, name, price, category) values ('946533ab-fb54-11ed-be56-0242ac120002', 'Civilization', '104.99', '3');
INSERT INTO products (id, name, price, category) values ('94653a88-fb54-11ed-be56-0242ac120002', 'Warhammer', '39.99', '3');
INSERT INTO products (id, name, price, category) values ('f3d77cbe-fb54-11ed-be56-0242ac120002', 'Gang Beasts', '19.99', '4');
INSERT INTO products (id, name, price, category) values ('f3d77236-fb54-11ed-be56-0242ac120002', 'Stardew Valley', '2.99', '4');
INSERT INTO products (id, name, price, category) values ('f3d78df8-fb54-11ed-be56-0242ac120002', 'MineCraft', '25.99', '4');

INSERT INTO users (id, username, password, role_id) values('38d853d5-8235-4d05-b285-d51f0b11ca6b', 'test', 'pass', 'b7228352-5541-4826-9ef6-6c4c1f5a8dac');
INSERT INTO cart (id, user_id) values ('1a353098a-fb53-11ed-be56-0242ac120002', 'fba7de01-d73a-4483-8227-16c216f05d33');

INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('a353098a-fb53-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', 'a353098a-fb53-11ed-be56-0242ac120002', 56);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('a3531022-fb53-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', 'a3531022-fb53-11ed-be56-0242ac120002', 12);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('a3531182-fb53-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', 'a3531182-fb53-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('1aff46ce-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', '1aff46ce-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('1aff4e1c-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', '1aff4e1c-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('1aff4666-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', '1aff4666-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('94653ee2-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', '94653ee2-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('946533ab-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', '946533ab-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('94653a88-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', '94653a88-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('f3d77cbe-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', 'f3d77cbe-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('f3d77236-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', 'f3d77236-fb54-11ed-be56-0242ac120002', 34);
INSERT INTO cart_items (id, cart_id, product_id, quantity) values ('f3d78df8-fb54-11ed-be56-0242ac120002', '1a353098a-fb53-11ed-be56-0242ac120002', 'f3d78df8-fb54-11ed-be56-0242ac120002', 34);