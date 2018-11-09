package ex6._12;

import java.util.List;

import ex6._1.User;

public interface UserDao {
	
	void add(User user);
	User get(String id);
	List<User> getAll();
	void deleteAll();
	int getCount();
	public void update(User user);
}
