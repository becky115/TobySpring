package ex1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ex1.db.ConnectionMaker8;
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
	
	public UserDao23(ConnectionMaker8 connectionMaker8){
		this.connectionMaker = connectionMaker8;
	}
	

	public User get(String id) throws ClassNotFoundException, SQLException{
		System.out.println(connectionMaker);
		this.c = connectionMaker.makeConnection();
		System.out.println(this.c);
		
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
