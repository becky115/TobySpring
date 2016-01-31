package ex3._2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * 리스트 3-2 예외 발생 시에도 리소스를 반환하도록 수정한 deleteAll()
 * @author eunji
 *
 */
public class UserDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void deleteAll() throws SQLException{
		Connection c = null;
		PreparedStatement ps = null;
		
		try{
			//31-34예외가 발생할 가능성이 있는 코드를 모두 try블록으로 묶어준다.
			c = dataSource.getConnection();
			ps = c.prepareStatement("delete from users");
			ps.executeUpdate();
		}catch(SQLException e){
			//예외가 발생했을 떄 부가적인 작업을 해줄 수 있도록 catch블록을 둔다.
			//아직은 예외를 다시 메소드 밖으로 던지는 것밖에 없다.
			throw e;
		}finally {//finally이므로 try 블록에서 예외가 발생했을 때나 안했을 때나 모두 실행된다.
			if(ps != null){
				try{
					ps.close();
				}catch(SQLException e){ 
					//ps.close() 메소드에서도 SQL Exception이 발생할 수 있기 때문에
					//이를 잡아줘야 한다. 그렇지 않으면 Connection을 close()하지 못하고
					//메소드를 빠져나갈 수 있다.
					
				}
			}
			
			if(c != null){
				try{
					c.close(); //--> Connection 반환
				}catch(SQLException e){
					
				}
			}
			
		}
	
		
	
		
	}
}
