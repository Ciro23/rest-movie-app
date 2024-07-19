insert into movies (title, release_date, budget, box_office, runtime, overview) values (
    'Pulp Fiction',
    '1994-10-14',
    8000000,
    213928762,
    154,
    'Jules Winnfield (Samuel L. Jackson) and Vincent Vega (John Travolta) are two hitmen who are out to retrieve a suitcase stolen from their employer, mob boss Marsellus Wallace (Ving Rhames). Wallace has also asked Vincent to take his wife Mia (Uma Thurman) out a few days later when Wallace himself will be out of town. Butch Coolidge (Bruce Willis) is an aging boxer who is paid by Wallace to lose his fight. The lives of these seemingly unrelated people are woven together comprising of a series of funny, bizarre and uncalled-for incidents.'
);

insert into movies (title, release_date, budget, box_office, runtime, overview) values (
    'Goodfellas',
    '1990-9-21',
    25000000,
    47049784,
    145,
    'Henry Hill might be a small time gangster, who may have taken part in a robbery with Jimmy Conway and Tommy De Vito, two other gangsters who might have set their sights a bit higher. His two partners could kill off everyone else involved in the robbery, and slowly start to think about climbing up through the hierarchy of the Mob. Henry, however, might be badly affected by his partners'' success, but will he consider stooping low enough to bring about the downfall of Jimmy and Tommy?'
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
    'Quentin Tarantino',
    '1963-3-27',
    'm'
);

insert into movies_actors (movie_id, actor_id, role, cast_order) values (1, 1, 'Vincent Vega', 0);
insert into movies_directors (movie_id, director_id) values (1, 3);

insert into users (username, password) values ('random_user', '$2a$12$AhDZ6au1fPFhBr2OKYA95.9/3CshPd3d86XY2Kqr0kr2vDP.9eK9W');
insert into users (username, password) values ('random_user_2', '$2a$12$YvoOvGSarvqgFQrj3/9LsuyKXYgDVWcqXl3M3ZIzbfSxfQkLl0HmC');

insert into reviews (movie_id, user_id, creation_date, vote, review) values (
    2, 1, '2024-7-18 10:45:00', 7.1, 'Good movie'
);

insert into genres (name) values ('Comedy');
insert into genres (name) values ('Horror');
insert into genres (name) values ('Romance');
insert into genres (name) values ('Crime');
insert into genres (name) values ('Thriller');
insert into genres (name) values ('Biography');

insert into movies_genres (movie_id, genre_id) values (1, 1);
insert into movies_genres (movie_id, genre_id) values (1, 5);
