package it.tino.postgres.person;

import java.sql.Date;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import it.tino.postgres.database.DataManagerTester;
import it.tino.postgres.person.Person.Gender;

public class PersonManagerTester extends DataManagerTester<Person, Integer> {
    
	private final PersonManager personManager;
	
    public PersonManagerTester(PersonManager personManager) {
        this.personManager = personManager;
    }
    
    @Override
	protected Supplier<List<Person>> onSelectAll() {
		return () -> personManager.selectAll();
	}

	@Override
	protected Function<Person, Person> onInsert() {
		return (toInsert) -> personManager.insert(toInsert);
	}

	@Override
	protected Function<Person, Person> onUpdate() {
		return (toUpdate) -> personManager.update(toUpdate);
	}

	@Override
	protected Function<Integer, Boolean> onDelete() {
		return (id) -> personManager.delete(id);
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
    
//    public List<Person> getDirectorsOfMovie() {
//        int movieId = 1;
//        System.out.println("------- SELECT DIRECTORS OF MOVIE -------");
//        List<Person> allRows = personManager.selectDirectorsByMovieId(movieId);
//        System.out.println("Movie id: " + movieId);
//        System.out.println(allRows);
//        System.out.println("-----------------------------------------");
//        
//        return allRows;
//    }
//    
//    public List<Person> getActorsOfMovie() {
//        int movieId = 1;
//        System.out.println("------- SELECT ACTORS OF MOVIE -------");
//        List<MovieActor> allRows = personManager.selectActorsByMovieId(movieId);
//        System.out.println("Movie id: " + movieId);
//        System.out.println(allRows);
//        System.out.println("---------------------------------------");
//        
//        return new ArrayList<>(allRows);
//    }
    
    public Person getLatestPerson(Collection<Person> people) {
        return people
            .stream()
            .max(Comparator.comparingInt(Person::getId))
            .get();
    }
}
