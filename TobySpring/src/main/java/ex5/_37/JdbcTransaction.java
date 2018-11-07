package ex5._37;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;


public class JdbcTransaction {
	
	
	public static void main(String[] args) throws SQLException {
		DataSource dataSource = new SimpleDriverDataSource();
		Connection c = dataSource.getConnection();
		
		c.setAutoCommit(false); //트랜잭션 시작
		
		try {
			PreparedStatement st1 = c.prepareStatement("update users ...");
			
			st1.executeUpdate();
			
			PreparedStatement st2 = c.prepareStatement("update users ...");
			
			st2.executeUpdate();
			
			c.commit(); //트랜잭션 커밋
		} catch (Exception e) {
			c.rollback(); //트랜잭션 롤백 
		}
		
		c.close();
	}
}
