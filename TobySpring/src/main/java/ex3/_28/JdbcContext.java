package ex3._28;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;


import ex3._8.StatementStrategy;

/**
 * 리스트 3-28 JdbcCOntext로 옮긴 executeSql() 메소드
 * @author eunji
 *
 */
public class JdbcContext {
	
	//DataSource 타입 빈을 DI받을 수 있게 준비해둔다.
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void executeSql(final String query) throws SQLException {
		workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					return c.prepareStatement(query); 
				}
			}
		);
	}
	
	public void executeSql2(final String query, Object... args) throws SQLException {
		workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					PreparedStatement ps =  c.prepareStatement(query);
					for(int i=0; i<args.length; i++) {
						//TODO CHECK
						ps.setString(i, args[i].toString());
					}
					return ps;
				}
			}
		);
	}
	
	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		
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
