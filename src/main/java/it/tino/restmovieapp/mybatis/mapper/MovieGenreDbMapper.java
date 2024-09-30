package it.tino.restmovieapp.mybatis.mapper;

import static it.tino.restmovieapp.mybatis.mapper.MovieGenreDbDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import it.tino.restmovieapp.mybatis.model.MovieGenreDb;
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
public interface MovieGenreDbMapper extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<MovieGenreDb>, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.363604705+02:00", comments="Source Table: movies_genres")
    BasicColumn[] selectList = BasicColumn.columnList(movieId, genreId);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.363216322+02:00", comments="Source Table: movies_genres")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MovieGenreDbResult", value = {
        @Result(column="movie_id", property="movieId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="genre_id", property="genreId", jdbcType=JdbcType.INTEGER, id=true)
    })
    List<MovieGenreDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.363273719+02:00", comments="Source Table: movies_genres")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MovieGenreDbResult")
    Optional<MovieGenreDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.363317901+02:00", comments="Source Table: movies_genres")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, movieGenreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.363346815+02:00", comments="Source Table: movies_genres")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, movieGenreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.363376129+02:00", comments="Source Table: movies_genres")
    default int deleteByPrimaryKey(Integer movieId_, Integer genreId_) {
        return delete(c -> 
            c.where(movieId, isEqualTo(movieId_))
            .and(genreId, isEqualTo(genreId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.363406295+02:00", comments="Source Table: movies_genres")
    default int insert(MovieGenreDb row) {
        return MyBatis3Utils.insert(this::insert, row, movieGenreDb, c ->
            c.map(movieId).toProperty("movieId")
            .map(genreId).toProperty("genreId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.363460516+02:00", comments="Source Table: movies_genres")
    default int insertMultiple(Collection<MovieGenreDb> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, movieGenreDb, c ->
            c.map(movieId).toProperty("movieId")
            .map(genreId).toProperty("genreId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.363529375+02:00", comments="Source Table: movies_genres")
    default int insertSelective(MovieGenreDb row) {
        return MyBatis3Utils.insert(this::insert, row, movieGenreDb, c ->
            c.map(movieId).toPropertyWhenPresent("movieId", row::getMovieId)
            .map(genreId).toPropertyWhenPresent("genreId", row::getGenreId)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.36365026+02:00", comments="Source Table: movies_genres")
    default Optional<MovieGenreDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, movieGenreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.36368292+02:00", comments="Source Table: movies_genres")
    default List<MovieGenreDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, movieGenreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.363713558+02:00", comments="Source Table: movies_genres")
    default List<MovieGenreDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, movieGenreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.363744545+02:00", comments="Source Table: movies_genres")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, movieGenreDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.363814696+02:00", comments="Source Table: movies_genres")
    static UpdateDSL<UpdateModel> updateAllColumns(MovieGenreDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(movieId).equalTo(row::getMovieId)
                .set(genreId).equalTo(row::getGenreId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.363874547+02:00", comments="Source Table: movies_genres")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(MovieGenreDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(movieId).equalToWhenPresent(row::getMovieId)
                .set(genreId).equalToWhenPresent(row::getGenreId);
    }
}