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
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888244Z", comments="Source Table: movies_actors")
    BasicColumn[] selectList = BasicColumn.columnList(movieId, actorId, role, castOrder);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887582Z", comments="Source Table: movies_actors")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MovieActorDbResult", value = {
        @Result(column="movie_id", property="movieId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="actor_id", property="actorId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role", property="role", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="cast_order", property="castOrder", jdbcType=JdbcType.INTEGER)
    })
    List<MovieActorDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887674Z", comments="Source Table: movies_actors")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MovieActorDbResult")
    Optional<MovieActorDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.88772Z", comments="Source Table: movies_actors")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, movieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887761Z", comments="Source Table: movies_actors")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, movieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.8878Z", comments="Source Table: movies_actors")
    default int deleteByPrimaryKey(Integer movieId_, Integer actorId_, String role_) {
        return delete(c -> 
            c.where(movieId, isEqualTo(movieId_))
            .and(actorId, isEqualTo(actorId_))
            .and(role, isEqualTo(role_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887853Z", comments="Source Table: movies_actors")
    default int insert(MovieActorDb row) {
        return MyBatis3Utils.insert(this::insert, row, movieActorDb, c ->
            c.map(movieId).toProperty("movieId")
            .map(actorId).toProperty("actorId")
            .map(role).toProperty("role")
            .map(castOrder).toProperty("castOrder")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887946Z", comments="Source Table: movies_actors")
    default int insertMultiple(Collection<MovieActorDb> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, movieActorDb, c ->
            c.map(movieId).toProperty("movieId")
            .map(actorId).toProperty("actorId")
            .map(role).toProperty("role")
            .map(castOrder).toProperty("castOrder")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888081Z", comments="Source Table: movies_actors")
    default int insertSelective(MovieActorDb row) {
        return MyBatis3Utils.insert(this::insert, row, movieActorDb, c ->
            c.map(movieId).toPropertyWhenPresent("movieId", row::getMovieId)
            .map(actorId).toPropertyWhenPresent("actorId", row::getActorId)
            .map(role).toPropertyWhenPresent("role", row::getRole)
            .map(castOrder).toPropertyWhenPresent("castOrder", row::getCastOrder)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888283Z", comments="Source Table: movies_actors")
    default Optional<MovieActorDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, movieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888323Z", comments="Source Table: movies_actors")
    default List<MovieActorDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, movieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888363Z", comments="Source Table: movies_actors")
    default List<MovieActorDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, movieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888399Z", comments="Source Table: movies_actors")
    default Optional<MovieActorDb> selectByPrimaryKey(Integer movieId_, Integer actorId_, String role_) {
        return selectOne(c ->
            c.where(movieId, isEqualTo(movieId_))
            .and(actorId, isEqualTo(actorId_))
            .and(role, isEqualTo(role_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888454Z", comments="Source Table: movies_actors")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, movieActorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888499Z", comments="Source Table: movies_actors")
    static UpdateDSL<UpdateModel> updateAllColumns(MovieActorDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(movieId).equalTo(row::getMovieId)
                .set(actorId).equalTo(row::getActorId)
                .set(role).equalTo(row::getRole)
                .set(castOrder).equalTo(row::getCastOrder);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.88858Z", comments="Source Table: movies_actors")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(MovieActorDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(movieId).equalToWhenPresent(row::getMovieId)
                .set(actorId).equalToWhenPresent(row::getActorId)
                .set(role).equalToWhenPresent(row::getRole)
                .set(castOrder).equalToWhenPresent(row::getCastOrder);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888677Z", comments="Source Table: movies_actors")
    default int updateByPrimaryKey(MovieActorDb row) {
        return update(c ->
            c.set(castOrder).equalTo(row::getCastOrder)
            .where(movieId, isEqualTo(row::getMovieId))
            .and(actorId, isEqualTo(row::getActorId))
            .and(role, isEqualTo(row::getRole))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.888764Z", comments="Source Table: movies_actors")
    default int updateByPrimaryKeySelective(MovieActorDb row) {
        return update(c ->
            c.set(castOrder).equalToWhenPresent(row::getCastOrder)
            .where(movieId, isEqualTo(row::getMovieId))
            .and(actorId, isEqualTo(row::getActorId))
            .and(role, isEqualTo(row::getRole))
        );
    }
}