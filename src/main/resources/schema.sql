DROP TABLE IF EXISTS public.users;
DROP TABLE IF EXISTS public.widgets;

CREATE TABLE public.users
(
  id SERIAL PRIMARY KEY,
  gravatar character varying(255),
  name character varying(255)
);

CREATE TABLE public.widgets
(
  id SERIAL PRIMARY KEY,
  color character varying(255),
  inventory integer,
  melts boolean NOT NULL,
  name character varying(255),
  price character varying(255)
);
