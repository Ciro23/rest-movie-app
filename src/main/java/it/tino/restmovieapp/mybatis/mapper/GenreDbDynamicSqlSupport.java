package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class GenreDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634456076+02:00", comments="Source Table: genres")
    public static final GenreDb genreDb = new GenreDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634536275+02:00", comments="Source field: genres.id")
    public static final SqlColumn<Integer> id = genreDb.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634576881+02:00", comments="Source field: genres.name")
    public static final SqlColumn<Object> name = genreDb.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.634497794+02:00", comments="Source Table: genres")
    public static final class GenreDb extends AliasableSqlTable<GenreDb> {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Object> name = column("name", JDBCType.OTHER);

        public GenreDb() {
            super("genres", GenreDb::new);
        }
    }
}