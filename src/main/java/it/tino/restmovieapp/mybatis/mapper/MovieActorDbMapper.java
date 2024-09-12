package it.tino.restmovieapp.mybatis.mapper;

import static it.tino.restmovieapp.mybatis.mapper.MovieActorDbDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import it.tino.restmovieapp.mybatis.model.MovieActorDb;
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
public interface MovieActorDbMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<MovieActorDb>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.443999155+02:00", comments="Source Table: movies_actors")
    BasicColumn[] selectList = BasicColumn.columnList(movieId, actorId, role, castOrder);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.443554979+02:00", comments="Source Table: movies_actors")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MovieActorDbResult", value = {
        @Result(column="movie_id", property="movieId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="actor_id", property="actorId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="cast_order", property="castOrder", jdbcType=JdbcType.INTEGER)
    })
    List<MovieActorDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.443627614+02:00", comments="Source Table: movies_actors")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MovieActorDbResult")
    Optional<MovieActorDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.443671526+02:00", comments="Source Table: movies_actors")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, movieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.443705098+02:00", comments="Source Table: movies_actors")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, movieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.443739302+02:00", comments="Source Table: movies_actors")
    default int deleteByPrimaryKey(Integer movieId_, Integer actorId_, String role_) {
        return delete(c -> 
            c.where(movieId, isEqualTo(movieId_))
            .and(actorId, isEqualTo(actorId_))
            .and(role, isEqualTo(role_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44377624+02:00", comments="Source Table: movies_actors")
    default int insert(MovieActorDb row) {
        return MyBatis3Utils.insert(this::insert, row, movieActorDb, c ->
            c.map(movieId).toProperty("movieId")
            .map(actorId).toProperty("actorId")
            .map(role).toProperty("role")
            .map(castOrder).toProperty("castOrder")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44383506+02:00", comments="Source Table: movies_actors")
    default int insertMultiple(Collection<MovieActorDb> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, movieActorDb, c ->
            c.map(movieId).toProperty("movieId")
            .map(actorId).toProperty("actorId")
            .map(role).toProperty("role")
            .map(castOrder).toProperty("castOrder")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.443908877+02:00", comments="Source Table: movies_actors")
    default int insertSelective(MovieActorDb row) {
        return MyBatis3Utils.insert(this::insert, row, movieActorDb, c ->
            c.map(movieId).toPropertyWhenPresent("movieId", row::getMovieId)
            .map(actorId).toPropertyWhenPresent("actorId", row::getActorId)
            .map(role).toPropertyWhenPresent("role", row::getRole)
            .map(castOrder).toPropertyWhenPresent("castOrder", row::getCastOrder)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.444031495+02:00", comments="Source Table: movies_actors")
    default Optional<MovieActorDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, movieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.444064296+02:00", comments="Source Table: movies_actors")
    default List<MovieActorDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, movieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.444095644+02:00", comments="Source Table: movies_actors")
    default List<MovieActorDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, movieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.444125149+02:00", comments="Source Table: movies_actors")
    default Optional<MovieActorDb> selectByPrimaryKey(Integer movieId_, Integer actorId_, String role_) {
        return selectOne(c ->
            c.where(movieId, isEqualTo(movieId_))
            .and(actorId, isEqualTo(actorId_))
            .and(role, isEqualTo(role_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.444163821+02:00", comments="Source Table: movies_actors")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, movieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.444198726+02:00", comments="Source Table: movies_actors")
    static UpdateDSL<UpdateModel> updateAllColumns(MovieActorDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(movieId).equalTo(row::getMovieId)
                .set(actorId).equalTo(row::getActorId)
                .set(role).equalTo(row::getRole)
                .set(castOrder).equalTo(row::getCastOrder);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44433488+02:00", comments="Source Table: movies_actors")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(MovieActorDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(movieId).equalToWhenPresent(row::getMovieId)
                .set(actorId).equalToWhenPresent(row::getActorId)
                .set(role).equalToWhenPresent(row::getRole)
                .set(castOrder).equalToWhenPresent(row::getCastOrder);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.444402295+02:00", comments="Source Table: movies_actors")
    default int updateByPrimaryKey(MovieActorDb row) {
        return update(c ->
            c.set(castOrder).equalTo(row::getCastOrder)
            .where(movieId, isEqualTo(row::getMovieId))
            .and(actorId, isEqualTo(row::getActorId))
            .and(role, isEqualTo(row::getRole))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.444497041+02:00", comments="Source Table: movies_actors")
    default int updateByPrimaryKeySelective(MovieActorDb row) {
        return update(c ->
            c.set(castOrder).equalToWhenPresent(row::getCastOrder)
            .where(movieId, isEqualTo(row::getMovieId))
            .and(actorId, isEqualTo(row::getActorId))
            .and(role, isEqualTo(row::getRole))
        );
    }
}