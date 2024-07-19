create table movies (
    id serial primary key,
    title varchar(128) not null,
    release_date date not null,
    budget int,
    box_office int not null,
    runtime int not null,
    overview text not null
);

CREATE TYPE gender AS ENUM ('m', 'f');
create table people (
    id serial primary key,
    name varchar(128) not null,
    birth date not null,
    gender gender not null
);

create table movies_actors (
    movie_id int references movies(id) on delete cascade,
    actor_id int references people(id) on delete cascade,
    role varchar(32) not null,
    cast_order int not null check (cast_order >= 0),
    primary key (movie_id, actor_id, role),
    constraint unique_cast_order_per_movie unique (movie_id, cast_order)
);

create table movies_directors (
    movie_id int references movies(id) on delete cascade,
    director_id int references people(id) on delete cascade,
    primary key (movie_id, director_id)
);

create table genres (
    id serial primary key,
    name varchar(32) unique
);

create table movies_genres (
    movie_id int references movies(id),
    genre_id int references genres(id),
    primary key (movie_id, genre_id)
);

create table users (
    id serial primary key,
    username varchar(32) not null,
    password varchar(128) not null
);

create table reviews (
    id serial primary key,
    movie_id int references movies(id) on delete cascade,
    user_id int references users(id) on delete cascade,
    creation_date timestamp default now(),
    vote real not null check (vote >= 0 and vote <= 10),
    review text,
    unique (movie_id, user_id)
);
