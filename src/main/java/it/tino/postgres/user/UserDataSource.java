package it.tino.postgres.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import it.tino.postgres.database.DatabaseTable;

public class UserDataSource implements UserRepository {

    private final DatabaseTable<User> database;
    private final Function<ResultSet, User> onMapEntity;
    
    public UserDataSource(DatabaseTable<User> database) {
        this.database = database;
        onMapEntity = (resultSet) -> {
            try {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
    }
    
    @Override
    public int save(User user) {
        boolean shouldUpdate = user.getId() != 0;
        BiConsumer<User, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getUsername());
                stmt.setString(++index, entity.getPassword());
                
                // Used in the WHERE clause to select the row to modify.
                if (shouldUpdate) {
                    stmt.setInt(++index, entity.getId());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        };
        
        String query = "insert into users (username, password) values (?, ?)";
        if (shouldUpdate) {
            query = "update users set username = ?, password = ? where id = ?";
        }
        
        return database.insertOrUpdate(
                user,
                query,
                onSetParameters
        );
    }
    
    @Override
    public List<User> findAll() {
       return database.select("select * from users order by id", null, onMapEntity);
    }
    
    @Override
    public int delete(User user) {
        return database.delete("delete from users where id = ?", (stmt) -> {
            try {
                stmt.setInt(1, user.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
