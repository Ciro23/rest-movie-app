package it.tino.postgres.movie;

import it.tino.postgres.database.JdbcManager;
import it.tino.postgres.genre.GenreDao;
import it.tino.postgres.person.PersonDao;

public class MovieService {

	private final JdbcManager database;
	
	private final MovieDao movieDao;
	private final PersonDao personDao;
	private final GenreDao genreDao;
	
	public MovieService(JdbcManager database, MovieDao movieDao, PersonDao personDao, GenreDao genreDao) {
		this.database = database;
		this.movieDao = movieDao;
		this.personDao = personDao;
		this.genreDao = genreDao;
	}

//	public void ciao() throws SQLException {
//		Movie movie = new Movie();
//		movie.setTitle("New movie");
//    	movie.setReleaseDate(new Date(7000000));
//    	movie.setBudget(150);
//    	movie.setBoxOffice(300);
//    	movie.setRuntime(90);
//    	movie.setOverview("A newly created movie");
//    	
//    	Person person = new Person();
//        person.setId(0);
//        person.setName("New person");
//        person.setBirth(new Date(1000000));
//        person.setGender(Person.Gender.MALE);
//        
//        Genre genre = new Genre();
//        genre.setName("New genre");
//		
//		database.executeInTransaction(() -> {
//			movieDao.insert(movie);
//			personDao.insert(person);
//			genreDao.insert(genre);
//		});
//	}
}
