package ex3._1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * 리스트 3-1 JDBC API를 이용한 DAO 코드인 deleteAll()
 * @author eunji
 *
 */
public class UserDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void deleteAll() throws SQLException{
		Connection c = dataSource.getConnection();
		
		//26-27 예외가 발생하면 바로 메소드 실행이 중단된다.
		PreparedStatement ps = c.prepareStatement("delete from users");
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
}
