package it.tino.postgres.genre;

import java.util.List;

import it.tino.postgres.Repository;

public interface GenreRepository extends Repository<Genre, Integer> {

    List<Genre> findByMovieId(int movieId);
}
