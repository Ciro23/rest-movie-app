package it.tino.postgres.movie;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import it.tino.postgres.database.DataManagerTester;

public class MovieManagerTester extends DataManagerTester<Movie, Integer> {
    
	private final MovieManager movieManager;
	
    public MovieManagerTester(MovieManager movieManager) {
        this.movieManager = movieManager;
    }
    
    @Override
	protected Supplier<List<Movie>> onSelectAll() {
		return movieManager::selectAll;
	}

	@Override
	protected Function<Movie, Movie> onInsert() {
		return movieManager::insert;
	}

	@Override
	protected Function<Movie, Movie> onUpdate() {
		return movieManager::update;
	}

	@Override
	protected Function<Integer, Boolean> onDelete() {
		return movieManager::delete;
	}

    @Override
    protected Movie onCreateObject() {
    	Movie movie = new Movie();
    	movie.setTitle("New movie");
    	movie.setReleaseDate(LocalDate.of(2024, 1, 1));
    	movie.setBudget(150);
    	movie.setBoxOffice(300);
    	movie.setRuntime(90);
    	movie.setOverview("A newly created movie");
        
    	return movie;
    }

    @Override
    protected void onUpdateObject(Movie objectToUpdate) {
        objectToUpdate.setTitle("New movie (updated)");
        objectToUpdate.setReleaseDate(LocalDate.of(2024, 2, 10));
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
