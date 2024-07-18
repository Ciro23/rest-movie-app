create table movies (
    id serial primary key,
    title varchar(128) not null,
    release_date date not null,
    budget int,
    box_office int not null,
    runtime int not null,
    overview text not null
);

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
    cast_order int not null check (cast_order >= 0), -- l'ordine in cui mostrare l'attore nella pagina del film
    primary key (movie_id, actor_id, role),
    constraint unique_cast_order_per_movie unique (movie_id, cast_order)
);

create table movies_directors (
    movie_id int references movies(id) on delete cascade,
    director_id int references people(id) on delete cascade,
    primary key (movie_id, director_id)
);

CREATE EXTENSION IF NOT EXISTS citext;
create table genres (
    id serial primary key,
    name citext unique
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
    movie_id int references movies(id) on delete cascade,
    user_id int references users(id) on delete cascade,
    creation_date timestamp defaukt now(),
    vote real not null check (vote >= 0 and vote <= 10),
    review text not null default '',
    primary key (movie_id, user_id)
);

insert into users (username, password) values ('ciro', '$2a$12$AhDZ6au1fPFhBr2OKYA95.9/3CshPd3d86XY2Kqr0kr2vDP.9eK9W');
insert into users (username, password) values ('random_user', '$2a$12$AhDZ6au1fPFhBr2OKYA95.9/3CshPd3d86XY2Kqr0kr2vDP.9eK9W');

insert into genres (name) values ('Drama');
insert into genres (name) values ('Comedy');
insert into genres (name) values ('Horror');
insert into genres (name) values ('Romance');
insert into genres (name) values ('Crime');
insert into genres (name) values ('Thriller');
insert into genres (name) values ('Biography');

insert into movies_genres (movie_id, genre_id) values (1, 5);
insert into movies_genres (movie_id, genre_id) values (1, 1);
insert into movies_genres (movie_id, genre_id) values (2, 1);
insert into movies_genres (movie_id, genre_id) values (2, 5);
insert into movies_genres (movie_id, genre_id) values (2, 7);
insert into movies_genres (movie_id, genre_id) values (3, 6);
insert into movies_genres (movie_id, genre_id) values (3, 5);
insert into movies_genres (movie_id, genre_id) values (3, 1);

insert into movies (title, release_date, budget, box_office, runtime, overview) values (
    'Pulp Fiction',
    '1994-10-14',
    8000000,
    213928762,
    154,
    'Jules Winnfield (Samuel L. Jackson) and Vincent Vega (John Travolta) are two hitmen who are out to retrieve a suitcase stolen from their employer, mob boss Marsellus Wallace (Ving Rhames). Wallace has also asked Vincent to take his wife Mia (Uma Thurman) out a few days later when Wallace himself will be out of town. Butch Coolidge (Bruce Willis) is an aging boxer who is paid by Wallace to lose his fight. The lives of these seemingly unrelated people are woven together comprising of a series of funny, bizarre and uncalled-for incidents.'
);

insert into people (name, birth, gender) values (
    'John Travolta',
    '1954-2-18',
    'm'
);
insert into people (name, birth, gender) values (
    'Uma Thurman',
    '1970-4-29',
    'f'
);
insert into people (name, birth, gender) values (
    'Samuel L. Jackson',
    '1948-12-21',
    'm'
);
insert into people (name, birth, gender) values (
    'Bruce Willis',
    '1955-3-19',
    'm'
);
insert into people (name, birth, gender) values (
    'Quentin Tarantino',
    '1963-3-27',
    'm'
);

insert into movies_directors (movie_id, director_id) values (1, 5);
insert into movies_actors (movie_id, actor_id, role, cast_order) values (1, 1, 'Vincent Vega', 0);
insert into movies_actors (movie_id, actor_id, role, cast_order) values (1, 2, 'Mia Wallace', 1);
insert into movies_actors (movie_id, actor_id, role, cast_order) values (1, 3, 'Jules Winnfield', 2);
insert into movies_actors (movie_id, actor_id, role, cast_order) values (1, 4, 'Butch Coolidge', 3);
insert into movies_actors (movie_id, actor_id, role, cast_order) values (1, 5, 'Jimmie Dimmick', 4);

