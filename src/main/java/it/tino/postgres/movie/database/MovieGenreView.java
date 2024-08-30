package it.tino.postgres.movie.database;

import java.sql.Date;

public class MovieGenreView implements MovieView {

	private int movieId;
	private String title;
	private Date releaseDate;
	private int budget;
	private int boxOffice;
	private int runtime;
	private String overview;
	
	private int genreId;
	private String genreName;

	@Override
	public int getMovieId() {
		return movieId;
	}

	@Override
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public Date getReleaseDate() {
		return releaseDate;
	}

	@Override
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public int getBudget() {
		return budget;
	}

	@Override
	public void setBudget(int budget) {
		this.budget = budget;
	}

	@Override
	public int getBoxOffice() {
		return boxOffice;
	}

	@Override
	public void setBoxOffice(int boxOffice) {
		this.boxOffice = boxOffice;
	}

	@Override
	public int getRuntime() {
		return runtime;
	}

	@Override
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	@Override
	public String getOverview() {
		return overview;
	}

	@Override
	public void setOverview(String overview) {
		this.overview = overview;
	}

	public int getGenreId() {
		return genreId;
	}
	
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	
	public String getGenreName() {
		return genreName;
	}
	
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
}
