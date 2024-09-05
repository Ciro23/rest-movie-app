package it.tino.postgres.mybatis.mapper;

import static it.tino.postgres.mybatis.mapper.UserDbDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import it.tino.postgres.mybatis.model.UserDb;
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
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.429875453+02:00", comments="Source Table: users")
    BasicColumn[] selectList = BasicColumn.columnList(id, username, password);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.424242054+02:00", comments="Source Table: users")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="row.id")
    int insert(InsertStatementProvider<UserDb> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.426097576+02:00", comments="Source Table: users")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultipleWithGeneratedKeys")
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<UserDb> records);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.426557561+02:00", comments="Source Table: users")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserDbResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    List<UserDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.427131669+02:00", comments="Source Table: users")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserDbResult")
    Optional<UserDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.427404547+02:00", comments="Source Table: users")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.427658219+02:00", comments="Source Table: users")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.427901752+02:00", comments="Source Table: users")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.428178296+02:00", comments="Source Table: users")
    default int insert(UserDb row) {
        return MyBatis3Utils.insert(this::insert, row, userDb, c ->
            c.map(username).toProperty("username")
            .map(password).toProperty("password")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.428810362+02:00", comments="Source Table: users")
    default int insertMultiple(Collection<UserDb> records) {
        return MyBatis3Utils.insertMultipleWithGeneratedKeys(this::insertMultiple, records, userDb, c ->
            c.map(username).toProperty("username")
            .map(password).toProperty("password")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.429230503+02:00", comments="Source Table: users")
    default int insertSelective(UserDb row) {
        return MyBatis3Utils.insert(this::insert, row, userDb, c ->
            c.map(username).toPropertyWhenPresent("username", row::getUsername)
            .map(password).toPropertyWhenPresent("password", row::getPassword)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.430331521+02:00", comments="Source Table: users")
    default Optional<UserDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.43056205+02:00", comments="Source Table: users")
    default List<UserDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.430782951+02:00", comments="Source Table: users")
    default List<UserDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.431003992+02:00", comments="Source Table: users")
    default Optional<UserDb> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.431221918+02:00", comments="Source Table: users")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.431507509+02:00", comments="Source Table: users")
    static UpdateDSL<UpdateModel> updateAllColumns(UserDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(username).equalTo(row::getUsername)
                .set(password).equalTo(row::getPassword);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.4318092+02:00", comments="Source Table: users")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(username).equalToWhenPresent(row::getUsername)
                .set(password).equalToWhenPresent(row::getPassword);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.432273293+02:00", comments="Source Table: users")
    default int updateByPrimaryKey(UserDb row) {
        return update(c ->
            c.set(username).equalTo(row::getUsername)
            .set(password).equalTo(row::getPassword)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.432581316+02:00", comments="Source Table: users")
    default int updateByPrimaryKeySelective(UserDb row) {
        return update(c ->
            c.set(username).equalToWhenPresent(row::getUsername)
            .set(password).equalToWhenPresent(row::getPassword)
            .where(id, isEqualTo(row::getId))
        );
    }
}