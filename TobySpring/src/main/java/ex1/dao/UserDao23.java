package ex1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ex1.domain.User;

/**
 * 인스턴스 변수를 사용하도록 수정한 UserDao
 * @author ejlee
 *
 */
public class UserDao23 {
	
	private ConnectionMaker8 connectionMaker;
	private Connection c;
	private User user;
	
	
	public User get(String id) throws ClassNotFoundException, SQLException{
		this.c = connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		rs.next();
	
		this.user = new User();
		this.user.setId(rs.getString("id"));
		this.user.setName(rs.getString("name"));
		this.user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return this.user;
	}
}
