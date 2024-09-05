package it.tino.postgres.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class VMovieDirectorDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446388219+02:00", comments="Source Table: v_movies_directors")
    public static final VMovieDirectorDb VMovieDirectorDb = new VMovieDirectorDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446474099+02:00", comments="Source field: v_movies_directors.director_id")
    public static final SqlColumn<Integer> directorId = VMovieDirectorDb.directorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446534571+02:00", comments="Source field: v_movies_directors.name")
    public static final SqlColumn<String> name = VMovieDirectorDb.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446572872+02:00", comments="Source field: v_movies_directors.birth")
    public static final SqlColumn<Date> birth = VMovieDirectorDb.birth;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446606124+02:00", comments="Source field: v_movies_directors.gender")
    public static final SqlColumn<String> gender = VMovieDirectorDb.gender;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446640418+02:00", comments="Source field: v_movies_directors.movie_id")
    public static final SqlColumn<Integer> movieId = VMovieDirectorDb.movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446674842+02:00", comments="Source field: v_movies_directors.title")
    public static final SqlColumn<String> title = VMovieDirectorDb.title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44671136+02:00", comments="Source field: v_movies_directors.release_date")
    public static final SqlColumn<Date> releaseDate = VMovieDirectorDb.releaseDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446745914+02:00", comments="Source field: v_movies_directors.budget")
    public static final SqlColumn<Integer> budget = VMovieDirectorDb.budget;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446778084+02:00", comments="Source field: v_movies_directors.box_office")
    public static final SqlColumn<Integer> boxOffice = VMovieDirectorDb.boxOffice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446824871+02:00", comments="Source field: v_movies_directors.runtime")
    public static final SqlColumn<Integer> runtime = VMovieDirectorDb.runtime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446864084+02:00", comments="Source field: v_movies_directors.overview")
    public static final SqlColumn<String> overview = VMovieDirectorDb.overview;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.446437811+02:00", comments="Source Table: v_movies_directors")
    public static final class VMovieDirectorDb extends AliasableSqlTable<VMovieDirectorDb> {
        public final SqlColumn<Integer> directorId = column("director_id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Date> birth = column("birth", JDBCType.DATE);

        public final SqlColumn<String> gender = column("gender", JDBCType.CHAR);

        public final SqlColumn<Integer> movieId = column("movie_id", JDBCType.INTEGER);

        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);

        public final SqlColumn<Date> releaseDate = column("release_date", JDBCType.DATE);

        public final SqlColumn<Integer> budget = column("budget", JDBCType.INTEGER);

        public final SqlColumn<Integer> boxOffice = column("box_office", JDBCType.INTEGER);

        public final SqlColumn<Integer> runtime = column("runtime", JDBCType.INTEGER);

        public final SqlColumn<String> overview = column("overview", JDBCType.VARCHAR);

        public VMovieDirectorDb() {
            super("v_movies_directors", VMovieDirectorDb::new);
        }
    }
}