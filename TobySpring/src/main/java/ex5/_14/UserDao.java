package ex5._14;

import java.util.List;

import ex5._3.User;

public interface UserDao {
	
	void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	int getCount();
	public int update(User user);
}
