package it.tino.restmovieapp.genre;

import org.jetbrains.annotations.NotNull;

public class GenreXlsx implements Comparable<GenreXlsx> {

    private int id;
    private String name;

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
    public int compareTo(@NotNull GenreXlsx other) {
        return Integer.compare(id, other.getId());
    }
}
