package it.tino.restmovieapp.user;

import it.tino.restmovieapp.SimpleManager;
import it.tino.restmovieapp.mybatis.mapper.UserDbMapper;
import it.tino.restmovieapp.mybatis.model.UserDb;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;

import java.util.List;

public class UserManager {

	private final SimpleManager<User, UserDb, Integer> simpleManager;

	public UserManager(SqlSessionFactory sqlSessionFactory, UserMapper userMapper) {
		SimpleManager.InsertFunction<UserDb> onInsert = (sqlSession, key) -> {
			UserDbMapper dao = sqlSession.getMapper(UserDbMapper.class);
			return dao.insert(key);
		};
		SimpleManager.UpdateFunction<UserDb> onUpdate = (sqlSession, key) -> {
			UserDbMapper dao = sqlSession.getMapper(UserDbMapper.class);
			return dao.updateByPrimaryKey(key);
		};
		SimpleManager.SelectFunction<UserDb> onSelect = (sqlSession, key) -> {
			UserDbMapper dao = sqlSession.getMapper(UserDbMapper.class);
			return dao.select(key);
		};
		SimpleManager.SelectByIdFunction<UserDb, Integer> onSelectById = (sqlSession, key) -> {
			UserDbMapper dao = sqlSession.getMapper(UserDbMapper.class);
			return dao.selectByPrimaryKey(key);
		};
		SimpleManager.DeleteFunction<Integer> onDelete = (sqlSession, key) -> {
			UserDbMapper dao = sqlSession.getMapper(UserDbMapper.class);
			return dao.deleteByPrimaryKey(key);
		};
		this.simpleManager = new SimpleManager<>(
				sqlSessionFactory,
				userMapper,
				onInsert,
				onUpdate,
				onSelect,
				onSelectById,
				onDelete
		);
	}

	public User insert(User user) {
		return simpleManager.insert(user);
	}
	
	public User update(User user) {
		return simpleManager.update(user);
	}

    public List<User> selectAll() {
		return simpleManager.selectAll();
	}

   	public User selectById(int id) {
		return simpleManager.selectById(id);
	}
    
    public List<User> selectByCriteria(SelectDSLCompleter selectDSLCompleter) {
		return simpleManager.selectByCriteria(selectDSLCompleter);
	}

	public boolean delete(int id) {
		return simpleManager.delete(id);
	}
}
