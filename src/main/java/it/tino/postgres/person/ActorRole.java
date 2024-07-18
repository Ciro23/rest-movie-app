package it.tino.postgres.person;

import java.sql.Date;

public class ActorRole extends Person {
    
    private int movieId;
    private String roleName;
    private int castOrder;
    
    public ActorRole(
        int personId,
        String name,
        Date birth,
        Gender gender,
        int movieId,
        String roleName,
        int castOrder
    ) {
        super(personId, name, birth, gender);
        this.movieId = movieId;
        this.roleName = roleName;
        this.castOrder = castOrder;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
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
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("  \"id\": ").append(id).append(",\n");
        builder.append("  \"name\": \"").append(name).append("\",\n");
        builder.append("  \"birth\": \"").append(birth).append("\",\n");
        builder.append("  \"gender\": ").append(gender).append(",\n");
        builder.append("  \"roleName\": ").append(roleName).append(",\n");
        builder.append("  \"castOrder\": ").append(castOrder).append(",\n");
        builder.append("}");
        return builder.toString();
    }
}
