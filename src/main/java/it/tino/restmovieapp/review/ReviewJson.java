package it.tino.restmovieapp.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.umd.cs.findbugs.annotations.Nullable;
import it.tino.restmovieapp.movie.Movie;
import it.tino.restmovieapp.user.User;

import java.time.LocalDateTime;

public class ReviewJson {

    private int id;
    private Movie movie;
    private User user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @Nullable
    private LocalDateTime creationDate;

    private float vote;

    @Nullable
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Nullable
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(@Nullable LocalDateTime creationDate) {
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
}
