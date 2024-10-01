package it.tino.restmovieapp.mybatis.mapper;

import static it.tino.restmovieapp.mybatis.mapper.GenreDbDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import it.tino.restmovieapp.mybatis.model.GenreDb;
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
public interface GenreDbMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.635244001+02:00", comments="Source Table: genres")
    BasicColumn[] selectList = BasicColumn.columnList(id, name);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.63461401+02:00", comments="Source Table: genres")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="row.id")
    int insert(InsertStatementProvider<GenreDb> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634693267+02:00", comments="Source Table: genres")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultipleWithGeneratedKeys")
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<GenreDb> records);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634749702+02:00", comments="Source Table: genres")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="GenreDbResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.OTHER)
    })
    List<GenreDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634814262+02:00", comments="Source Table: genres")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("GenreDbResult")
    Optional<GenreDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634865658+02:00", comments="Source Table: genres")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, genreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634958971+02:00", comments="Source Table: genres")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, genreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.635015987+02:00", comments="Source Table: genres")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.635070378+02:00", comments="Source Table: genres")
    default int insert(GenreDb row) {
        return MyBatis3Utils.insert(this::insert, row, genreDb, c ->
            c.map(name).toProperty("name")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.635116915+02:00", comments="Source Table: genres")
    default int insertMultiple(Collection<GenreDb> records) {
        return MyBatis3Utils.insertMultipleWithGeneratedKeys(this::insertMultiple, records, genreDb, c ->
            c.map(name).toProperty("name")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.635171336+02:00", comments="Source Table: genres")
    default int insertSelective(GenreDb row) {
        return MyBatis3Utils.insert(this::insert, row, genreDb, c ->
            c.map(name).toPropertyWhenPresent("name", row::getName)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.635275179+02:00", comments="Source Table: genres")
    default Optional<GenreDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, genreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.6353136+02:00", comments="Source Table: genres")
    default List<GenreDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, genreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.635346141+02:00", comments="Source Table: genres")
    default List<GenreDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, genreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.63537809+02:00", comments="Source Table: genres")
    default Optional<GenreDb> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.635422393+02:00", comments="Source Table: genres")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, genreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.635467697+02:00", comments="Source Table: genres")
    static UpdateDSL<UpdateModel> updateAllColumns(GenreDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalTo(row::getName);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.635515516+02:00", comments="Source Table: genres")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(GenreDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(name).equalToWhenPresent(row::getName);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.635563856+02:00", comments="Source Table: genres")
    default int updateByPrimaryKey(GenreDb row) {
        return update(c ->
            c.set(name).equalTo(row::getName)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.635615602+02:00", comments="Source Table: genres")
    default int updateByPrimaryKeySelective(GenreDb row) {
        return update(c ->
            c.set(name).equalToWhenPresent(row::getName)
            .where(id, isEqualTo(row::getId))
        );
    }
}