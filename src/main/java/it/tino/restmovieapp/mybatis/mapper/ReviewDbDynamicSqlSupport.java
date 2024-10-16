package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class ReviewDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.869135Z", comments="Source Table: reviews")
    public static final ReviewDb reviewDb = new ReviewDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.86947Z", comments="Source field: reviews.id")
    public static final SqlColumn<Integer> id = reviewDb.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.869581Z", comments="Source field: reviews.movie_id")
    public static final SqlColumn<Integer> movieId = reviewDb.movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.869681Z", comments="Source field: reviews.user_id")
    public static final SqlColumn<Integer> userId = reviewDb.userId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.869787Z", comments="Source field: reviews.creation_date")
    public static final SqlColumn<Date> creationDate = reviewDb.creationDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.869887Z", comments="Source field: reviews.vote")
    public static final SqlColumn<Float> vote = reviewDb.vote;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.869993Z", comments="Source field: reviews.review")
    public static final SqlColumn<String> review = reviewDb.review;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.869337Z", comments="Source Table: reviews")
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