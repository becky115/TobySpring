package ex1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ex1.db.ConnectionMaker8;
import ex1.db.DConnctionMaker9;
import ex1.domain.User;

/**
 * 싱글턴 패턴을 적용한 UserDao
 * @author ejlee
 *
 */
public class UserDao22 {
	
	private static UserDao22 INSTANCE;
	private ConnectionMaker8 connectionMaker;
	
	private UserDao22(ConnectionMaker8 connectionMaker){
		this.connectionMaker = connectionMaker;
	}
	
	public static synchronized UserDao22 getInstance(){
		if(INSTANCE == null) INSTANCE = new UserDao22(new DConnctionMaker9());
		return INSTANCE;
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException{
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
