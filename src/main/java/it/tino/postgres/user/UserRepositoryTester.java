package it.tino.postgres.user;

import it.tino.postgres.database.RepositoryTester;

public class UserRepositoryTester extends RepositoryTester<User, Integer> {

    public UserRepositoryTester(UserRepository repository) {
        super(repository);
    }

    @Override
    protected User onCreateObject() {
    	User user = new User();
    	user.setUsername("new_user");
    	user.setPassword("my_password");
    	
    	return user;
    }

    @Override
    protected void onUpdateObject(User objectToUpdate) {
        objectToUpdate.setUsername("new user (updated)");
        objectToUpdate.setPassword("my_password (updated)");
    }
}
