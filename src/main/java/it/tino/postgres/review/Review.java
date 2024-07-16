package it.tino.postgres.review;

import java.sql.Timestamp;

import edu.umd.cs.findbugs.annotations.Nullable;

public class Review {
    
    private int movieId;
    private int userId;
    
    @Nullable
    private Timestamp creationDate;
    
    private double vote;
    
    @Nullable
    private String review;

    /**
     * Constructor for non-persisted reviews, as the
     * creation date is yet to be established (during persistence).
     */
    public Review(
        int movieId,
        int userId,
        double vote,
        String review
    ) {
        this.movieId = movieId;
        this.userId = userId;
        setVote(vote);
        this.review = review;
    }
    
    /**
     * Constructor which allows to define a custom creation date.
     * Objects created this way are considered as already persisted
     * in the database.
     */
    public Review(
        int movieId,
        int userId,
        Timestamp creationDate,
        double vote,
        String review
    ) {
        this.movieId = movieId;
        this.userId = userId;
        this.creationDate = creationDate;
        setVote(vote);
        this.review = review;
    }
    
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    /**
     * Adding a creation date makes this object considered as
     * already persisted in the database.
     */
    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public double getVote() {
        return vote;
    }

    public void setVote(double vote) {
        if (vote < 0 || vote > 10) {
            throw new IllegalArgumentException("Movie vote '" + vote + "' is not within"
                    + " legal values (0-10");
        }
        
        this.vote = vote;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("  \"movieId\": ").append(movieId).append(",\n");
        builder.append("  \"userId\": \"").append(userId).append("\",\n");
        builder.append("  \"vote\": \"").append(vote).append("\",\n");
        builder.append("  \"review\": ").append(review).append(",\n");
        builder.append("}");
        return builder.toString();
    }
}
