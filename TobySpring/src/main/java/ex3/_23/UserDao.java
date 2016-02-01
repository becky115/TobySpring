package ex3._23;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import ex1.domain.User;
import ex3._8.StatementStrategy;


public class UserDao {
	
	private JdbcContext jdbcContext;
	
	private DataSource dataSource;

	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(final User user) throws SQLException {
		this.jdbcContext.workWithStatementStrategy(
			new StatementStrategy() {
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					PreparedStatement ps = c.prepareStatement("insert into users(id, name, password values(?, ?, ?)");
					
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
