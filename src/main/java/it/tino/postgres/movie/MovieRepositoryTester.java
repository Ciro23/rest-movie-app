package it.tino.postgres.movie;

import java.sql.Date;
import java.util.Collection;
import java.util.Comparator;

import it.tino.postgres.database.RepositoryTester;

public class MovieRepositoryTester extends RepositoryTester<Movie, Integer> {
    
    public MovieRepositoryTester(MovieRepository movieDataSource) {
        super(movieDataSource);
    }

    @Override
    protected Movie onCreateObject() {
    	Movie movie = new Movie();
    	movie.setTitle("New movie");
    	movie.setReleaseDate(new Date(7000000));
    	movie.setBudget(150);
    	movie.setBoxOffice(300);
    	movie.setRuntime(90);
    	movie.setOverview("A newly created movie");
        
    	return movie;
    }

    @Override
    protected void onUpdateObject(Movie objectToUpdate) {
        objectToUpdate.setTitle("New movie (updated)");
        objectToUpdate.setReleaseDate(new Date(8000000));
        objectToUpdate.setBudget(400);
        objectToUpdate.setBoxOffice(550);
        objectToUpdate.setRuntime(95);
        objectToUpdate.setOverview("A newly created movie (updated)");
    }
    
    public Movie getLatestMovie(Collection<Movie> movies) {
        return movies
            .stream()
            .max(Comparator.comparingInt(Movie::getId))
            .get();
    }
}
