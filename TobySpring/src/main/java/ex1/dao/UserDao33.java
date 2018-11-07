package ex1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ex1.db.ConnectionMaker8;
import ex1.domain.User;

/**
 * 리스트1-33 수정자 메소드 DI방식을 사용한 UserDao
 * @author ejlee
 *
 */
public class UserDao33 {
	
	private ConnectionMaker8 connectionMaker;

	public void setConnectionMaker(ConnectionMaker8 connectionMaker) {
		this.connectionMaker = connectionMaker;
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
		System.out.println(user.toString());
		return user;
	}
}
