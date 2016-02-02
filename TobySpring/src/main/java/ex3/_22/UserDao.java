package ex3._22;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ex3._21.JdbcContext;
import ex3._8.StatementStrategy;

/**
 * 
 * 리스트 3-22 jdbcContext를 DI받아서 사용하도록 만든 UserDao
 * @author eunji
 *
 */
public class UserDao {
	
	private JdbcContext jdbcContext;

	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}
	
	public void add(final User user) throws SQLException {
		this.jdbcContext.workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
					
					ps.setString(1, user.getId());
					ps.setString(2, user.getName());
					ps.setString(3, user.getPassword());
					return ps;
				}
			}
		);
	}
	
	public void deleteAll() throws SQLException {
		this.jdbcContext.workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					return c.prepareStatement("delete from users");
				}
			}
		);
	}
}
