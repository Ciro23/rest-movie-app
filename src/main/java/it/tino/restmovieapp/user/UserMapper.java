package it.tino.restmovieapp.user;

import it.tino.restmovieapp.ObjectMapper;
import it.tino.restmovieapp.mybatis.model.UserDb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserMapper implements ObjectMapper<User, UserDb> {

    @Override
    public List<User> sourceToDomain(Collection<UserDb> source) {
        List<User> users = new ArrayList<>();
        for (UserDb dbEntity : source) {
            User user = new User();
            user.setId(dbEntity.getId());
            user.setEmail(dbEntity.getEmail());
            user.setUsername(dbEntity.getUsername());
            user.setPassword(dbEntity.getPassword());
            users.add(user);
        }
        return users;
    }

    @Override
    public List<UserDb> domainToTarget(Collection<User> domainEntities) {
        List<UserDb> usersDb = new ArrayList<>();
        for (User domainEntity : domainEntities) {
            UserDb userDb = new UserDb();
            userDb.setId(domainEntity.getId());
            userDb.setEmail(domainEntity.getEmail());
            userDb.setUsername(domainEntity.getUsername());
            userDb.setPassword(domainEntity.getPassword());
            usersDb.add(userDb);
        }
        return usersDb;
    }
}
