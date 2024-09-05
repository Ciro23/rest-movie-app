package it.tino.postgres.mybatis.mapper;

import static it.tino.postgres.mybatis.mapper.MovieDirectorDbDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import it.tino.postgres.mybatis.model.MovieDirectorDb;
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
public interface MovieDirectorDbMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<MovieDirectorDb>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.442676024+02:00", comments="Source Table: movies_directors")
    BasicColumn[] selectList = BasicColumn.columnList(movieId, directorId);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.441773345+02:00", comments="Source Table: movies_directors")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MovieDirectorDbResult", value = {
        @Result(column="movie_id", property="movieId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="director_id", property="directorId", jdbcType=JdbcType.INTEGER, id=true)
    })
    List<MovieDirectorDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.441841211+02:00", comments="Source Table: movies_directors")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MovieDirectorDbResult")
    Optional<MovieDirectorDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.441889631+02:00", comments="Source Table: movies_directors")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, movieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44192698+02:00", comments="Source Table: movies_directors")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, movieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.441963458+02:00", comments="Source Table: movies_directors")
    default int deleteByPrimaryKey(Integer movieId_, Integer directorId_) {
        return delete(c -> 
            c.where(movieId, isEqualTo(movieId_))
            .and(directorId, isEqualTo(directorId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44200185+02:00", comments="Source Table: movies_directors")
    default int insert(MovieDirectorDb row) {
        return MyBatis3Utils.insert(this::insert, row, movieDirectorDb, c ->
            c.map(movieId).toProperty("movieId")
            .map(directorId).toProperty("directorId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.442477454+02:00", comments="Source Table: movies_directors")
    default int insertMultiple(Collection<MovieDirectorDb> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, movieDirectorDb, c ->
            c.map(movieId).toProperty("movieId")
            .map(directorId).toProperty("directorId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.442587559+02:00", comments="Source Table: movies_directors")
    default int insertSelective(MovieDirectorDb row) {
        return MyBatis3Utils.insert(this::insert, row, movieDirectorDb, c ->
            c.map(movieId).toPropertyWhenPresent("movieId", row::getMovieId)
            .map(directorId).toPropertyWhenPresent("directorId", row::getDirectorId)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.442715066+02:00", comments="Source Table: movies_directors")
    default Optional<MovieDirectorDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, movieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.442754149+02:00", comments="Source Table: movies_directors")
    default List<MovieDirectorDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, movieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.442794123+02:00", comments="Source Table: movies_directors")
    default List<MovieDirectorDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, movieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44282965+02:00", comments="Source Table: movies_directors")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, movieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.442865426+02:00", comments="Source Table: movies_directors")
    static UpdateDSL<UpdateModel> updateAllColumns(MovieDirectorDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(movieId).equalTo(row::getMovieId)
                .set(directorId).equalTo(row::getDirectorId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.442933322+02:00", comments="Source Table: movies_directors")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(MovieDirectorDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(movieId).equalToWhenPresent(row::getMovieId)
                .set(directorId).equalToWhenPresent(row::getDirectorId);
    }
}