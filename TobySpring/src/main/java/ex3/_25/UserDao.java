package ex3._25;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import ex1.domain.User;
import ex3._8.StatementStrategy;

/**
 * 
 * 리스트 3-25 jdbcContext생성과 DI작업을 수행하는 setDataSource()메소드
 * @author eunji
 *
 */
public class UserDao {
	
	private JdbcContext jdbcContext;
	private DataSource dataSource;

	//수정자 메소드이면서 JdbcContext에 대한 생성, DI작업을 동시에 수행한다.
	public void setDataSource(DataSource dataSource) {
		this.jdbcContext = new JdbcContext(); //JdbcContext생성(Ioc)
		
		this.jdbcContext.setDataSource(dataSource); //의존 오브젝트 주입(DI
		
		this.dataSource = dataSource; //아직 jdbcContext를 적용하지 않은 메소드를 위해 저장해둔다.
	}
	

	public void add(final User user) throws SQLException {
		this.jdbcContext.workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
					
					ps.setString(1, user.getId());
					ps.setString(2, user.getName());
					ps.setString(3, user.getPassword());
					return ps;
				}
			}
		);
	}
	
	public void deleteAll() throws SQLException {
		this.jdbcContext.workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					return c.prepareStatement("delete from users");
				}
			}
		);
	}
	
	public User get(String id) throws SQLException{
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		
		try{
			
			c = dataSource.getConnection();
	
			ps = c.prepareStatement("select * from users where id = ?");
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			rs.next();
			
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}catch(SQLException e){
			throw e;
		}finally {
			if(rs != null){ try{ rs.close(); }catch(SQLException e){} }
			if(ps != null){ try{ ps.close(); }catch(SQLException e){} }
			if(c != null){try{c.close(); }catch(SQLException e){} }
		}	
		
		return user;
	}
}
