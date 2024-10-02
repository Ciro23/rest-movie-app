package it.tino.restmovieapp.mybatis.mapper;

import static it.tino.restmovieapp.mybatis.mapper.UserDbDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import it.tino.restmovieapp.mybatis.model.UserDb;
import jakarta.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface UserDbMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.837984Z", comments="Source Table: users")
    BasicColumn[] selectList = BasicColumn.columnList(id, username, password, email);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.827442Z", comments="Source Table: users")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="row.id")
    int insert(InsertStatementProvider<UserDb> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.830695Z", comments="Source Table: users")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultipleWithGeneratedKeys")
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<UserDb> records);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.83162Z", comments="Source Table: users")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserDbResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR)
    })
    List<UserDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.832658Z", comments="Source Table: users")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserDbResult")
    Optional<UserDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.833143Z", comments="Source Table: users")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.83363Z", comments="Source Table: users")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.834089Z", comments="Source Table: users")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.834564Z", comments="Source Table: users")
    default int insert(UserDb row) {
        return MyBatis3Utils.insert(this::insert, row, userDb, c ->
            c.map(username).toProperty("username")
            .map(password).toProperty("password")
            .map(email).toProperty("email")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.835472Z", comments="Source Table: users")
    default int insertMultiple(Collection<UserDb> records) {
        return MyBatis3Utils.insertMultipleWithGeneratedKeys(this::insertMultiple, records, userDb, c ->
            c.map(username).toProperty("username")
            .map(password).toProperty("password")
            .map(email).toProperty("email")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.836131Z", comments="Source Table: users")
    default int insertSelective(UserDb row) {
        return MyBatis3Utils.insert(this::insert, row, userDb, c ->
            c.map(username).toPropertyWhenPresent("username", row::getUsername)
            .map(password).toPropertyWhenPresent("password", row::getPassword)
            .map(email).toPropertyWhenPresent("email", row::getEmail)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.838689Z", comments="Source Table: users")
    default Optional<UserDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.839016Z", comments="Source Table: users")
    default List<UserDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.839318Z", comments="Source Table: users")
    default List<UserDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.83973Z", comments="Source Table: users")
    default Optional<UserDb> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.840051Z", comments="Source Table: users")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.840387Z", comments="Source Table: users")
    static UpdateDSL<UpdateModel> updateAllColumns(UserDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(username).equalTo(row::getUsername)
                .set(password).equalTo(row::getPassword)
                .set(email).equalTo(row::getEmail);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.840788Z", comments="Source Table: users")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(username).equalToWhenPresent(row::getUsername)
                .set(password).equalToWhenPresent(row::getPassword)
                .set(email).equalToWhenPresent(row::getEmail);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.841372Z", comments="Source Table: users")
    default int updateByPrimaryKey(UserDb row) {
        return update(c ->
            c.set(username).equalTo(row::getUsername)
            .set(password).equalTo(row::getPassword)
            .set(email).equalTo(row::getEmail)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.865503Z", comments="Source Table: users")
    default int updateByPrimaryKeySelective(UserDb row) {
        return update(c ->
            c.set(username).equalToWhenPresent(row::getUsername)
            .set(password).equalToWhenPresent(row::getPassword)
            .set(email).equalToWhenPresent(row::getEmail)
            .where(id, isEqualTo(row::getId))
        );
    }
}