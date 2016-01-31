package ex3._3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * 리스트 3-3 JDBC 예외처리를 적용한 getCount() 메소드
 * @author eunji
 *
 */
public class UserDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public int getCount() throws SQLException{
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		

		try{
			c = dataSource.getConnection();
			ps = c.prepareStatement("select count(*) from users");
			
			//34-36 ResultSeet도 다양한 SQLException이 발생할 수 있는 코드이므로 try블록 안에 둬야한다.
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		}catch(SQLException e){
			throw e;
		}finally {
			//42-48 만들어진 ResultSet을 닫아주는 기능.
			//close는 만들어진 순서의 반대로 하는것이 원칙이다.
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException e){ 
					
				}
			}
			
			if(ps != null){
				try{
					ps.close();
				}catch(SQLException e){
					
				}
			}
			
			if(c != null){
				try{
					c.close();
				}catch(SQLException e){
					
				}
			}
		
		}
	
	}
}
