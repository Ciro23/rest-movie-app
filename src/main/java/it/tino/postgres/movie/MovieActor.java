package it.tino.postgres.movie;

/**
 * Represents the relationship between movies and actors (people).
 */
public class MovieActor {
	
	private int movieId;
	private int actorId;
	private String roleName;
	private int castOrder;
	
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public int getActorId() {
		return actorId;
	}
	
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public int getCastOrder() {
		return castOrder;
	}
	
	public void setCastOrder(int castOrder) {
		this.castOrder = castOrder;
	}
	
	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("    \"movieId\": ").append(movieId).append(",\n");
        builder.append("    \"actorId\": ").append(actorId).append(",\n");
        builder.append("    \"roleName\": \"").append(roleName).append("\",\n");
        builder.append("    \"castOrder\": \"").append(castOrder).append("\",\n");
        builder.append("}");
        return builder.toString();
    }
}
