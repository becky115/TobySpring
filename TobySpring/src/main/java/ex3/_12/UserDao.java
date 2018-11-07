package ex3._12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import ex3._8.StatementStrategy;
import ex3._9.DeleteAllStatement;

/**
 * 리스트 3-12 클라이언트 책임을 담당할 deleteAll()메소드
 * @author eunji
 *
 */
public class UserDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public void deleteAll() throws SQLException {
		StatementStrategy st = new DeleteAllStatement(); //선정한 전략 클래스의 오브젝트 생성
		jdbcContextWithStatementStrategy(st);//컨텍스트 호출. 전략 오브젝트 전달.
		
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


