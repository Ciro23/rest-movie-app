package it.tino.postgres.person;

import java.util.List;

import it.tino.postgres.Repository;

public interface PersonRepository extends Repository<Person, Integer>{

    List<Person> findDirectorsByMovieId(int movieId);
    List<ActorRole> findActorsByMovieId(int movieId);
}
