package it.tino.restmovieapp.movie;

import edu.umd.cs.findbugs.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class MovieXlsx implements Comparable<MovieXlsx> {

    private int id;
    private String title;

    /**
     * A date already formatted.
     */
    private String releaseDate;

    private int budget;
    private int boxOffice;
    private int runtime;

    /**
     * A string containing all genre names separated by a comma.
     */
    private String genres;

    /**
     * A string containing all director names separated by a comma.
     */
    private String directors;

    /**
     * A string containing all actor names separated by a comma.
     */
    private String actors;

    private String overview;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(int boxOffice) {
        this.boxOffice = boxOffice;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getGenreNames() {
        return genres;
    }

    public void setGenreNames(String genreNames) {
        this.genres = genreNames;
    }

    public String getDirectorNames() {
        return directors;
    }

    public void setDirectorNames(String directorNames) {
        this.directors = directorNames;
    }

    public String getActorNames() {
        return actors;
    }

    public void setActorNames(String actorNames) {
        this.actors = actorNames;
    }

    @Nullable
    public String getOverview() {
        return overview;
    }

    public void setOverview(@Nullable String overview) {
        this.overview = overview;
    }

    @Override
    public int compareTo(@NotNull MovieXlsx other) {
        return Integer.compare(id, other.getId());
    }
}
