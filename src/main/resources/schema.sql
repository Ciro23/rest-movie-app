-- PostgreSQL 16.3

CREATE TABLE public.movies (
    id serial4 NOT NULL,
    title varchar(128) NOT NULL,
    release_date date NOT NULL,
    budget int4 NOT NULL,
    box_office int4 NOT NULL,
    runtime int4 NOT NULL,
    overview text NULL,
    CONSTRAINT movies_pkey PRIMARY KEY (id)
);

CREATE TABLE public.people (
    id serial4 NOT NULL,
    "name" varchar(128) NOT NULL,
    birth date NOT NULL,
    gender bpchar(1) NOT NULL,
    last_name varchar NULL,
    CONSTRAINT gender_check CHECK (((gender)::text = ANY (ARRAY[('m'::character varying)::text, ('f'::character varying)::text]))),
    CONSTRAINT people_pkey PRIMARY KEY (id)
);

CREATE TABLE public.movies_actors (
    movie_id int4 NOT NULL,
    actor_id int4 NOT NULL,
    "role" varchar(32) NOT NULL,
    cast_order int4 NOT NULL,
    CONSTRAINT movies_actors_cast_order_check CHECK ((cast_order >= 0)),
    CONSTRAINT movies_actors_pkey PRIMARY KEY (movie_id, actor_id, role),
    CONSTRAINT unique_cast_order_per_movie UNIQUE (movie_id, cast_order),
    CONSTRAINT movies_actors_actor_id_fkey FOREIGN KEY (actor_id) REFERENCES public.people(id) ON DELETE CASCADE,
    CONSTRAINT movies_actors_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(id) ON DELETE CASCADE
);

CREATE TABLE public.movies_directors (
    movie_id int4 NOT NULL,
    director_id int4 NOT NULL,
    CONSTRAINT movies_directors_pkey PRIMARY KEY (movie_id, director_id),
    CONSTRAINT movies_directors_director_id_fkey FOREIGN KEY (director_id) REFERENCES public.people(id) ON DELETE CASCADE,
    CONSTRAINT movies_directors_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(id) ON DELETE CASCADE
);

CREATE EXTENSION IF NOT EXISTS citext; -- case insensitive text
CREATE TABLE public.genres (
    id serial4 NOT NULL,
    "name" public.citext NULL,
    CONSTRAINT genres_name_key UNIQUE (name),
    CONSTRAINT genres_pkey PRIMARY KEY (id)
);

CREATE TABLE public.movies_genres (
    movie_id int4 NOT NULL,
    genre_id int4 NOT NULL,
    CONSTRAINT movies_genres_pkey PRIMARY KEY (movie_id, genre_id),
    CONSTRAINT movies_genres_genre_id_fkey FOREIGN KEY (genre_id) REFERENCES public.genres(id) ON DELETE CASCADE,
    CONSTRAINT movies_genres_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(id) ON DELETE CASCADE
);

CREATE TABLE public.users (
    id serial4 NOT NULL,
    username varchar(32) NOT NULL,
    "password" varchar(128) NOT NULL,
    email varchar(254) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_unique UNIQUE (email)
);

CREATE TABLE public.reviews (
    id serial4 NOT NULL,
    movie_id int4 NULL,
    user_id int4 NULL,
    creation_date timestamp DEFAULT now() NULL,
    vote float4 NOT NULL,
    review text NULL,
    CONSTRAINT reviews_movie_id_user_id_key UNIQUE (movie_id, user_id),
    CONSTRAINT reviews_pkey PRIMARY KEY (id),
    CONSTRAINT reviews_vote_check CHECK (((vote >= (0)::double precision) AND (vote <= (10)::double precision))),
    CONSTRAINT reviews_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(id) ON DELETE CASCADE,
    CONSTRAINT reviews_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE
);

CREATE VIEW v_movies_directors AS
    SELECT p.id as director_id, p.name, p.last_name, p.birth, p.gender, m.id as movie_id,
           m.title, m.release_date, m.budget, m.box_office, m.runtime, m.overview
    FROM people p
        JOIN movies_directors md ON p.id = md.director_id
        JOIN movies m on m.id = md.movie_id;


CREATE VIEW v_movies_actors AS
    SELECT p.id as actor_id, p.name, p.last_name, p.birth, p.gender, m.id as movie_id,
           m.title, m.release_date, m.budget, m.box_office, m.runtime, m.overview, ma.role, ma.cast_order
    FROM people p
        JOIN movies_actors ma ON p.id = ma.actor_id
        JOIN movies m on m.id = ma.movie_id;

CREATE VIEW v_movies_genres AS
    SELECT g.id as genre_id, g.name as name, m.id as movie_id, m.title,
       m.release_date, m.budget,m.box_office, m.runtime, m.overview
    FROM genres g
                                                                                                                                           JOIN movies_genres mg ON g.id = mg.genre_id
                                                                                                                                           join movies m on m.id = mg.movie_id;