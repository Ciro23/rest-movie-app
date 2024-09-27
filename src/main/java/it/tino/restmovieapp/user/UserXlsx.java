package it.tino.restmovieapp.user;

import org.jetbrains.annotations.NotNull;

public class UserXlsx implements Comparable<UserXlsx> {

    private int id;
    private String username;
    private String email;

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

    @Override
    public int compareTo(@NotNull UserXlsx other) {
        return Integer.compare(id, other.getId());
    }
}
