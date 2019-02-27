DELETE
FROM history_voting;
DELETE
FROM dishes;
DELETE
FROM restaurants;
DELETE
FROM user_roles;
DELETE
FROM users;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User1', 'user1@yandex.ru', '{noop}password'),
       ('User2', 'user2@yandex.ru', '{noop}password'),
       ('Admin1', 'admin1@gmail.com', '{noop}admin'),
       ('Admin2', 'admin2@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_USER', 100001),
       ('ROLE_ADMIN', 100002),
       ('ROLE_ADMIN', 100003),
       ('ROLE_USER', 100003);

INSERT INTO restaurants (name, address)
VALUES ('rest1', 'address1'),
       ('rest2', 'address2'),
       ('rest3', 'address3'),
       ('rest4', 'address4');

INSERT INTO dishes (restaurant_id, name, price, created)
VALUES (100004, 'dishes1', 550, '2018-10-28'),
       (100004, 'dishes2', 850, '2018-10-28'),
       (100004, 'dishes3', 1550, '2018-10-28'),
       (100005, 'dishes1', 500, '2018-10-28'),
       (100005, 'dishes2', 800, '2018-10-30'),
       (100006, 'dishes1', 10000, '2018-10-28'),
       (100006, 'dishes2', 8500, '2018-10-28'),
       (100006, 'dishes3', 15500, '2018-10-29'),
       (100006, 'dishes4', 5500, '2018-10-29'),
       (100007, 'dishes1', 300, '2018-10-28'),
       (100007, 'dishes2', 150, '2018-10-28');

INSERT INTO history_voting (user_id, restaurant_id, date_voting)
VALUES (100000, 100005, '2018-10-28'),
       (100001, 100006, '2018-10-28'),
       (100000, 100004, '2018-10-29'),
       (100001, 100004, '2018-10-29'),
       (100000, 100007, '2018-10-30'),
       (100001, 100005, '2018-10-30'),
       (100000, 100005, '2018-10-31'),
       (100001, 100005, '2018-10-31'),
       (100002, 100007, '2018-10-31'),
       (100003, 100007, '2018-10-31');
