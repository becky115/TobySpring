package ex1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ex1.db.ConnectionMaker8;
import ex1.domain.User;

/**
 * {@link UserDao10} 생성자 수정
 * @author ejlee
 *
 */
public class UserDao11 {
	
	private ConnectionMaker8 connectionMaker;
	

	public UserDao11(ConnectionMaker8 connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}

	public void delete(String id) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement("delete from users where id = ?");
		ps.setString(1, id);
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		
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
