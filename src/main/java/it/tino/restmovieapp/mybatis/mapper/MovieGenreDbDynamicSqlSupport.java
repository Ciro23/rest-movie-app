package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

import java.sql.JDBCType;

public final class MovieGenreDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.889097Z", comments="Source Table: movies_genres")
    public static final MovieGenreDb movieGenreDb = new MovieGenreDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.889215Z", comments="Source field: movies_genres.movie_id")
    public static final SqlColumn<Integer> movieId = movieGenreDb.movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.889279Z", comments="Source field: movies_genres.genre_id")
    public static final SqlColumn<Integer> genreId = movieGenreDb.genreId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.889157Z", comments="Source Table: movies_genres")
    public static final class MovieGenreDb extends AliasableSqlTable<MovieGenreDb> {
        public final SqlColumn<Integer> movieId = column("movie_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> genreId = column("genre_id", JDBCType.INTEGER);

        public MovieGenreDb() {
            super("movies_genres", MovieGenreDb::new);
        }
    }
}