insert into movies (title, release_date, budget, box_office, runtime, overview) values (
    'Goodfellas',
    '1990-9-21',
    25000000,
    47049784,
    145,
    'Henry Hill might be a small time gangster, who may have taken part in a robbery with Jimmy Conway and Tommy De Vito, two other gangsters who might have set their sights a bit higher. His two partners could kill off everyone else involved in the robbery, and slowly start to think about climbing up through the hierarchy of the Mob. Henry, however, might be badly affected by his partners'' success, but will he consider stooping low enough to bring about the downfall of Jimmy and Tommy?'
);

insert into people (name, birth, gender) values (
    'Robert De Niro',
    '1943-8-17',
    'm'
);
insert into people (name, birth, gender) values (
    'Ray Liotta',
    '1954-12-18',
    'm'
);
insert into people (name, birth, gender) values (
    'Joe Pesci',
    '1943-2-9',
    'm'
);
insert into people (name, birth, gender) values (
    'Lorraine Bracco',
    '1954-10-2',
    'f'
);
insert into people (name, alias, birth, gender) values (
    'Martin Scorsese',
    '1942-11-17',
    'm'
);

insert into movies_directors (movie_id, director_id) values (2, 10);
insert into movies_actors (movie_id, actor_id, role, cast_order) values (2, 6, 'James Conway', 0);
insert into movies_actors (movie_id, actor_id, role, cast_order) values (2, 7, 'Henry Hill', 1);
insert into movies_actors (movie_id, actor_id, role, cast_order) values (2, 8, 'Tommy DeVito', 2);
insert into movies_actors (movie_id, actor_id, role, cast_order) values (2, 9, 'Karen Hill', 3);

insert into reviews (movie_id, user_id, vote, review) values (
    1, 1, 9.1, 'Very well made'
);
insert into reviews (movie_id, user_id, vote, review) values (
    2, 1, 10, 'My favorite movie of all time'
);
insert into reviews (movie_id, user_id, vote, review) values (
    1, 2, 7, ''
);
insert into reviews (movie_id, user_id, vote, review) values (
    2, 2, 7.5, ''
);

create view movie_details as
select
    m.id,
    m.title,
    m.release_date,
    m.budget,
    m.box_office,
    m.runtime,
    string_agg(distinct g.name, ', ') as genres,
    round(avg(ur.vote)::numeric, 2) as average_vote,
    string_agg(distinct p.alias, ', ') as directors,
    string_agg(distinct p2.alias, ', ') as cast,
    m.overview
from
    movies m
left join
    movies_genres mg on m.id = mg.movie_id
left join
    genres g on mg.genre_id = g.id
left join
    users_reviews ur on m.id = ur.movie_id
left join
    movies_directors md on m.id = md.movie_id
left join
    movies_actors ma on m.id = ma.movie_id
left join people p on md.director_id = p.id
left join people p2 on ma.actor_id = p2.id
group by
    m.id, m.title, m.release_date, m.budget, m.box_office, m.runtime, m.overview;

insert into movies (title, release_date, budget, box_office, runtime, overview) values (
    'Uncut Gems',
    '2019-12-25',
    19000000,
    50023780,
    135,
    'Howard Ratner (Adam Sandler) is a once successful New York gems dealer whose gambling addiction has left his family and career in shambles, and him hundreds of thousands in debt. Always looking for the next big bet, Howard thinks he finally hit it big when he discovers a rare uncut Opal from Ethiopia, with a very interested high-profile buyer. But the closer Howard gets to finally winning big, the more he is forced to realize he can''t keep running from the consequences of his actions.'
);
insert into people (name, birth, gender) values (
    'Benny Safdie',
    '1986-2-24',
    'm'
);
insert into people (name, birth, gender) values (
    'Joshua Safdie',
    '1984-4-3',
    'm'
);
insert into movies_directors (movie_id, director_id) values (3, 11);
insert into movies_directors (movie_id, director_id) values (3, 12);
insert into people (name, birth, gender) values (
    'Adam Sandler',
    '1966-9-6',
    'm'
);
insert into people (name, birth, gender) values (
    'Julia Fox',
    '1990-2-1',
    'f'
);
insert into movies_actors (movie_id, actor_id, role, cast_order) values (3, 13, 'Howard Ratner', 0);
insert into movies_actors (movie_id, actor_id, role, cast_order) values (3, , 'Julia De Fiore', 1);

