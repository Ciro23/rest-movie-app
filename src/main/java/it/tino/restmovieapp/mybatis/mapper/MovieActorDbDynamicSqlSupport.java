package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class MovieActorDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.443313259+02:00", comments="Source Table: movies_actors")
    public static final MovieActorDb movieActorDb = new MovieActorDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.443390272+02:00", comments="Source field: movies_actors.movie_id")
    public static final SqlColumn<Integer> movieId = movieActorDb.movieId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.443443802+02:00", comments="Source field: movies_actors.actor_id")
    public static final SqlColumn<Integer> actorId = movieActorDb.actorId;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.44348036+02:00", comments="Source field: movies_actors.role")
    public static final SqlColumn<String> role = movieActorDb.role;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.443515205+02:00", comments="Source field: movies_actors.cast_order")
    public static final SqlColumn<Integer> castOrder = movieActorDb.castOrder;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.443353785+02:00", comments="Source Table: movies_actors")
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