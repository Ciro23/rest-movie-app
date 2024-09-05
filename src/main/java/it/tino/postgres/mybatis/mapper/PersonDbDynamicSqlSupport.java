package it.tino.postgres.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class PersonDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.438062893+02:00", comments="Source Table: people")
    public static final PersonDb personDb = new PersonDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.438148803+02:00", comments="Source field: people.id")
    public static final SqlColumn<Integer> id = personDb.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.438193526+02:00", comments="Source field: people.name")
    public static final SqlColumn<String> name = personDb.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.438243459+02:00", comments="Source field: people.birth")
    public static final SqlColumn<Date> birth = personDb.birth;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.438287801+02:00", comments="Source field: people.gender")
    public static final SqlColumn<String> gender = personDb.gender;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-09-12T09:29:49.438107646+02:00", comments="Source Table: people")
    public static final class PersonDb extends AliasableSqlTable<PersonDb> {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Date> birth = column("birth", JDBCType.DATE);

        public final SqlColumn<String> gender = column("gender", JDBCType.CHAR);

        public PersonDb() {
            super("people", PersonDb::new);
        }
    }
}