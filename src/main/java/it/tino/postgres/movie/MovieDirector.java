package it.tino.postgres.movie;

/**
 * Represents the relationship between movies and directors (people).
 */
public class MovieDirector {
	
	private int movieId;
	private int directorId;
	
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public int getDirectorId() {
		return directorId;
	}
	
	public void setDirectorId(int directorId) {
		this.directorId = directorId;
	}
	
	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("    \"movieId\": ").append(movieId).append(",\n");
        builder.append("    \"directorId\": ").append(directorId).append(",\n");
        builder.append("}");
        return builder.toString();
    }
}
