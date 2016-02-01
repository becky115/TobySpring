package ex3._11;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import ex3._8.StatementStrategy;

/**
 * 리스트 3-11 메소드로 분리한 try/catch/finally 컨텍스트 코드
 * @author eunji
 *
 */
public class UserDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * @param stmt //클라이언트가 컨텍스트를 호출할 때 넘겨줄 전략 파라미터
	 * @throws SQLException
	 */
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


