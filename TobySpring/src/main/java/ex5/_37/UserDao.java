package ex5._37;

import java.sql.Connection;

import ex5._26.User;

public interface UserDao {
	
	public void add(Connection c, User user);
	public void get(Connection c, String id);
	
	public void update(Connection c, User user1);
}
