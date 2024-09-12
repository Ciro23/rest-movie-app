package it.tino.restmovieapp.movie;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.umd.cs.findbugs.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Movie implements Comparable<Movie> {

	private int id;
    private String title;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private int budget;
    private int boxOffice;
    private int runtime;

	@Nullable
	private String overview;

	private List<Integer> genreIds = new ArrayList<>();
    private List<Integer> directorIds = new ArrayList<>();
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
	
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(LocalDate releaseDate) {
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

	@Nullable
	public String getOverview() {
		return overview;
	}
	
	public void setOverview(@Nullable String overview) {
		if (overview != null && overview.isBlank()) {
			overview = null;
		}
		this.overview = overview;
	}

	public List<Integer> getGenreIds() {
		return genreIds;
	}

	public void setGenreIds(List<Integer> genres) {
		this.genreIds = genres;
	}

	public List<Integer> getDirectorIds() {
		return directorIds;
	}

	public void setDirectorIds(List<Integer> directors) {
		this.directorIds = directors;
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
        builder.append("  \"genres\": \"").append(genreIds).append("\"\n");
        builder.append("  \"directors\": \"").append(directorIds).append("\"\n");
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

	@Override
	public int compareTo(@NotNull Movie other) {
		return Comparator.comparing(Movie::getTitle)
				.thenComparing(Movie::getId)
				.compare(this, other);
	}
}
