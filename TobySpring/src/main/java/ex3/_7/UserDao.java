package ex3._7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;


/**
 * 리스트 3-7 makeStatement()를 구현한 UserDao 서브클래스
 * @author eunji
 *
 */
public abstract class UserDao {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	abstract protected PreparedStatement makeStatement(Connection c) throws SQLException;
	
	public void deleteAll() throws SQLException{
		Connection c = null;
		PreparedStatement ps = null;
		
		try{
			
			c = dataSource.getConnection();
			
			ps = makeStatement(c);
			
			ps.executeUpdate();
		}catch(SQLException e){
			throw e;
		}finally {
			if(ps != null){ try{ ps.close(); }catch(SQLException e){} }
			if(c != null){try{c.close(); }catch(SQLException e){} }
			
		}	
		
	}
	

}
