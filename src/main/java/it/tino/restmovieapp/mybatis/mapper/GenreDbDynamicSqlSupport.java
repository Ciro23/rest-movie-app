package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class GenreDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.873196Z", comments="Source Table: genres")
    public static final GenreDb genreDb = new GenreDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.873426Z", comments="Source field: genres.id")
    public static final SqlColumn<Integer> id = genreDb.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.873529Z", comments="Source field: genres.name")
    public static final SqlColumn<Object> name = genreDb.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.873312Z", comments="Source Table: genres")
    public static final class GenreDb extends AliasableSqlTable<GenreDb> {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Object> name = column("name", JDBCType.OTHER);

        public GenreDb() {
            super("genres", GenreDb::new);
        }
    }
}