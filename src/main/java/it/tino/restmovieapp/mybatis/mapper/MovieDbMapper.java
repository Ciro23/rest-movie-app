package it.tino.restmovieapp.mybatis.mapper;

import static it.tino.restmovieapp.mybatis.mapper.MovieDbDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import it.tino.restmovieapp.mybatis.model.MovieDb;
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
public interface MovieDbMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639010703+02:00", comments="Source Table: movies")
    BasicColumn[] selectList = BasicColumn.columnList(id, title, releaseDate, budget, boxOffice, runtime, overview);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.638449099+02:00", comments="Source Table: movies")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="row.id")
    int insert(InsertStatementProvider<MovieDb> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.638512136+02:00", comments="Source Table: movies")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultipleWithGeneratedKeys")
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<MovieDb> records);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.638567659+02:00", comments="Source Table: movies")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MovieDbResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_date", property="releaseDate", jdbcType=JdbcType.DATE),
        @Result(column="budget", property="budget", jdbcType=JdbcType.INTEGER),
        @Result(column="box_office", property="boxOffice", jdbcType=JdbcType.INTEGER),
        @Result(column="runtime", property="runtime", jdbcType=JdbcType.INTEGER),
        @Result(column="overview", property="overview", jdbcType=JdbcType.VARCHAR)
    })
    List<MovieDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.63866461+02:00", comments="Source Table: movies")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MovieDbResult")
    Optional<MovieDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.638704193+02:00", comments="Source Table: movies")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, movieDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.63875071+02:00", comments="Source Table: movies")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, movieDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.638788229+02:00", comments="Source Table: movies")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.638819027+02:00", comments="Source Table: movies")
    default int insert(MovieDb row) {
        return MyBatis3Utils.insert(this::insert, row, movieDb, c ->
            c.map(title).toProperty("title")
            .map(releaseDate).toProperty("releaseDate")
            .map(budget).toProperty("budget")
            .map(boxOffice).toProperty("boxOffice")
            .map(runtime).toProperty("runtime")
            .map(overview).toProperty("overview")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.638876493+02:00", comments="Source Table: movies")
    default int insertMultiple(Collection<MovieDb> records) {
        return MyBatis3Utils.insertMultipleWithGeneratedKeys(this::insertMultiple, records, movieDb, c ->
            c.map(title).toProperty("title")
            .map(releaseDate).toProperty("releaseDate")
            .map(budget).toProperty("budget")
            .map(boxOffice).toProperty("boxOffice")
            .map(runtime).toProperty("runtime")
            .map(overview).toProperty("overview")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.638925825+02:00", comments="Source Table: movies")
    default int insertSelective(MovieDb row) {
        return MyBatis3Utils.insert(this::insert, row, movieDb, c ->
            c.map(title).toPropertyWhenPresent("title", row::getTitle)
            .map(releaseDate).toPropertyWhenPresent("releaseDate", row::getReleaseDate)
            .map(budget).toPropertyWhenPresent("budget", row::getBudget)
            .map(boxOffice).toPropertyWhenPresent("boxOffice", row::getBoxOffice)
            .map(runtime).toPropertyWhenPresent("runtime", row::getRuntime)
            .map(overview).toPropertyWhenPresent("overview", row::getOverview)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639041971+02:00", comments="Source Table: movies")
    default Optional<MovieDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, movieDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639092796+02:00", comments="Source Table: movies")
    default List<MovieDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, movieDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639123673+02:00", comments="Source Table: movies")
    default List<MovieDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, movieDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.6391542+02:00", comments="Source Table: movies")
    default Optional<MovieDb> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639189796+02:00", comments="Source Table: movies")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, movieDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639227887+02:00", comments="Source Table: movies")
    static UpdateDSL<UpdateModel> updateAllColumns(MovieDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(title).equalTo(row::getTitle)
                .set(releaseDate).equalTo(row::getReleaseDate)
                .set(budget).equalTo(row::getBudget)
                .set(boxOffice).equalTo(row::getBoxOffice)
                .set(runtime).equalTo(row::getRuntime)
                .set(overview).equalTo(row::getOverview);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639291375+02:00", comments="Source Table: movies")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(MovieDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(title).equalToWhenPresent(row::getTitle)
                .set(releaseDate).equalToWhenPresent(row::getReleaseDate)
                .set(budget).equalToWhenPresent(row::getBudget)
                .set(boxOffice).equalToWhenPresent(row::getBoxOffice)
                .set(runtime).equalToWhenPresent(row::getRuntime)
                .set(overview).equalToWhenPresent(row::getOverview);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639353049+02:00", comments="Source Table: movies")
    default int updateByPrimaryKey(MovieDb row) {
        return update(c ->
            c.set(title).equalTo(row::getTitle)
            .set(releaseDate).equalTo(row::getReleaseDate)
            .set(budget).equalTo(row::getBudget)
            .set(boxOffice).equalTo(row::getBoxOffice)
            .set(runtime).equalTo(row::getRuntime)
            .set(overview).equalTo(row::getOverview)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639413592+02:00", comments="Source Table: movies")
    default int updateByPrimaryKeySelective(MovieDb row) {
        return update(c ->
            c.set(title).equalToWhenPresent(row::getTitle)
            .set(releaseDate).equalToWhenPresent(row::getReleaseDate)
            .set(budget).equalToWhenPresent(row::getBudget)
            .set(boxOffice).equalToWhenPresent(row::getBoxOffice)
            .set(runtime).equalToWhenPresent(row::getRuntime)
            .set(overview).equalToWhenPresent(row::getOverview)
            .where(id, isEqualTo(row::getId))
        );
    }
}