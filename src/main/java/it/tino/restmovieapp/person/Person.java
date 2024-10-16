package it.tino.restmovieapp.person;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.tino.restmovieapp.movie.MovieActor;
import it.tino.restmovieapp.movie.MovieDirector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Person implements Comparable<Person> {
	
	private int id = 0;

	/**
	 * May also contain a middle name, if present, as there's no specific
	 * fields for that.
	 */
    private String name;

	/**
	 * Not every actor uses a last name for their artist name, like Zendaya.
	 */
	@Nullable
    private String lastName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birth;

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

	@Nullable
	public String getLastName() {
		return lastName;
	}

	public void setLastName(@Nullable String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirth() {
		return birth;
	}
	
	public void setBirth(LocalDate birth) {
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
		Person other = (Person) obj;
		return id == other.id;
	}

	@Override
	public int compareTo(@NotNull Person other) {
		return Comparator.comparing(Person::getLastName, Comparator.nullsLast(Comparator.naturalOrder()))
				.thenComparing(Person::getName)
				.thenComparing(Person::getId)
				.compare(this, other);
	}

	public enum Gender {
		MALE("m"),
		FEMALE("f");

		private final String id;

		Gender(String id) {
			this.id = id.toLowerCase();
		}

		public String getId() {
			return id;
		}

		public static Gender fromId(String id) {
			String lowerCaseId = id.toLowerCase().trim();

			if (lowerCaseId.equals(MALE.id)) {
				return MALE;
			}

			if (lowerCaseId.equals(FEMALE.id)) {
				return FEMALE;
			}

			throw new IllegalArgumentException("Gender with id '" + id + "' is not"
					+ " within supported values ('m', 'f')");
		}
	}
}
