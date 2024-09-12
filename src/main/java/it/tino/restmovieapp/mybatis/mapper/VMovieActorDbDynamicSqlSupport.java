package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class VMovieActorDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.26796753+02:00", comments="Source Table: v_movies_actors")
    public static final VMovieActorDb VMovieActorDb = new VMovieActorDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268054492+02:00", comments="Source field: v_movies_actors.actor_id")
    public static final SqlColumn<Integer> actorId = VMovieActorDb.actorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268088295+02:00", comments="Source field: v_movies_actors.name")
    public static final SqlColumn<String> name = VMovieActorDb.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268124152+02:00", comments="Source field: v_movies_actors.birth")
    public static final SqlColumn<Date> birth = VMovieActorDb.birth;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268156171+02:00", comments="Source field: v_movies_actors.gender")
    public static final SqlColumn<String> gender = VMovieActorDb.gender;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268188141+02:00", comments="Source field: v_movies_actors.movie_id")
    public static final SqlColumn<Integer> movieId = VMovieActorDb.movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268221894+02:00", comments="Source field: v_movies_actors.title")
    public static final SqlColumn<String> title = VMovieActorDb.title;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268253352+02:00", comments="Source field: v_movies_actors.release_date")
    public static final SqlColumn<Date> releaseDate = VMovieActorDb.releaseDate;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268285993+02:00", comments="Source field: v_movies_actors.budget")
    public static final SqlColumn<Integer> budget = VMovieActorDb.budget;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268315528+02:00", comments="Source field: v_movies_actors.box_office")
    public static final SqlColumn<Integer> boxOffice = VMovieActorDb.boxOffice;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268349311+02:00", comments="Source field: v_movies_actors.runtime")
    public static final SqlColumn<Integer> runtime = VMovieActorDb.runtime;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268379998+02:00", comments="Source field: v_movies_actors.overview")
    public static final SqlColumn<String> overview = VMovieActorDb.overview;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268412198+02:00", comments="Source field: v_movies_actors.role")
    public static final SqlColumn<String> role = VMovieActorDb.role;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268443877+02:00", comments="Source field: v_movies_actors.cast_order")
    public static final SqlColumn<Integer> castOrder = VMovieActorDb.castOrder;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.268008286+02:00", comments="Source Table: v_movies_actors")
    public static final class VMovieActorDb extends AliasableSqlTable<VMovieActorDb> {
        public final SqlColumn<Integer> actorId = column("actor_id", JDBCType.INTEGER);

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

        public final SqlColumn<String> role = column("role", JDBCType.VARCHAR);

        public final SqlColumn<Integer> castOrder = column("cast_order", JDBCType.INTEGER);

        public VMovieActorDb() {
            super("v_movies_actors", VMovieActorDb::new);
        }
    }
}