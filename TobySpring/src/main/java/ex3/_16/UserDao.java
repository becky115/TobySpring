package ex3._16;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import ex3._14.AddStatement;
import ex3._14.User;
import ex3._8.StatementStrategy;

/**
 * 리스트 3-16 add()메소드 내의 로컬 클래스로 이전한 AddStatement
 * @author eunji
 *
 */
public class UserDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public void add(User user) throws SQLException{
		
		//add()메소드 내부에 선언된 로컬클래스다.
		class AddStatement implements StatementStrategy{
			User user;
			
			public AddStatement(User user){
				this.user = user;
			}

			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("insert into users(id, name, password values(?, ?, ?)");
				
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());
				return ps;
			}
			
		}
		StatementStrategy st = new AddStatement(user);
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


