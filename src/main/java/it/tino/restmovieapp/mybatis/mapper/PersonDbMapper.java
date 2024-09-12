package it.tino.restmovieapp.mybatis.mapper;

import static it.tino.restmovieapp.mybatis.mapper.PersonDbDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import it.tino.restmovieapp.mybatis.model.PersonDb;
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
public interface PersonDbMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.258947377+02:00", comments="Source Table: people")
    BasicColumn[] selectList = BasicColumn.columnList(id, name, birth, gender);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.258393046+02:00", comments="Source Table: people")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="row.id")
    int insert(InsertStatementProvider<PersonDb> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.258454059+02:00", comments="Source Table: people")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultipleWithGeneratedKeys")
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<PersonDb> records);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.25850809+02:00", comments="Source Table: people")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="PersonDbResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="birth", property="birth", jdbcType=JdbcType.DATE),
        @Result(column="gender", property="gender", jdbcType=JdbcType.CHAR)
    })
    List<PersonDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.258579232+02:00", comments="Source Table: people")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("PersonDbResult")
    Optional<PersonDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.25862102+02:00", comments="Source Table: people")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, personDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.258660934+02:00", comments="Source Table: people")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, personDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.258693515+02:00", comments="Source Table: people")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.258764036+02:00", comments="Source Table: people")
    default int insert(PersonDb row) {
        return MyBatis3Utils.insert(this::insert, row, personDb, c ->
            c.map(name).toProperty("name")
            .map(birth).toProperty("birth")
            .map(gender).toProperty("gender")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.258810232+02:00", comments="Source Table: people")
    default int insertMultiple(Collection<PersonDb> records) {
        return MyBatis3Utils.insertMultipleWithGeneratedKeys(this::insertMultiple, records, personDb, c ->
            c.map(name).toProperty("name")
            .map(birth).toProperty("birth")
            .map(gender).toProperty("gender")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.258875333+02:00", comments="Source Table: people")
    default int insertSelective(PersonDb row) {
        return MyBatis3Utils.insert(this::insert, row, personDb, c ->
            c.map(name).toPropertyWhenPresent("name", row::getName)
            .map(birth).toPropertyWhenPresent("birth", row::getBirth)
            .map(gender).toPropertyWhenPresent("gender", row::getGender)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.258980539+02:00", comments="Source Table: people")
    default Optional<PersonDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, personDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259026955+02:00", comments="Source Table: people")
    default List<PersonDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, personDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259068202+02:00", comments="Source Table: people")
    default List<PersonDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, personDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.25909915+02:00", comments="Source Table: people")
    default Optional<PersonDb> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259140386+02:00", comments="Source Table: people")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, personDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259175902+02:00", comments="Source Table: people")
    static UpdateDSL<UpdateModel> updateAllColumns(PersonDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalTo(row::getName)
                .set(birth).equalTo(row::getBirth)
                .set(gender).equalTo(row::getGender);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.2592339+02:00", comments="Source Table: people")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(PersonDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalToWhenPresent(row::getName)
                .set(birth).equalToWhenPresent(row::getBirth)
                .set(gender).equalToWhenPresent(row::getGender);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259289814+02:00", comments="Source Table: people")
    default int updateByPrimaryKey(PersonDb row) {
        return update(c ->
            c.set(name).equalTo(row::getName)
            .set(birth).equalTo(row::getBirth)
            .set(gender).equalTo(row::getGender)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.259343755+02:00", comments="Source Table: people")
    default int updateByPrimaryKeySelective(PersonDb row) {
        return update(c ->
            c.set(name).equalToWhenPresent(row::getName)
            .set(birth).equalToWhenPresent(row::getBirth)
            .set(gender).equalToWhenPresent(row::getGender)
            .where(id, isEqualTo(row::getId))
        );
    }
}