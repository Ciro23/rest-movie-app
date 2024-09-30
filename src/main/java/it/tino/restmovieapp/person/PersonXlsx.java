package it.tino.restmovieapp.person;

import org.jetbrains.annotations.NotNull;

public class PersonXlsx implements Comparable<PersonXlsx> {

    private int id = 0;
    private String name;
    private String lastName;

    /**
     * A date already formatted.
     */
    private String birth;

    private Person.Gender gender;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public Person.Gender getGender() {
        return gender;
    }

    public void setGender(Person.Gender gender) {
        this.gender = gender;
    }

    @Override
    public int compareTo(@NotNull PersonXlsx other) {
        return Integer.compare(id, other.getId());
    }
}
