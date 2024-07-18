package it.tino.postgres.person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiConsumer;
import java.util.function.Function;

import it.tino.postgres.database.Dao;
import it.tino.postgres.database.Database;
import it.tino.postgres.database.SimpleDao;

public class PersonDao extends SimpleDao<Person, Integer> implements Dao<Person, Integer> {

	public PersonDao(Database database) {
		super(database);
	}
	
	@Override
	protected String getTableName() {
		return "people";
	}

	@Override
	protected Function<ResultSet, Person> getOnMapEntity() {
		return (resultSet) -> {
            try {
                return new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("birth"),
                        Person.Gender.fromId(resultSet.getString("gender"))
                );
            } catch (SQLException e) {
            	logger.error(e);
            	throw new RuntimeException(e);
            }
        };
	}

	@Override
	public Person insert(Person person) {
		String query = "insert into people (name, birth, gender) values (?, ?, ?::gender)";
		
		BiConsumer<Person, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getName());
                stmt.setDate(++index, entity.getBirth());
                stmt.setString(++index, String.valueOf(entity.getGender().getId()));
            } catch (Exception e) {
            	logger.error(e);
            	throw new RuntimeException(e);
            }
        };
		
		return insert(person, query, onSetParameters);
	}

	@Override
	public Person update(Person person) {
		String query = "update people set name = ?, birth = ?, gender = ?::gender where id = ?";
		
		BiConsumer<Person, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getName());
                stmt.setDate(++index, entity.getBirth());
                stmt.setString(++index, String.valueOf(entity.getGender().getId()));
                stmt.setInt(++index, entity.getId());
            } catch (Exception e) {
            	logger.error(e);
            	throw new RuntimeException(e);
            }
        };
		
		return update(person, query, onSetParameters);
	}
}
