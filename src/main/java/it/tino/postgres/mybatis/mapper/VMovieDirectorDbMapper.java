package it.tino.postgres.mybatis.mapper;

import static it.tino.postgres.mybatis.mapper.VMovieDirectorDbDynamicSqlSupport.*;

import it.tino.postgres.mybatis.model.VMovieDirectorDb;
import jakarta.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface VMovieDirectorDbMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<VMovieDirectorDb>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447470091+02:00", comments="Source Table: v_movies_directors")
    BasicColumn[] selectList = BasicColumn.columnList(directorId, name, birth, gender, movieId, title, releaseDate, budget, boxOffice, runtime, overview);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446901283+02:00", comments="Source Table: v_movies_directors")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="VMovieDirectorDbResult", value = {
        @Result(column="director_id", property="directorId", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="birth", property="birth", jdbcType=JdbcType.DATE),
        @Result(column="gender", property="gender", jdbcType=JdbcType.CHAR),
        @Result(column="movie_id", property="movieId", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_date", property="releaseDate", jdbcType=JdbcType.DATE),
        @Result(column="budget", property="budget", jdbcType=JdbcType.INTEGER),
        @Result(column="box_office", property="boxOffice", jdbcType=JdbcType.INTEGER),
        @Result(column="runtime", property="runtime", jdbcType=JdbcType.INTEGER),
        @Result(column="overview", property="overview", jdbcType=JdbcType.VARCHAR)
    })
    List<VMovieDirectorDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446981573+02:00", comments="Source Table: v_movies_directors")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("VMovieDirectorDbResult")
    Optional<VMovieDirectorDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447032918+02:00", comments="Source Table: v_movies_directors")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, VMovieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447090125+02:00", comments="Source Table: v_movies_directors")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, VMovieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447137964+02:00", comments="Source Table: v_movies_directors")
    default int insert(VMovieDirectorDb row) {
        return MyBatis3Utils.insert(this::insert, row, VMovieDirectorDb, c ->
            c.map(directorId).toProperty("directorId")
            .map(name).toProperty("name")
            .map(birth).toProperty("birth")
            .map(gender).toProperty("gender")
            .map(movieId).toProperty("movieId")
            .map(title).toProperty("title")
            .map(releaseDate).toProperty("releaseDate")
            .map(budget).toProperty("budget")
            .map(boxOffice).toProperty("boxOffice")
            .map(runtime).toProperty("runtime")
            .map(overview).toProperty("overview")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447223483+02:00", comments="Source Table: v_movies_directors")
    default int insertMultiple(Collection<VMovieDirectorDb> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, VMovieDirectorDb, c ->
            c.map(directorId).toProperty("directorId")
            .map(name).toProperty("name")
            .map(birth).toProperty("birth")
            .map(gender).toProperty("gender")
            .map(movieId).toProperty("movieId")
            .map(title).toProperty("title")
            .map(releaseDate).toProperty("releaseDate")
            .map(budget).toProperty("budget")
            .map(boxOffice).toProperty("boxOffice")
            .map(runtime).toProperty("runtime")
            .map(overview).toProperty("overview")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447315003+02:00", comments="Source Table: v_movies_directors")
    default int insertSelective(VMovieDirectorDb row) {
        return MyBatis3Utils.insert(this::insert, row, VMovieDirectorDb, c ->
            c.map(directorId).toPropertyWhenPresent("directorId", row::getDirectorId)
            .map(name).toPropertyWhenPresent("name", row::getName)
            .map(birth).toPropertyWhenPresent("birth", row::getBirth)
            .map(gender).toPropertyWhenPresent("gender", row::getGender)
            .map(movieId).toPropertyWhenPresent("movieId", row::getMovieId)
            .map(title).toPropertyWhenPresent("title", row::getTitle)
            .map(releaseDate).toPropertyWhenPresent("releaseDate", row::getReleaseDate)
            .map(budget).toPropertyWhenPresent("budget", row::getBudget)
            .map(boxOffice).toPropertyWhenPresent("boxOffice", row::getBoxOffice)
            .map(runtime).toPropertyWhenPresent("runtime", row::getRuntime)
            .map(overview).toPropertyWhenPresent("overview", row::getOverview)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447505898+02:00", comments="Source Table: v_movies_directors")
    default Optional<VMovieDirectorDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, VMovieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447539761+02:00", comments="Source Table: v_movies_directors")
    default List<VMovieDirectorDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, VMovieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447577211+02:00", comments="Source Table: v_movies_directors")
    default List<VMovieDirectorDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, VMovieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447610372+02:00", comments="Source Table: v_movies_directors")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, VMovieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447645207+02:00", comments="Source Table: v_movies_directors")
    static UpdateDSL<UpdateModel> updateAllColumns(VMovieDirectorDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(directorId).equalTo(row::getDirectorId)
                .set(name).equalTo(row::getName)
                .set(birth).equalTo(row::getBirth)
                .set(gender).equalTo(row::getGender)
                .set(movieId).equalTo(row::getMovieId)
                .set(title).equalTo(row::getTitle)
                .set(releaseDate).equalTo(row::getReleaseDate)
                .set(budget).equalTo(row::getBudget)
                .set(boxOffice).equalTo(row::getBoxOffice)
                .set(runtime).equalTo(row::getRuntime)
                .set(overview).equalTo(row::getOverview);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.447724925+02:00", comments="Source Table: v_movies_directors")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(VMovieDirectorDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(directorId).equalToWhenPresent(row::getDirectorId)
                .set(name).equalToWhenPresent(row::getName)
                .set(birth).equalToWhenPresent(row::getBirth)
                .set(gender).equalToWhenPresent(row::getGender)
                .set(movieId).equalToWhenPresent(row::getMovieId)
                .set(title).equalToWhenPresent(row::getTitle)
                .set(releaseDate).equalToWhenPresent(row::getReleaseDate)
                .set(budget).equalToWhenPresent(row::getBudget)
                .set(boxOffice).equalToWhenPresent(row::getBoxOffice)
                .set(runtime).equalToWhenPresent(row::getRuntime)
                .set(overview).equalToWhenPresent(row::getOverview);
    }
}