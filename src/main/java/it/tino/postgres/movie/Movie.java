package it.tino.postgres.movie;

import java.sql.Date;

import it.tino.postgres.database.Identifiable;

public class Movie implements Identifiable<Integer> {
    
    private int id;
    private String title;
    private Date releaseDate;
    private int budget;
    private int boxOffice;
    private int runtime;
    private String overview;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("  \"id\": ").append(id).append(",\n");
        builder.append("  \"title\": \"").append(title).append("\",\n");
        builder.append("  \"releaseDate\": \"").append(releaseDate).append("\",\n");
        builder.append("  \"budget\": ").append(budget).append(",\n");
        builder.append("  \"boxOffice\": ").append(boxOffice).append(",\n");
        builder.append("  \"runtime\": ").append(runtime).append(",\n");
        builder.append("  \"overview\": \"").append(overview).append("\"\n");
        builder.append("}");
        return builder.toString();
    }
}
