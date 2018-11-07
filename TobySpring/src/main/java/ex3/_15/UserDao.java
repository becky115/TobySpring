package ex3._15;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import ex3._14.AddStatement;
import ex3._14.User;
import ex3._8.StatementStrategy;

/**
 * 리스트 3-15 user정보를 AddStatement에 전달해주는 add()메소드
 * @author eunji
 *
 */
public class UserDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public void add(User user) throws SQLException {
		StatementStrategy st = new AddStatement(user); 
		jdbcContextWithStatementStrategy(st);
		
	}
	
	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = dataSource.getConnection();
	
			ps = stmt.makePreparedStatement(c);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}finally {
			if(ps != null) { try { ps.close(); } catch (SQLException e) {} }
			if(c != null) {try {c.close(); } catch (SQLException e) {} }
			
		}	
		
	}


}


