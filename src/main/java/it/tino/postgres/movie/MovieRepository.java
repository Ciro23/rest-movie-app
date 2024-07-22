package it.tino.postgres.movie;

import it.tino.postgres.Repository;

public interface MovieRepository extends Repository<Movie, Integer> {
	boolean createFirstMovie();
}
