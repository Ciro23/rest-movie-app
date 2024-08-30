package it.tino.postgres.movie.database;

import java.sql.Date;

import it.tino.postgres.person.Person.Gender;
import it.tino.postgres.person.database.PersonView;

/**
 * Represents the view which joins movies and directors (people).
 */
public class MovieDirectorView implements MovieView, PersonView {
	
	private int movieId;
	private String title;
	private Date releaseDate;
	private int budget;
	private int boxOffice;
	private int runtime;
	private String overview;
	
	private int personId;
	private String name;
	private Date birth;
	private Gender gender;

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
	public void setBoxOffice(int box_office) {
		this.boxOffice = box_office;
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

	@Override
	public int getPersonId() {
		return personId;
	}

	@Override
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Date getBirth() {
		return birth;
	}

	@Override
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public Gender getGender() {
		return gender;
	}

	@Override
	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
