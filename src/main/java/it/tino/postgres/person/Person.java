package it.tino.postgres.person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.tino.postgres.movie.MovieActor;
import it.tino.postgres.movie.MovieDirector;
import it.tino.postgres.person.database.PersonJdbc.Gender;

public class Person {
	
	private int id = 0;
    private String name;
    private Date birth;
    private Gender gender;
    private List<MovieDirector> directedMovies = new ArrayList<>();
    private List<MovieActor> starredMovies = new ArrayList<>();
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public List<MovieDirector> getDirectedMovies() {
		return directedMovies;
	}
	
	public void setDirectedMovies(List<MovieDirector> directedMovies) {
		this.directedMovies = directedMovies;
	}
	
	public List<MovieActor> getStarredMovies() {
		return starredMovies;
	}
	
	public void setStarredMovies(List<MovieActor> starredMovies) {
		this.starredMovies = starredMovies;
	}
	
	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("  \"id\": ").append(id).append(",\n");
        builder.append("  \"name\": \"").append(name).append("\",\n");
        builder.append("  \"birth\": \"").append(birth).append("\",\n");
        builder.append("  \"gender\": ").append(gender).append(",\n");
        builder.append("  \"directedMovies\": ").append(directedMovies.toString()).append(",\n");
        builder.append("  \"starredMovies\": ").append(starredMovies.toString()).append(",\n");
        builder.append("}");
        return builder.toString();
    }
}
