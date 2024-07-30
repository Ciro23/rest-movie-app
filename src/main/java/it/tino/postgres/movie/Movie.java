package it.tino.postgres.movie;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Movie {

	private int id;
    private String title;
    private Date releaseDate;
    private int budget;
    private int boxOffice;
    private int runtime;
    private String overview;
    private List<MovieGenre> genres = new ArrayList<>();
    private List<MovieDirector> directors = new ArrayList<>();
    private List<MovieActor> actors = new ArrayList<>();
    
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
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate) {
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
	
	public String getOverview() {
		return overview;
	}
	
	public void setOverview(String overview) {
		this.overview = overview;
	}

	public List<MovieGenre> getGenres() {
		return genres;
	}

	public void setGenres(List<MovieGenre> genres) {
		this.genres = genres;
	}

	public List<MovieDirector> getDirectors() {
		return directors;
	}

	public void setDirectors(List<MovieDirector> directors) {
		this.directors = directors;
	}

	public List<MovieActor> getActors() {
		return actors;
	}

	public void setActors(List<MovieActor> actors) {
		this.actors = actors;
	}
	
	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("  \"id\": ").append(id).append(",\n");
        builder.append("  \"title\": \"").append(title).append("\",\n");
        builder.append("  \"releaseDate\": \"").append(releaseDate).append("\",\n");
        builder.append("  \"budget\": ").append(budget).append(",\n");
        builder.append("  \"boxOffice\": ").append(boxOffice).append(",\n");
        builder.append("  \"runtime\": ").append(runtime).append(",\n");
        builder.append("  \"overview\": \"").append(overview).append("\"\n");
        builder.append("  \"genres\": \"").append(genres).append("\"\n");
        builder.append("  \"directors\": \"").append(directors).append("\"\n");
        builder.append("  \"actors\": \"").append(actors).append("\"\n");
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
		Movie other = (Movie) obj;
		return id == other.id;
	}
}
