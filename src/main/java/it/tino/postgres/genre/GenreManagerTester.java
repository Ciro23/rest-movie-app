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
		return genreManager::selectAll;
	}

	@Override
	protected Function<Genre, Genre> onInsert() {
		return genreManager::insert;
	}

	@Override
	protected Function<Genre, Genre> onUpdate() {
		return genreManager::update;
	}

	@Override
	protected Function<Integer, Boolean> onDelete() {
		return genreManager::delete;
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
    
    public Genre getLatestGenre(Collection<Genre> genres) {
        return genres
            .stream()
            .max(Comparator.comparingInt(Genre::getId))
            .get();
    }
}
