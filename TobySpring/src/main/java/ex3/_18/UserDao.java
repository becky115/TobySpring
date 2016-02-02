package ex3._18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import ex3._14.User;
import ex3._8.StatementStrategy;

/**
 * 리스트 3-18 AddStatement를 익명 내부 클래스로 전환
 * @author eunji
 *
 */
public class UserDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	public void add(final User user) throws SQLException{

		//익명 내부 클래스는 구현하는 인터페이스를 생성자처럼 이용해서 오브젝트를 만든다.
		StatementStrategy st = new StatementStrategy() {
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
				
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());
				return ps;
			}
			
		};

		jdbcContextWithStatementStrategy(st);
		
	}
	
	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException{
		Connection c = null;
		PreparedStatement ps = null;
		
		try{
			
			c = dataSource.getConnection();
	
			ps = stmt.makePreparedStatement(c);
			
			ps.executeUpdate();
		}catch(SQLException e){
			throw e;
		}finally {
			if(ps != null){ try{ ps.close(); }catch(SQLException e){} }
			if(c != null){try{c.close(); }catch(SQLException e){} }
			
		}	
		
	}


}


