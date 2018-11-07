package ex3._17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import ex3._14.AddStatement;
import ex3._14.User;
import ex3._8.StatementStrategy;

/**
 * 리스트 3-17 add()메소드의 로컬 변수를 직접 사용하도록 수정한 AddStatement
 * @author eunji
 *
 */
public class UserDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * @param user //로컬(내부) 클래스의 코드에서 외부의 메소드 로컬 변수에 직접 접근할 수 있다.
	 * @throws SQLException
	 */
	public void add(final User user) throws SQLException {

		class AddStatement implements StatementStrategy{
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
				
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());
				return ps;
			}
			
		}
		
		StatementStrategy st = new AddStatement(); //생성자 파라미터로 user를 전달하지 않아도 된다.
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


