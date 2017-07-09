DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS restaurants CASCADE;
DROP TABLE IF EXISTS menus CASCADE;
DROP TABLE IF EXISTS meals CASCADE;
DROP TABLE IF EXISTS votes CASCADE;
DROP SEQUENCE IF EXISTS global_seq CASCADE;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE public.users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR NOT NULL,
  email      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL,
  registered DATE           DEFAULT now(),
  enabled    BOOLEAN             DEFAULT TRUE
);
CREATE UNIQUE INDEX users_email_uindex
  ON public.users (email);

CREATE TABLE public.roles
(
  user_id INT     NOT NULL,
  role    VARCHAR NOT NULL,
  CONSTRAINT roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE public.restaurants
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR NOT NULL
);

CREATE TABLE public.menus
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id INT       NOT NULL,
  price         INT       NOT NULL,
  published     DATE NOT NULL  DEFAULT now(),
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE public.meals
(
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name    VARCHAR NOT NULL,
  menu_id INT     NOT NULL,
  FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE
);

CREATE TABLE public.votes
(
  id             INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id        INT       NOT NULL,
  menu_id       INT       NOT NULL,
  vote_date DATE NOT NULL  DEFAULT now(),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (menu_id) REFERENCES menus (id)
);