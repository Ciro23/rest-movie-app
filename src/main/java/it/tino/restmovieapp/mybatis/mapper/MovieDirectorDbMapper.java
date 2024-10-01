package it.tino.restmovieapp.mybatis.mapper;

import static it.tino.restmovieapp.mybatis.mapper.MovieDirectorDbDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import it.tino.restmovieapp.mybatis.model.MovieDirectorDb;
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
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.640579028+02:00", comments="Source Table: movies_directors")
    BasicColumn[] selectList = BasicColumn.columnList(movieId, directorId);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639854091+02:00", comments="Source Table: movies_directors")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MovieDirectorDbResult", value = {
        @Result(column="movie_id", property="movieId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="director_id", property="directorId", jdbcType=JdbcType.INTEGER, id=true)
    })
    List<MovieDirectorDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639930182+02:00", comments="Source Table: movies_directors")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MovieDirectorDbResult")
    Optional<MovieDirectorDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.639967201+02:00", comments="Source Table: movies_directors")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, movieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.63999913+02:00", comments="Source Table: movies_directors")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, movieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.640027733+02:00", comments="Source Table: movies_directors")
    default int deleteByPrimaryKey(Integer movieId_, Integer directorId_) {
        return delete(c -> 
            c.where(movieId, isEqualTo(movieId_))
            .and(directorId, isEqualTo(directorId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.640071254+02:00", comments="Source Table: movies_directors")
    default int insert(MovieDirectorDb row) {
        return MyBatis3Utils.insert(this::insert, row, movieDirectorDb, c ->
            c.map(movieId).toProperty("movieId")
            .map(directorId).toProperty("directorId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.640400035+02:00", comments="Source Table: movies_directors")
    default int insertMultiple(Collection<MovieDirectorDb> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, movieDirectorDb, c ->
            c.map(movieId).toProperty("movieId")
            .map(directorId).toProperty("directorId")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.640496535+02:00", comments="Source Table: movies_directors")
    default int insertSelective(MovieDirectorDb row) {
        return MyBatis3Utils.insert(this::insert, row, movieDirectorDb, c ->
            c.map(movieId).toPropertyWhenPresent("movieId", row::getMovieId)
            .map(directorId).toPropertyWhenPresent("directorId", row::getDirectorId)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.640610497+02:00", comments="Source Table: movies_directors")
    default Optional<MovieDirectorDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, movieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.640644851+02:00", comments="Source Table: movies_directors")
    default List<MovieDirectorDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, movieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.640677682+02:00", comments="Source Table: movies_directors")
    default List<MovieDirectorDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, movieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.640731832+02:00", comments="Source Table: movies_directors")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, movieDirectorDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.64076819+02:00", comments="Source Table: movies_directors")
    static UpdateDSL<UpdateModel> updateAllColumns(MovieDirectorDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(movieId).equalTo(row::getMovieId)
                .set(directorId).equalTo(row::getDirectorId);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.640828452+02:00", comments="Source Table: movies_directors")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(MovieDirectorDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(movieId).equalToWhenPresent(row::getMovieId)
                .set(directorId).equalToWhenPresent(row::getDirectorId);
    }
}