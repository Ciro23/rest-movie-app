package it.tino.postgres.movie;

/**
 * Represents the relationship between movies and genres.
 */
public class MovieGenre {
	
	private int movieId;
	private int genreId;
	
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public int getGenreId() {
		return genreId;
	}
	
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	
	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("    \"movieId\": ").append(movieId).append(",\n");
        builder.append("    \"genreId\": ").append(genreId).append(",\n");
        builder.append("}");
        return builder.toString();
    }
}
