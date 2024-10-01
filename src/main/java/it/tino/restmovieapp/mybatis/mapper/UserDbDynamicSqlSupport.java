package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class UserDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.622002737+02:00", comments="Source Table: users")
    public static final UserDb userDb = new UserDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.622234979+02:00", comments="Source field: users.id")
    public static final SqlColumn<Integer> id = userDb.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.622498369+02:00", comments="Source field: users.username")
    public static final SqlColumn<String> username = userDb.username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.622555855+02:00", comments="Source field: users.password")
    public static final SqlColumn<String> password = userDb.password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.622607922+02:00", comments="Source field: users.email")
    public static final SqlColumn<String> email = userDb.email;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-02T11:37:13.622150302+02:00", comments="Source Table: users")
    public static final class UserDb extends AliasableSqlTable<UserDb> {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> username = column("username", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);

        public UserDb() {
            super("users", UserDb::new);
        }
    }
}