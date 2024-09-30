package it.tino.restmovieapp.mybatis.mapper;

import static it.tino.restmovieapp.mybatis.mapper.ReviewDbDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import it.tino.restmovieapp.mybatis.model.ReviewDb;
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
public interface ReviewDbMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354013517+02:00", comments="Source Table: reviews")
    BasicColumn[] selectList = BasicColumn.columnList(id, movieId, userId, creationDate, vote, review);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.353263019+02:00", comments="Source Table: reviews")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="row.id")
    int insert(InsertStatementProvider<ReviewDb> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.353357075+02:00", comments="Source Table: reviews")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultipleWithGeneratedKeys")
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<ReviewDb> records);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.353430141+02:00", comments="Source Table: reviews")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ReviewDbResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="movie_id", property="movieId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="creation_date", property="creationDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="vote", property="vote", jdbcType=JdbcType.REAL),
        @Result(column="review", property="review", jdbcType=JdbcType.VARCHAR)
    })
    List<ReviewDb> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.353542199+02:00", comments="Source Table: reviews")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ReviewDbResult")
    Optional<ReviewDb> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.353602872+02:00", comments="Source Table: reviews")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, reviewDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.353664277+02:00", comments="Source Table: reviews")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, reviewDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.35370934+02:00", comments="Source Table: reviews")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.353760405+02:00", comments="Source Table: reviews")
    default int insert(ReviewDb row) {
        return MyBatis3Utils.insert(this::insert, row, reviewDb, c ->
            c.map(movieId).toProperty("movieId")
            .map(userId).toProperty("userId")
            .map(creationDate).toProperty("creationDate")
            .map(vote).toProperty("vote")
            .map(review).toProperty("review")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.353841036+02:00", comments="Source Table: reviews")
    default int insertMultiple(Collection<ReviewDb> records) {
        return MyBatis3Utils.insertMultipleWithGeneratedKeys(this::insertMultiple, records, reviewDb, c ->
            c.map(movieId).toProperty("movieId")
            .map(userId).toProperty("userId")
            .map(creationDate).toProperty("creationDate")
            .map(vote).toProperty("vote")
            .map(review).toProperty("review")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.3539187+02:00", comments="Source Table: reviews")
    default int insertSelective(ReviewDb row) {
        return MyBatis3Utils.insert(this::insert, row, reviewDb, c ->
            c.map(movieId).toPropertyWhenPresent("movieId", row::getMovieId)
            .map(userId).toPropertyWhenPresent("userId", row::getUserId)
            .map(creationDate).toPropertyWhenPresent("creationDate", row::getCreationDate)
            .map(vote).toPropertyWhenPresent("vote", row::getVote)
            .map(review).toPropertyWhenPresent("review", row::getReview)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354064151+02:00", comments="Source Table: reviews")
    default Optional<ReviewDb> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, reviewDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354110527+02:00", comments="Source Table: reviews")
    default List<ReviewDb> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, reviewDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354152806+02:00", comments="Source Table: reviews")
    default List<ReviewDb> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, reviewDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354193161+02:00", comments="Source Table: reviews")
    default Optional<ReviewDb> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354235731+02:00", comments="Source Table: reviews")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, reviewDb, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354290733+02:00", comments="Source Table: reviews")
    static UpdateDSL<UpdateModel> updateAllColumns(ReviewDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(movieId).equalTo(row::getMovieId)
                .set(userId).equalTo(row::getUserId)
                .set(creationDate).equalTo(row::getCreationDate)
                .set(vote).equalTo(row::getVote)
                .set(review).equalTo(row::getReview);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354365202+02:00", comments="Source Table: reviews")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ReviewDb row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(movieId).equalToWhenPresent(row::getMovieId)
                .set(userId).equalToWhenPresent(row::getUserId)
                .set(creationDate).equalToWhenPresent(row::getCreationDate)
                .set(vote).equalToWhenPresent(row::getVote)
                .set(review).equalToWhenPresent(row::getReview);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354442666+02:00", comments="Source Table: reviews")
    default int updateByPrimaryKey(ReviewDb row) {
        return update(c ->
            c.set(movieId).equalTo(row::getMovieId)
            .set(userId).equalTo(row::getUserId)
            .set(creationDate).equalTo(row::getCreationDate)
            .set(vote).equalTo(row::getVote)
            .set(review).equalTo(row::getReview)
            .where(id, isEqualTo(row::getId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.354519258+02:00", comments="Source Table: reviews")
    default int updateByPrimaryKeySelective(ReviewDb row) {
        return update(c ->
            c.set(movieId).equalToWhenPresent(row::getMovieId)
            .set(userId).equalToWhenPresent(row::getUserId)
            .set(creationDate).equalToWhenPresent(row::getCreationDate)
            .set(vote).equalToWhenPresent(row::getVote)
            .set(review).equalToWhenPresent(row::getReview)
            .where(id, isEqualTo(row::getId))
        );
    }
}