package it.tino.postgres.movie.database;

import java.sql.Date;

import it.tino.postgres.person.database.PersonJdbc.Gender;

/**
 * Represents the view which joins movies and actors (people).
 */
public class MovieActorView {
	
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
	private String roleName;
	private int castOrder;
	
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId = movieId;
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
	
	public int getPersonId() {
		return personId;
	}
	
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
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
}
