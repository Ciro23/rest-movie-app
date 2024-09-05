package it.tino.postgres.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class MovieGenreDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.444735565+02:00", comments="Source Table: movies_genres")
    public static final MovieGenreDb movieGenreDb = new MovieGenreDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.444810564+02:00", comments="Source field: movies_genres.movie_id")
    public static final SqlColumn<Integer> movieId = movieGenreDb.movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.444848916+02:00", comments="Source field: movies_genres.genre_id")
    public static final SqlColumn<Integer> genreId = movieGenreDb.genreId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.444774557+02:00", comments="Source Table: movies_genres")
    public static final class MovieGenreDb extends AliasableSqlTable<MovieGenreDb> {
        public final SqlColumn<Integer> movieId = column("movie_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> genreId = column("genre_id", JDBCType.INTEGER);

        public MovieGenreDb() {
            super("movies_genres", MovieGenreDb::new);
        }
    }
}