package ex1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ex1.db.SimpleConnectionMaker7;
import ex1.domain.User;

/**
 * 독립된 SimpleConnectionMaker7를 사용하게 만든 UserDao
 * @author ejlee
 *
 */
public class UserDao6 {
	
	private SimpleConnectionMaker7 simpleConnectionMaker7;

	public UserDao6() {
		simpleConnectionMaker7 = new SimpleConnectionMaker7();
	}
	
	public void add(User user) throws ClassNotFoundException, SQLException{
		Connection c = simpleConnectionMaker7.makeNewConnection();
		
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}

	public void delete(String id) throws ClassNotFoundException, SQLException{
		Connection c = simpleConnectionMaker7.makeNewConnection();
		
		PreparedStatement ps = c.prepareStatement("delete from users where id = ?");
		ps.setString(1, id);
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException{
		Connection c = simpleConnectionMaker7.makeNewConnection();
		
		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		rs.next();
	
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
	}
	
}
