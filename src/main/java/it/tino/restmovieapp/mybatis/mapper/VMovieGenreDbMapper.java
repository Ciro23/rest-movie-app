package it.tino.restmovieapp.mybatis.mapper;

import static it.tino.restmovieapp.mybatis.mapper.VMovieGenreDbDynamicSqlSupport.*;

import it.tino.restmovieapp.mybatis.model.VMovieGenreDb;
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
public interface VMovieGenreDbMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<VMovieGenreDb>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.451638756+02:00", comments="Source Table: v_movies_genres")
    BasicColumn[] selectList = BasicColumn.columnList(genreId, name, movieId, title, releaseDate, budget, boxOffice, runtime, overview);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.451181645+02:00", comments="Source Table: v_movies_genres")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="VMovieGenreDbResult", value = {
        @Result(column="genre_id", property="genreId", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.OTHER),
        @Result(column="movie_id", property="movieId", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_date", property="releaseDate", jdbcType=JdbcType.DATE),
        @Result(column="budget", property="budget", jdbcType=JdbcType.INTEGER),
        @Result(column="box_office", property="boxOffice", jdbcType=JdbcType.INTEGER),
        @Result(column="runtime", property="runtime", jdbcType=JdbcType.INTEGER),
        @Result(column="overview", property="overview", jdbcType=JdbcType.VARCHAR)
    })
    List<VMovieGenreDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.451263127+02:00", comments="Source Table: v_movies_genres")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("VMovieGenreDbResult")
    Optional<VMovieGenreDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.45129705+02:00", comments="Source Table: v_movies_genres")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, VMovieGenreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.451346853+02:00", comments="Source Table: v_movies_genres")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, VMovieGenreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.451379854+02:00", comments="Source Table: v_movies_genres")
    default int insert(VMovieGenreDb row) {
        return MyBatis3Utils.insert(this::insert, row, VMovieGenreDb, c ->
            c.map(genreId).toProperty("genreId")
            .map(name).toProperty("name")
            .map(movieId).toProperty("movieId")
            .map(title).toProperty("title")
            .map(releaseDate).toProperty("releaseDate")
            .map(budget).toProperty("budget")
            .map(boxOffice).toProperty("boxOffice")
            .map(runtime).toProperty("runtime")
            .map(overview).toProperty("overview")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.451455565+02:00", comments="Source Table: v_movies_genres")
    default int insertMultiple(Collection<VMovieGenreDb> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, VMovieGenreDb, c ->
            c.map(genreId).toProperty("genreId")
            .map(name).toProperty("name")
            .map(movieId).toProperty("movieId")
            .map(title).toProperty("title")
            .map(releaseDate).toProperty("releaseDate")
            .map(budget).toProperty("budget")
            .map(boxOffice).toProperty("boxOffice")
            .map(runtime).toProperty("runtime")
            .map(overview).toProperty("overview")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.451535714+02:00", comments="Source Table: v_movies_genres")
    default int insertSelective(VMovieGenreDb row) {
        return MyBatis3Utils.insert(this::insert, row, VMovieGenreDb, c ->
            c.map(genreId).toPropertyWhenPresent("genreId", row::getGenreId)
            .map(name).toPropertyWhenPresent("name", row::getName)
            .map(movieId).toPropertyWhenPresent("movieId", row::getMovieId)
            .map(title).toPropertyWhenPresent("title", row::getTitle)
            .map(releaseDate).toPropertyWhenPresent("releaseDate", row::getReleaseDate)
            .map(budget).toPropertyWhenPresent("budget", row::getBudget)
            .map(boxOffice).toPropertyWhenPresent("boxOffice", row::getBoxOffice)
            .map(runtime).toPropertyWhenPresent("runtime", row::getRuntime)
            .map(overview).toPropertyWhenPresent("overview", row::getOverview)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.451666317+02:00", comments="Source Table: v_movies_genres")
    default Optional<VMovieGenreDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, VMovieGenreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.451696974+02:00", comments="Source Table: v_movies_genres")
    default List<VMovieGenreDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, VMovieGenreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.451732059+02:00", comments="Source Table: v_movies_genres")
    default List<VMovieGenreDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, VMovieGenreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.451766423+02:00", comments="Source Table: v_movies_genres")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, VMovieGenreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.451796469+02:00", comments="Source Table: v_movies_genres")
    static UpdateDSL<UpdateModel> updateAllColumns(VMovieGenreDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(genreId).equalTo(row::getGenreId)
                .set(name).equalTo(row::getName)
                .set(movieId).equalTo(row::getMovieId)
                .set(title).equalTo(row::getTitle)
                .set(releaseDate).equalTo(row::getReleaseDate)
                .set(budget).equalTo(row::getBudget)
                .set(boxOffice).equalTo(row::getBoxOffice)
                .set(runtime).equalTo(row::getRuntime)
                .set(overview).equalTo(row::getOverview);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.451860588+02:00", comments="Source Table: v_movies_genres")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(VMovieGenreDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(genreId).equalToWhenPresent(row::getGenreId)
                .set(name).equalToWhenPresent(row::getName)
                .set(movieId).equalToWhenPresent(row::getMovieId)
                .set(title).equalToWhenPresent(row::getTitle)
                .set(releaseDate).equalToWhenPresent(row::getReleaseDate)
                .set(budget).equalToWhenPresent(row::getBudget)
                .set(boxOffice).equalToWhenPresent(row::getBoxOffice)
                .set(runtime).equalToWhenPresent(row::getRuntime)
                .set(overview).equalToWhenPresent(row::getOverview);
    }
}