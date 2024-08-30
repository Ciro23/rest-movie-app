package it.tino.postgres.person.database;

import it.tino.postgres.person.Person;

import java.sql.Date;

public class PersonDb {
    
    protected int id = 0;
    protected String name;
    protected Date birth;
    protected Person.Gender gender;

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

    public Person.Gender getGender() {
        return gender;
    }

    public void setGender(Person.Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonDb personDb = (PersonDb) o;

        if (id != personDb.id) return false;
        if (!name.equals(personDb.name)) return false;
        if (!birth.equals(personDb.birth)) return false;
        return gender == personDb.gender;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + birth.hashCode();
        result = 31 * result + gender.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");
        builder.append("  \"id\": ").append(id).append(",\n");
        builder.append("  \"name\": \"").append(name).append("\",\n");
        builder.append("  \"birth\": \"").append(birth).append("\",\n");
        builder.append("  \"gender\": ").append(gender).append(",\n");
        builder.append("}");
        return builder.toString();
    }
}
