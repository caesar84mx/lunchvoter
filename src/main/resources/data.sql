DELETE FROM roles;
DELETE FROM users;
DELETE FROM restaurants;
DELETE FROM menus;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, enabled)
VALUES ('User', 'user@yandex.ru', '$2a$10$d11ozJqkkFCPMdET0f0baOhzlYVF4rnZfhIBVKwNt9vuNbVSISdMa', TRUE);

INSERT INTO users (name, email, password, enabled)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$wweTA3K6djQI5POVsFspXOwRaEIE6JYgVKvRnQMBdSin3fkIcOfsu', TRUE);

INSERT INTO roles (role, user_id) VALUES
    ('ROLE_USER', 100000),
    ('ROLE_USER', 100001),
    ('ROLE_ADMIN', 100001);

INSERT INTO restaurants (name) VALUES ('Escherichia coli');
INSERT INTO restaurants (name) VALUES ('Salmonella');

INSERT INTO menus (restaurant_id, price) VALUES (100002, 33000);
INSERT INTO menus (restaurant_id, price) VALUES (100003, 25000);

INSERT INTO meals (name, menu_id)
VALUES ('Суп с фрикадельками', 100004);

INSERT INTO meals (name, menu_id)
VALUES ('Картофель отварной', 100004);

INSERT INTO meals (name, menu_id)
VALUES ('Суп харчо', 100005);

INSERT INTO meals (name, menu_id)
VALUES ('Долма', 100005);