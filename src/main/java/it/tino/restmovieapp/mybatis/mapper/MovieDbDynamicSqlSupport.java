package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class MovieDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.358487767+02:00", comments="Source Table: movies")
    public static final MovieDb movieDb = new MovieDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.358570562+02:00", comments="Source field: movies.id")
    public static final SqlColumn<Integer> id = movieDb.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.358607971+02:00", comments="Source field: movies.title")
    public static final SqlColumn<String> title = movieDb.title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.35865579+02:00", comments="Source field: movies.release_date")
    public static final SqlColumn<Date> releaseDate = movieDb.releaseDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.358693961+02:00", comments="Source field: movies.budget")
    public static final SqlColumn<Integer> budget = movieDb.budget;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.358732163+02:00", comments="Source field: movies.box_office")
    public static final SqlColumn<Integer> boxOffice = movieDb.boxOffice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.358774732+02:00", comments="Source field: movies.runtime")
    public static final SqlColumn<Integer> runtime = movieDb.runtime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.358810579+02:00", comments="Source field: movies.overview")
    public static final SqlColumn<String> overview = movieDb.overview;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-30T12:02:57.358532831+02:00", comments="Source Table: movies")
    public static final class MovieDb extends AliasableSqlTable<MovieDb> {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);

        public final SqlColumn<Date> releaseDate = column("release_date", JDBCType.DATE);

        public final SqlColumn<Integer> budget = column("budget", JDBCType.INTEGER);

        public final SqlColumn<Integer> boxOffice = column("box_office", JDBCType.INTEGER);

        public final SqlColumn<Integer> runtime = column("runtime", JDBCType.INTEGER);

        public final SqlColumn<String> overview = column("overview", JDBCType.VARCHAR);

        public MovieDb() {
            super("movies", MovieDb::new);
        }
    }
}