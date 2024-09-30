package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class ReviewDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352835333+02:00", comments="Source Table: reviews")
    public static final ReviewDb reviewDb = new ReviewDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352966788+02:00", comments="Source field: reviews.id")
    public static final SqlColumn<Integer> id = reviewDb.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.35301641+02:00", comments="Source field: reviews.movie_id")
    public static final SqlColumn<Integer> movieId = reviewDb.movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.353061574+02:00", comments="Source field: reviews.user_id")
    public static final SqlColumn<Integer> userId = reviewDb.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.353109914+02:00", comments="Source field: reviews.creation_date")
    public static final SqlColumn<Date> creationDate = reviewDb.creationDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.353154618+02:00", comments="Source field: reviews.vote")
    public static final SqlColumn<Float> vote = reviewDb.vote;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.35319881+02:00", comments="Source field: reviews.review")
    public static final SqlColumn<String> review = reviewDb.review;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.352910713+02:00", comments="Source Table: reviews")
    public static final class ReviewDb extends AliasableSqlTable<ReviewDb> {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Integer> movieId = column("movie_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> userId = column("user_id", JDBCType.INTEGER);

        public final SqlColumn<Date> creationDate = column("creation_date", JDBCType.TIMESTAMP);

        public final SqlColumn<Float> vote = column("vote", JDBCType.REAL);

        public final SqlColumn<String> review = column("review", JDBCType.VARCHAR);

        public ReviewDb() {
            super("reviews", ReviewDb::new);
        }
    }
}