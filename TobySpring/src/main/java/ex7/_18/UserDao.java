package ex7._18;

import java.util.List;

import ex7.User;

public interface UserDao {
	
	void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	int getCount();
	public int update(User user);
}
