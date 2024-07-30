package it.tino.postgres.person.database;

import java.sql.Date;

import it.tino.postgres.database.Identifiable;

public class PersonJdbc implements Identifiable<Integer> {
    
    protected int id = 0;
    protected String name;
    protected Date birth;
    protected Gender gender;

    public Integer getId() {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public enum Gender {
        MALE("m"),
        FEMALE("f");
        
        private final String id;

        private Gender(String id) {
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
