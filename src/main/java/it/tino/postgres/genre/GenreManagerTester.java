package it.tino.postgres.genre;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import it.tino.postgres.database.DataManagerTester;

public class GenreManagerTester extends DataManagerTester<Genre, Integer> {

	private final GenreManager genreManager;
	
    public GenreManagerTester(GenreManager genreManager) {
        this.genreManager = genreManager;
    }
    
    @Override
	protected Supplier<List<Genre>> onSelectAll() {
		return () -> genreManager.selectAll();
	}

	@Override
	protected Function<Genre, Genre> onInsert() {
		return (toInsert) -> genreManager.insert(toInsert);
	}

	@Override
	protected Function<Genre, Genre> onUpdate() {
		return (toUpdate) -> genreManager.update(toUpdate);
	}

	@Override
	protected Function<Integer, Boolean> onDelete() {
		return (id) -> genreManager.delete(id);
	}

    @Override
    protected Genre onCreateObject() {
    	Genre genre = new Genre();
    	genre.setName("New genre");
    	
    	return genre;
    }

    @Override
    protected void onUpdateObject(Genre objectToUpdate) {
        objectToUpdate.setName("New genre (updated)");
    }
    
//    public List<Genre> getGenresOfMovie() {
//        int movieId = 1;
//        System.out.println("------- SELECT GENRES OF MOVIE -------");
//        List<Genre> allRows = genreManager.selectByMovieId(movieId);
//        System.out.println("Movie id: " + movieId);
//        System.out.println(allRows);
//        System.out.println("--------------------------");
//        
//        return allRows;
//    }
    
    public Genre getLatestGenre(Collection<Genre> genres) {
        return genres
            .stream()
            .max(Comparator.comparingInt(Genre::getId))
            .get();
    }
}
