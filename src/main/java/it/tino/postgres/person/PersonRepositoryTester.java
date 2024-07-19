package it.tino.postgres.person;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import it.tino.postgres.database.RepositoryTester;
import it.tino.postgres.person.Person.Gender;

public class PersonRepositoryTester extends RepositoryTester<Person, Integer> {
    
    public PersonRepositoryTester(PersonRepository personDataSource) {
        super(personDataSource);
    }

    @Override
    protected Person onCreateObject() {
        Person person = new Person();
        person.setId(0);
        person.setName("New person");
        person.setBirth(new Date(1000000));
        person.setGender(Gender.MALE);
        
        return person;
    }

    @Override
    protected void onUpdateObject(Person objectToUpdate) {
        objectToUpdate.setName("New person (updated)");
        objectToUpdate.setBirth(new Date(8000000));
        objectToUpdate.setGender(Gender.FEMALE);
    }
    
    public List<Person> getDirectorsOfMovie() {
        int movieId = 1;
        System.out.println("------- SELECT DIRECTORS OF MOVIE -------");
        List<Person> allRows = ((PersonRepository) repository).findDirectorsByMovieId(movieId);
        System.out.println("Movie id: " + movieId);
        System.out.println(allRows);
        System.out.println("-----------------------------------------");
        
        return allRows;
    }
    
    public List<Person> getActorsOfMovie() {
        int movieId = 1;
        System.out.println("------- SELECT ACTORS OF MOVIE -------");
        List<MovieActor> allRows = ((PersonRepository) repository).findActorsByMovieId(movieId);
        System.out.println("Movie id: " + movieId);
        System.out.println(allRows);
        System.out.println("---------------------------------------");
        
        return new ArrayList<>(allRows);
    }
    
    public Person getLatestPerson(Collection<Person> people) {
        return people
            .stream()
            .max(Comparator.comparingInt(Person::getId))
            .get();
    }
}
