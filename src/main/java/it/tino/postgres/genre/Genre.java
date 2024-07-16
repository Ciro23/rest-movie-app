package it.tino.postgres.genre;

public class Genre {

    private int id;
    private String name;
    
    /**
     * Constructor for non-persisted genres, as the
     * id is yet to be generated.
     */
    public Genre(String name) {
        this.id = 0;
        this.name = name;
    }
    
    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

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
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("  \"id\": ").append(id).append(",\n");
        builder.append("  \"name\": \"").append(name).append("\",\n");
        builder.append("}");
        return builder.toString();
    }
}
