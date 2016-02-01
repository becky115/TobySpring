package ex3._14;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ex3._8.StatementStrategy;

/**
 * 리스트 3-14 User 정보를 생성자로부터 제공받도록 만든 AddStatement
 * @author eunji
 *
 */
public class AddStatement implements StatementStrategy{
	
	User user;
		
	public AddStatement(User user) {
		this.user = user;
	}


	public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password values(?, ?, ?)");
		
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		return ps;
	}

}
