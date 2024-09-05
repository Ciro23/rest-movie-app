package it.tino.postgres.genre;

import it.tino.postgres.ObjectMapper;
import it.tino.postgres.mybatis.model.GenreDb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenreMapper implements ObjectMapper<Genre, GenreDb> {

    @Override
    public List<Genre> dbToDomain(Collection<GenreDb> dbEntities) {
        List<Genre> genres = new ArrayList<>();
        for (GenreDb dbEntity : dbEntities) {
            Genre genre = new Genre();
            genre.setId(dbEntity.getId());
            genre.setName((String) dbEntity.getName());
            genres.add(genre);
        }

        return genres;
    }

    @Override
    public List<GenreDb> domainToDb(Collection<Genre> domainEntities) {
        List<GenreDb> genresDb = new ArrayList<>();
        for (Genre domainEntity : domainEntities) {
            GenreDb genre = new GenreDb();
            genre.setId(domainEntity.getId());
            genre.setName(domainEntity.getName());
            genresDb.add(genre);
        }

        return genresDb;
    }
}
