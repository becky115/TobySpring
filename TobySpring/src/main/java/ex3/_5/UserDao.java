package ex3._5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * 리스트 3-5 add()메소드에서 수정할 부분
 * @author eunji
 *
 */
public class UserDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void add(User user) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		
		try {
			
			c = dataSource.getConnection();
			
			ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");//변하는 부분
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}finally {
			if(ps != null) { try { ps.close(); } catch (SQLException e) {} }
			if(c != null) {try {c.close(); } catch (SQLException e) {} }
			
		}	
		
	}
}



