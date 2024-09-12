package it.tino.restmovieapp.mybatis.mapper;

import static it.tino.restmovieapp.mybatis.mapper.VMovieActorDbDynamicSqlSupport.*;

import it.tino.restmovieapp.mybatis.model.VMovieActorDb;
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
public interface VMovieActorDbMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<VMovieActorDb>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.449810435+02:00", comments="Source Table: v_movies_actors")
    BasicColumn[] selectList = BasicColumn.columnList(actorId, name, birth, gender, movieId, title, releaseDate, budget, boxOffice, runtime, overview, role, castOrder);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.449265311+02:00", comments="Source Table: v_movies_actors")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="VMovieActorDbResult", value = {
        @Result(column="actor_id", property="actorId", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="birth", property="birth", jdbcType=JdbcType.DATE),
        @Result(column="gender", property="gender", jdbcType=JdbcType.CHAR),
        @Result(column="movie_id", property="movieId", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_date", property="releaseDate", jdbcType=JdbcType.DATE),
        @Result(column="budget", property="budget", jdbcType=JdbcType.INTEGER),
        @Result(column="box_office", property="boxOffice", jdbcType=JdbcType.INTEGER),
        @Result(column="runtime", property="runtime", jdbcType=JdbcType.INTEGER),
        @Result(column="overview", property="overview", jdbcType=JdbcType.VARCHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR),
        @Result(column="cast_order", property="castOrder", jdbcType=JdbcType.INTEGER)
    })
    List<VMovieActorDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.449381647+02:00", comments="Source Table: v_movies_actors")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("VMovieActorDbResult")
    Optional<VMovieActorDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.449425158+02:00", comments="Source Table: v_movies_actors")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, VMovieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.449473949+02:00", comments="Source Table: v_movies_actors")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, VMovieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.449511118+02:00", comments="Source Table: v_movies_actors")
    default int insert(VMovieActorDb row) {
        return MyBatis3Utils.insert(this::insert, row, VMovieActorDb, c ->
            c.map(actorId).toProperty("actorId")
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
            .map(role).toProperty("role")
            .map(castOrder).toProperty("castOrder")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.449599222+02:00", comments="Source Table: v_movies_actors")
    default int insertMultiple(Collection<VMovieActorDb> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, VMovieActorDb, c ->
            c.map(actorId).toProperty("actorId")
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
            .map(role).toProperty("role")
            .map(castOrder).toProperty("castOrder")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.449674502+02:00", comments="Source Table: v_movies_actors")
    default int insertSelective(VMovieActorDb row) {
        return MyBatis3Utils.insert(this::insert, row, VMovieActorDb, c ->
            c.map(actorId).toPropertyWhenPresent("actorId", row::getActorId)
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
            .map(role).toPropertyWhenPresent("role", row::getRole)
            .map(castOrder).toPropertyWhenPresent("castOrder", row::getCastOrder)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.449851541+02:00", comments="Source Table: v_movies_actors")
    default Optional<VMovieActorDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, VMovieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.449890684+02:00", comments="Source Table: v_movies_actors")
    default List<VMovieActorDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, VMovieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.449925308+02:00", comments="Source Table: v_movies_actors")
    default List<VMovieActorDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, VMovieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44996397+02:00", comments="Source Table: v_movies_actors")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, VMovieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.45000138+02:00", comments="Source Table: v_movies_actors")
    static UpdateDSL<UpdateModel> updateAllColumns(VMovieActorDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(actorId).equalTo(row::getActorId)
                .set(name).equalTo(row::getName)
                .set(birth).equalTo(row::getBirth)
                .set(gender).equalTo(row::getGender)
                .set(movieId).equalTo(row::getMovieId)
                .set(title).equalTo(row::getTitle)
                .set(releaseDate).equalTo(row::getReleaseDate)
                .set(budget).equalTo(row::getBudget)
                .set(boxOffice).equalTo(row::getBoxOffice)
                .set(runtime).equalTo(row::getRuntime)
                .set(overview).equalTo(row::getOverview)
                .set(role).equalTo(row::getRole)
                .set(castOrder).equalTo(row::getCastOrder);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.450079315+02:00", comments="Source Table: v_movies_actors")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(VMovieActorDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(actorId).equalToWhenPresent(row::getActorId)
                .set(name).equalToWhenPresent(row::getName)
                .set(birth).equalToWhenPresent(row::getBirth)
                .set(gender).equalToWhenPresent(row::getGender)
                .set(movieId).equalToWhenPresent(row::getMovieId)
                .set(title).equalToWhenPresent(row::getTitle)
                .set(releaseDate).equalToWhenPresent(row::getReleaseDate)
                .set(budget).equalToWhenPresent(row::getBudget)
                .set(boxOffice).equalToWhenPresent(row::getBoxOffice)
                .set(runtime).equalToWhenPresent(row::getRuntime)
                .set(overview).equalToWhenPresent(row::getOverview)
                .set(role).equalToWhenPresent(row::getRole)
                .set(castOrder).equalToWhenPresent(row::getCastOrder);
    }
}