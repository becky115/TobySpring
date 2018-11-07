package ex3._23;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;


import ex3._8.StatementStrategy;

/**
 * 리스트 3-21 JDBC 작업 흐름을 분리해서 만든 JdbcContext 클래스
 * @author eunji
 *
 */
public class JdbcContext {
	
	//DataSource 타입 빈을 DI받을 수 있게 준비해둔다.
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//JdbcContext클래스 안에 옮겼으므로 이름도 그에 맞게 수정했다.
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
