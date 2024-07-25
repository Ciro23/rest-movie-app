package it.tino.postgres.user;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import it.tino.postgres.database.DataManagerTester;

public class UserManagerTester extends DataManagerTester<User, Integer> {

	private final UserManager userManager;
	
    public UserManagerTester(UserManager reviewManager) {
        this.userManager = reviewManager;
    }
    
    @Override
	protected Supplier<List<User>> onSelectAll() {
		return () -> userManager.selectAll();
	}

	@Override
	protected Function<User, User> onInsert() {
		return (toInsert) -> userManager.insert(toInsert);
	}

	@Override
	protected Function<User, User> onUpdate() {
		return (toUpdate) -> userManager.update(toUpdate);
	}

	@Override
	protected Function<Integer, Boolean> onDelete() {
		return (id) -> userManager.delete(id);
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
