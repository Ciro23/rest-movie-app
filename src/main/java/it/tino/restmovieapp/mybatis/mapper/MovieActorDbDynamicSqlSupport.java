package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class MovieActorDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887196Z", comments="Source Table: movies_actors")
    public static final MovieActorDb movieActorDb = new MovieActorDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887324Z", comments="Source field: movies_actors.movie_id")
    public static final SqlColumn<Integer> movieId = movieActorDb.movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887387Z", comments="Source field: movies_actors.actor_id")
    public static final SqlColumn<Integer> actorId = movieActorDb.actorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887463Z", comments="Source field: movies_actors.role")
    public static final SqlColumn<String> role = movieActorDb.role;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887525Z", comments="Source field: movies_actors.cast_order")
    public static final SqlColumn<Integer> castOrder = movieActorDb.castOrder;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.887263Z", comments="Source Table: movies_actors")
    public static final class MovieActorDb extends AliasableSqlTable<MovieActorDb> {
        public final SqlColumn<Integer> movieId = column("movie_id", JDBCType.INTEGER);

        public final SqlColumn<Integer> actorId = column("actor_id", JDBCType.INTEGER);

        public final SqlColumn<String> role = column("role", JDBCType.VARCHAR);

        public final SqlColumn<Integer> castOrder = column("cast_order", JDBCType.INTEGER);

        public MovieActorDb() {
            super("movies_actors", MovieActorDb::new);
        }
    }
}