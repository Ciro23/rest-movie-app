package it.tino.restmovieapp.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

public class Review implements Comparable<Review> {
    
	private int id;
    private int movieId;
    private int userId;

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
    	if (content != null && content.isBlank()) {
    		content = null;
    	}
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("  \"movieId\": ").append(movieId).append(",\n");
        builder.append("  \"userId\": \"").append(userId).append("\",\n");
        builder.append("  \"creationDate\": \"").append(creationDate).append("\",\n");
        builder.append("  \"vote\": \"").append(vote).append("\",\n");
        builder.append("  \"review\": ").append(content).append(",\n");
        builder.append("}");
        return builder.toString();
    }

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return id == other.id;
	}

    @Override
    public int compareTo(@NotNull Review other) {
        return Comparator.comparing(Review::getCreationDate, Comparator.reverseOrder())
                .thenComparing(Review::getId)
                .compare(this, other);
    }
}
