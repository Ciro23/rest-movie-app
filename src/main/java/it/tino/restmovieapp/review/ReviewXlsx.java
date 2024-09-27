package it.tino.restmovieapp.review;

import edu.umd.cs.findbugs.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

public class ReviewXlsx implements Comparable<ReviewXlsx> {

    private int id;

    /**
     * The movie title.
     */
    private String movie;

    /**
     * The user email.
     */
    private String user;

    /**
     * A date already formatted.
     */
    private String creationDate;

    private float vote;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public float getVote() {
        return vote;
    }

    public void setVote(float vote) {
        this.vote = vote;
    }

    @Nullable
    public String getContent() {
        return content;
    }

    public void setContent(@Nullable String content) {
        this.content = content;
    }

    @Override
    public int compareTo(@NotNull ReviewXlsx other) {
        return Integer.compare(id, other.getId());
    }
}
