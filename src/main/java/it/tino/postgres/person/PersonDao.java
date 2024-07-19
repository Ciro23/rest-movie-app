package it.tino.postgres.person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BiConsumer;
import java.util.function.Function;

import it.tino.postgres.MovieAppException;
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
            	Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setBirth(resultSet.getDate("birth"));
                person.setGender(Person.Gender.fromId(resultSet.getString("gender")));
                
                return person;
            } catch (SQLException e) {
            	logger.error(e);
            	throw new MovieAppException(e);
            }
        };
	}

	@Override
	public Person insert(Person person) {
		String query = "insert into people (name, birth, gender)"
				+ " values (?, ?, ?::gender)";
		
		BiConsumer<Person, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getName());
                stmt.setDate(++index, entity.getBirth());
                stmt.setString(++index, String.valueOf(entity.getGender().getId()));
            } catch (SQLException e) {
            	logger.error(e);
            	throw new MovieAppException(e);
            }
        };
		
		return insert(person, query, onSetParameters);
	}

	@Override
	public Person update(Person person) {
		String query = "update people set name = ?, birth = ?,"
				+ " gender = ?::gender where id = ?";
		
		BiConsumer<Person, PreparedStatement> onSetParameters = (entity, stmt) -> {
            int index = 0;
            try {
                stmt.setString(++index, entity.getName());
                stmt.setDate(++index, entity.getBirth());
                stmt.setString(++index, String.valueOf(entity.getGender().getId()));
                stmt.setInt(++index, entity.getId());
            } catch (SQLException e) {
            	logger.error(e);
            	throw new MovieAppException(e);
            }
        };
		
		return update(person, query, onSetParameters);
	}
}
