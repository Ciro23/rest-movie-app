package it.tino.postgres.genre;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import it.tino.postgres.database.RepositoryTester;

public class GenreRepositoryTester extends RepositoryTester<Genre, Integer> {

    public GenreRepositoryTester(GenreRepository repository) {
        super(repository);
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
    
    public List<Genre> getGenresOfMovie() {
        int movieId = 1;
        System.out.println("------- SELECT GENRES OF MOVIE -------");
        List<Genre> allRows = ((GenreRepository) repository).findByMovieId(movieId);
        System.out.println("Movie id: " + movieId);
        System.out.println(allRows);
        System.out.println("--------------------------");
        
        return allRows;
    }
    
    public Genre getLatestGenre(Collection<Genre> genres) {
        return genres
            .stream()
            .max(Comparator.comparingInt(Genre::getId))
            .get();
    }
}
