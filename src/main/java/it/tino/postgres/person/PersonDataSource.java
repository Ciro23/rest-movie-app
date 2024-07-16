package it.tino.postgres.person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import it.tino.postgres.database.DatabaseTable;

public class PersonDataSource implements PersonRepository {

    private final DatabaseTable<Person> database;
    private final Function<ResultSet, Person> onMapEntity;
    
    public PersonDataSource(DatabaseTable<Person> database) {
        this.database = database;
        onMapEntity = (resultSet) -> {
            try {
                return new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("birth"),
                        Person.Gender.fromId(resultSet.getString("gender"))
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    @Override
    public int save(Person person) {
        boolean shouldUpdate = person.getId() != 0;
        BiConsumer<Person, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getName());
                stmt.setDate(++index, entity.getBirth());
                stmt.setString(++index, String.valueOf(entity.getGender().getId()));
                
                // Used in the WHERE clause to select the row to modify.
                if (shouldUpdate) {
                    stmt.setInt(++index, entity.getId());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        };
        
        String query = "insert into people (name, birth, gender) values (?, ?, ?::gender)";
        if (shouldUpdate) {
            query = "update people set name = ?, birth = ?, gender = ?::gender where id = ?";
        }
        
        return database.insertOrUpdate(
                person,
                query,
                onSetParameters
        );
    }
    
    @Override
    public List<Person> findAll() {
       return database.select("select * from people order by id", null, onMapEntity);
    }

    @Override
    public List<Person> findDirectorsByMovieId(int movieId) {
        String sql = "SELECT * FROM people p " +
                "JOIN movies_directors md ON p.id = md.director_id " +
                "WHERE md.movie_id = ?";
        
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
    public List<ActorRole> findActorsByMovieId(int movieId) {
        String sql = "SELECT p.*, ma.role, ma.cast_order FROM people p " +
                "JOIN movies_actors ma ON p.id = ma.actor_id " +
                "WHERE ma.movie_id = ?";
        
        Consumer<PreparedStatement> onSetParameters = (stmt) -> {
            try {
                stmt.setInt(1, movieId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        
        Function<ResultSet, Person> onMapActorRole = (resultSet) -> {
            Person person = onMapEntity.apply(resultSet);
            try {
                return new ActorRole(
                        person.getId(),
                        person.getName(),
                        person.getBirth(),
                        person.getGender(),
                        movieId,
                        resultSet.getString("role"),
                        resultSet.getInt("cast_order")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        
        return database.select(sql, onSetParameters, onMapActorRole)
                .stream()
                .map(p -> (ActorRole) p)
                .toList();
    }

    @Override
    public int delete(Person person) {
        return database.delete("delete from people where id = ?", (stmt) -> {
            try {
                stmt.setInt(1, person.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
