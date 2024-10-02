package it.tino.restmovieapp.mybatis.mapper;

import jakarta.annotation.Generated;
import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class UserDbDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.825418Z", comments="Source Table: users")
    public static final UserDb userDb = new UserDb();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.82584Z", comments="Source field: users.id")
    public static final SqlColumn<Integer> id = userDb.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.826224Z", comments="Source field: users.username")
    public static final SqlColumn<String> username = userDb.username;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.826391Z", comments="Source field: users.password")
    public static final SqlColumn<String> password = userDb.password;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.82655Z", comments="Source field: users.email")
    public static final SqlColumn<String> email = userDb.email;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2024-10-03T14:07:55.825658Z", comments="Source Table: users")
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