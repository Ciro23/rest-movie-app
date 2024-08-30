package it.tino.postgres.movie.database;

import java.sql.Date;

/**
 * Represents columns of the movies table when joined with other
 * tables, inside the resulting view.
 */
public interface MovieView {

    int getMovieId();
    void setMovieId(int movieId);

    String getTitle();
    void setTitle(String title);

    Date getReleaseDate();
    void setReleaseDate(Date releaseDate);

    int getBudget();
    void setBudget(int budget);

    int getBoxOffice();
    void setBoxOffice(int box_office);

    int getRuntime();
    void setRuntime(int runtime);

    String getOverview();
    void setOverview(String overview);
}
