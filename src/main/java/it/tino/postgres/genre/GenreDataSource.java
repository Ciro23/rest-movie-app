package it.tino.postgres.genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import it.tino.postgres.database.DatabaseTable;

public class GenreDataSource implements GenreRepository {

    private final DatabaseTable<Genre> database;
    private final Function<ResultSet, Genre> onMapEntity;
    
    public GenreDataSource(DatabaseTable<Genre> database) {
        this.database = database;
        onMapEntity = (resultSet) -> {
            try {
                return new Genre(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Override
    public int save(Genre genre) {
        boolean shouldUpdate = genre.getId() != 0;
        BiConsumer<Genre, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getName());
                
                // Used in the WHERE clause to select the row to modify.
                if (shouldUpdate) {
                    stmt.setInt(++index, entity.getId());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        };
        
        String query = "insert into genres (name) values (?)";
        if (shouldUpdate) {
            query = "update genres set name = ? where id = ?";
        }
        
        return database.insertOrUpdate(
                genre,
                query,
                onSetParameters
        );
    }

    @Override
    public List<Genre> findAll() {
        return database.select("select * from genres order by id", null, onMapEntity);
    }
    
    @Override
    public List<Genre> findByMovieId(int movieId) {
        String sql = "SELECT g.id, g.name FROM genres g"
                + " JOIN movies_genres mg ON g.id = mg.genre_id"
                + " WHERE mg.movie_id = ?";
        
        Consumer<PreparedStatement> onSetParameters = (stmt) -> {
            try {
                stmt.setInt(1, movieId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        
        return database.select(sql, onSetParameters, onMapEntity);
    }

    @Override
    public int delete(Genre entity) {
        return database.delete("delete from genres where id = ?", (stmt) -> {
            try {
                stmt.setInt(1, entity.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
