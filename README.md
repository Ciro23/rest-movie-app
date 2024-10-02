## Introduction
This is a simple CMS web application to manage movies, genres, users, review, actors and directors.

The goal of this project was practicing with REST APIs and learn new technologies as JAX-RS, MyBatis, Jasper Report, TypeScript and Angular.

## Running with Docker
This project is composed by:
- Frontend, in Angular 18;
- Backend, with Java 21, JAX-RS and MyBatis;
- Database, with Postgres 17.

Copy the `.env.example` file, rename it to `.env` and change the values as needed on your environment.

[Docker](https://docs.docker.com/) and [Docker Compose](https://docs.docker.com/compose/) are necessary to run the project: use `docker compose up --build -d` or `docker compose --profile dev up --build` during development in case the database schema has changed, and MyBatis mappers and models need to be re-generated.
