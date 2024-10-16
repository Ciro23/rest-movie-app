package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class VMovieGenreDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897263Z", comments="Source Table: v_movies_genres")
    public static final VMovieGenreDb VMovieGenreDb = new VMovieGenreDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897404Z", comments="Source field: v_movies_genres.genre_id")
    public static final SqlColumn<Integer> genreId = VMovieGenreDb.genreId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897482Z", comments="Source field: v_movies_genres.name")
    public static final SqlColumn<Object> name = VMovieGenreDb.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897544Z", comments="Source field: v_movies_genres.movie_id")
    public static final SqlColumn<Integer> movieId = VMovieGenreDb.movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897604Z", comments="Source field: v_movies_genres.title")
    public static final SqlColumn<String> title = VMovieGenreDb.title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897666Z", comments="Source field: v_movies_genres.release_date")
    public static final SqlColumn<Date> releaseDate = VMovieGenreDb.releaseDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897731Z", comments="Source field: v_movies_genres.budget")
    public static final SqlColumn<Integer> budget = VMovieGenreDb.budget;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897793Z", comments="Source field: v_movies_genres.box_office")
    public static final SqlColumn<Integer> boxOffice = VMovieGenreDb.boxOffice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897855Z", comments="Source field: v_movies_genres.runtime")
    public static final SqlColumn<Integer> runtime = VMovieGenreDb.runtime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897927Z", comments="Source field: v_movies_genres.overview")
    public static final SqlColumn<String> overview = VMovieGenreDb.overview;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.897326Z", comments="Source Table: v_movies_genres")
    public static final class VMovieGenreDb extends AliasableSqlTable<VMovieGenreDb> {
        public final SqlColumn<Integer> genreId = column("genre_id", JDBCType.INTEGER);

        public final SqlColumn<Object> name = column("name", JDBCType.OTHER);

        public final SqlColumn<Integer> movieId = column("movie_id", JDBCType.INTEGER);

        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);

        public final SqlColumn<Date> releaseDate = column("release_date", JDBCType.DATE);

        public final SqlColumn<Integer> budget = column("budget", JDBCType.INTEGER);

        public final SqlColumn<Integer> boxOffice = column("box_office", JDBCType.INTEGER);

        public final SqlColumn<Integer> runtime = column("runtime", JDBCType.INTEGER);

        public final SqlColumn<String> overview = column("overview", JDBCType.VARCHAR);

        public VMovieGenreDb() {
            super("v_movies_genres", VMovieGenreDb::new);
        }
    }
}