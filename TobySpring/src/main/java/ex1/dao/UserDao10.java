package ex1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ex1.domain.User;

/**
 * {@link ConnectionMaker8} 인터페이스를 사용하도록 개선한 UserDao10
 * @author ejlee
 *
 */
public class UserDao10 {
	
	private ConnectionMaker8 connectionMaker8;
	

	public UserDao10() {
		connectionMaker8 = new DConnctionMaker9();
	}
	
	public void add(User user) throws ClassNotFoundException, SQLException{
		Connection c = connectionMaker8.makeConnection();
		
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}

	public void delete(String id) throws ClassNotFoundException, SQLException{
		Connection c = connectionMaker8.makeConnection();
		
		PreparedStatement ps = c.prepareStatement("delete from users where id = ?");
		ps.setString(1, id);
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException{
		Connection c = connectionMaker8.makeConnection();
		
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
