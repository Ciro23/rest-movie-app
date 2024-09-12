package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class MovieDirectorDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.26150693+02:00", comments="Source Table: movies_directors")
    public static final MovieDirectorDb movieDirectorDb = new MovieDirectorDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.261582861+02:00", comments="Source field: movies_directors.movie_id")
    public static final SqlColumn<Integer> movieId = movieDirectorDb.movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.261642051+02:00", comments="Source field: movies_directors.director_id")
    public static final SqlColumn<Integer> directorId = movieDirectorDb.directorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-26T17:06:15.261546994+02:00", comments="Source Table: movies_directors")
    public static final class MovieDirectorDb extends AliasableSqlTable<MovieDirectorDb> {
        public final SqlColumn<Integer> movieId = column("movie_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> directorId = column("director_id", JDBCType.INTEGER);

        public MovieDirectorDb() {
            super("movies_directors", MovieDirectorDb::new);
        }
    }
}