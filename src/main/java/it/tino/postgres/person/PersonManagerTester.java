package it.tino.postgres.person;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import it.tino.postgres.database.DataManagerTester;

public class PersonManagerTester extends DataManagerTester<Person, Integer> {
    
	private final PersonManager personManager;
	
    public PersonManagerTester(PersonManager personManager) {
        this.personManager = personManager;
    }
    
    @Override
	protected Supplier<List<Person>> onSelectAll() {
		return personManager::selectAll;
	}

	@Override
	protected Function<Person, Person> onInsert() {
		return personManager::insert;
	}

	@Override
	protected Function<Person, Person> onUpdate() {
		return personManager::update;
	}

	@Override
	protected Function<Integer, Boolean> onDelete() {
		return personManager::delete;
	}

    @Override
    protected Person onCreateObject() {
    	Person person = new Person();
        person.setId(0);
        person.setName("New person");
        person.setBirth(LocalDate.of(1998, 6, 3));
        person.setGender(Person.Gender.MALE);
        
        return person;
    }

    @Override
    protected void onUpdateObject(Person objectToUpdate) {
        objectToUpdate.setName("New person (updated)");
        objectToUpdate.setBirth(LocalDate.of(1998, 7, 4));
        objectToUpdate.setGender(Person.Gender.FEMALE);
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
