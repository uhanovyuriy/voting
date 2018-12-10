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
ALTER SEQUENCE global_seq
RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User1', 'user1@yandex.ru', '$2a$10$3gFJhDDDpb0aXDGlBIqZPuIcOoGWOv398Kmbi4Lyz/DEaFDjmrqKC'),
       ('User2', 'user2@yandex.ru', '$2a$10$3gFJhDDDpb0aXDGlBIqZPuIcOoGWOv398Kmbi4Lyz/DEaFDjmrqKC'),
       ('Admin1', 'admin1@gmail.com', '$2a$10$gyIatFXjxzzNvMMmJAYtYOEWk5/Ie1PwCVr8m2hgc0laPX.Yq4U4m'),
       ('Admin2', 'admin2@gmail.com', '$2a$10$gyIatFXjxzzNvMMmJAYtYOEWk5/Ie1PwCVr8m2hgc0laPX.Yq4U4m');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_USER', 100001),
       ('ROLE_ADMIN', 100002),
       ('ROLE_ADMIN', 100003);

INSERT INTO restaurants (name, address)
VALUES ('rest1', 'address1'),
       ('rest2', 'address2'),
       ('rest3', 'address3'),
       ('rest4', 'address4');

INSERT INTO dishes (restaurant_id, name, price)
VALUES (100004, 'dishes1', 550),
       (100004, 'dishes2', 850),
       (100004, 'dishes3', 1550),
       (100005, 'dishes1', 500),
       (100005, 'dishes2', 800),
       (100006, 'dishes1', 10000),
       (100006, 'dishes2', 8500),
       (100006, 'dishes3', 15500),
       (100006, 'dishes4', 5500),
       (100007, 'dishes1', 300),
       (100007, 'dishes2', 150);

INSERT INTO history_voting (user_id, restaurant_id, date_time_voting)
VALUES (100000, 100005, '2018-10-28 10:00:00'),
       (100001, 100006, '2018-10-28 10:02:00'),
       (100000, 100004, '2018-10-29 10:00:00'),
       (100001, 100004, '2018-10-29 10:02:00'),
       (100000, 100007, '2018-10-30 10:00:00'),
       (100001, 100005, '2018-10-30 10:02:00'),
       (100000, 100005, '2018-10-31 10:00:00'),
       (100001, 100005, '2018-10-31 10:02:00');
