package it.tino.postgres.movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import it.tino.postgres.database.DatabaseTable;

public class MovieDataSource implements MovieRepository {
    
    private final DatabaseTable<Movie> database;
    private final Function<ResultSet, Movie> onMapEntity;

    public MovieDataSource(DatabaseTable<Movie> database) {
        this.database = database;
        onMapEntity = (resultSet) -> {
            try {
                return new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getDate("release_date"),
                        resultSet.getInt("budget"),
                        resultSet.getInt("box_office"),
                        resultSet.getInt("runtime"),
                        resultSet.getString("overview")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    @Override
    public int save(Movie movie) {
        boolean shouldUpdate = movie.getId() != 0;
        BiConsumer<Movie, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getTitle());
                stmt.setDate(++index, entity.getReleaseDate());
                stmt.setInt(++index, entity.getBudget());
                stmt.setInt(++index, entity.getBoxOffice());
                stmt.setInt(++index, entity.getRuntime());
                stmt.setString(++index, entity.getOverview());
                
                // Used in the WHERE clause to select the row to modify.
                if (shouldUpdate) {
                    stmt.setInt(++index, entity.getId());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        };
        
        String query = "insert into movies (title, release_date, budget, box_office, runtime, overview)"
                + " values (?, ?, ?, ?, ?, ?)";
        if (shouldUpdate) {
            query = "update movies set title = ?, release_date = ?, budget = ?, box_office = ?,"
                    + " runtime = ?, overview = ? where id = ?";
        }
        
        return database.insertOrUpdate(
                movie,
                query,
                onSetParameters
        );
    }
    
    @Override
    public List<Movie> findAll() {
       return database.select("select * from movies order by id", null, onMapEntity); 
    }
    
    @Override
    public int delete(Movie movie) {
        return database.delete("delete from movies where id = ?", (stmt) -> {
            try {
                stmt.setInt(1, movie.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
