package it.tino.postgres.user;

public class User {

    private int id;
    private String username;
    private String password;
    
    /**
     * Constructor for non-persisted users, as the
     * id is yet to be generated.
     */
    public User(String username, String password) {
        this.id = 0;
        this.username = username;
        this.password = password;
    }
    
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

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
        builder.append("  \"username\": \"").append(username).append("\",\n");
        builder.append("}");
        return builder.toString();
    }
}
