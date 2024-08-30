package it.tino.postgres.person.database;

import it.tino.postgres.person.Person;

import java.sql.Date;

/**
 * Represents columns of the people table when joined with other
 * tables, inside the resulting view.
 */
public interface PersonView {

    int getPersonId();
    void setPersonId(int personId);

    String getName();
    void setName(String name);

    Date getBirth();
    void setBirth(Date birth);

    Person.Gender getGender();
    void setGender(Person.Gender gender);
}
