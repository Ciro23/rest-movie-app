package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class MovieDirectorDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.884921Z", comments="Source Table: movies_directors")
    public static final MovieDirectorDb movieDirectorDb = new MovieDirectorDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.885153Z", comments="Source field: movies_directors.movie_id")
    public static final SqlColumn<Integer> movieId = movieDirectorDb.movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.885239Z", comments="Source field: movies_directors.director_id")
    public static final SqlColumn<Integer> directorId = movieDirectorDb.directorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.884978Z", comments="Source Table: movies_directors")
    public static final class MovieDirectorDb extends AliasableSqlTable<MovieDirectorDb> {
        public final SqlColumn<Integer> movieId = column("movie_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> directorId = column("director_id", JDBCType.INTEGER);

        public MovieDirectorDb() {
            super("movies_directors", MovieDirectorDb::new);
        }
    }
}