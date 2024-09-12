package it.tino.restmovieapp.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Objects;

public class User implements Comparable<User> {
	
	private int id;
    private String username;
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("  \"id\": ").append(id).append(",\n");
        builder.append("  \"email\": \"").append(email).append("\",\n");
        builder.append("  \"username\": \"").append(username).append("\",\n");
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public int compareTo(@NotNull User other) {
		return Comparator.comparing(User::getUsername)
				.thenComparing(User::getEmail)
				.compare(this, other);
	}
}
