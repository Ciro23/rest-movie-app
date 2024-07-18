package it.tino.postgres.review;

import java.sql.Timestamp;

import edu.umd.cs.findbugs.annotations.Nullable;
import it.tino.postgres.database.Identifiable;

public class Review implements Identifiable<Integer> {
    
	private int id;
    private int movieId;
    private int userId;
    
    @Nullable
    private Timestamp creationDate;
    
    private double vote;
    
    @Nullable
    private String review;
    
    public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
