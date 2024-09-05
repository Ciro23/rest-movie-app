package it.tino.postgres.user;

import it.tino.postgres.ObjectMapper;
import it.tino.postgres.mybatis.model.UserDb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserMapper implements ObjectMapper<User, UserDb> {

    @Override
    public List<User> dbToDomain(Collection<UserDb> dbEntities) {
        List<User> users = new ArrayList<>();
        for (UserDb dbEntity : dbEntities) {
            User user = new User();
            user.setId(dbEntity.getId());
            user.setUsername(dbEntity.getUsername());
            user.setPassword(dbEntity.getPassword());
            users.add(user);
        }
        return users;
    }

    @Override
    public List<UserDb> domainToDb(Collection<User> domainEntities) {
        List<UserDb> usersDb = new ArrayList<>();
        for (User domainEntity : domainEntities) {
            UserDb userDb = new UserDb();
            userDb.setId(domainEntity.getId());
            userDb.setUsername(domainEntity.getUsername());
            userDb.setPassword(domainEntity.getPassword());
            usersDb.add(userDb);
        }
        return usersDb;
    }
}
