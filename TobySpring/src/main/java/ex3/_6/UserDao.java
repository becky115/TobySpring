package ex3._6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * 리스트 3-6 변하는 부분을 메소드로 추출한 후의 deleteAll()
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
			
			//변하는 부분을 메소드로 추출하고 변하지 않는 부분에서 호출하도록 만들었다.
			ps = makeStatement(c);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}finally {
			if(ps != null) { try { ps.close(); } catch (SQLException e) {} }
			if(c != null) {try {c.close(); } catch (SQLException e) {} }
			
		}	
		
	}

	private PreparedStatement makeStatement(Connection c) throws SQLException {
		PreparedStatement ps;
		ps = c.prepareStatement("delete from users");
		return ps;
	}
}


