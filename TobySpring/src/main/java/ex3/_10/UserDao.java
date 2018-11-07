package ex3._10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import ex3._8.StatementStrategy;
import ex3._9.DeleteAllStatement;

/**
 * 리스트 3-10 전략패턴을 따라 DeleteAllStatement가 적용된 deleteAll()메소드
 * @author eunji
 *
 */
public class UserDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void deleteAll() throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = dataSource.getConnection();
			
			StatementStrategy strategy = new DeleteAllStatement();
			ps = strategy.makePreparedStatement(c);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}finally {
			if(ps != null) { try { ps.close(); } catch (SQLException e) {} }
			if(c != null) {try {c.close(); } catch (SQLException e) {} }
			
		}	
		
	}

}


