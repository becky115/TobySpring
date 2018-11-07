package ex5._44;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class JtaTransaction {
	public static final String USER_TX_JNDI_NAME = "javax.transaction.UserTransaction";
	
	public static void main(String[] args) throws Exception {
		DataSource dataSource = new SimpleDriverDataSource();
		
		InitialContext ctx = new InitialContext();
		UserTransaction tx = (UserTransaction) ctx.lookup(USER_TX_JNDI_NAME);
		//JNDI를 이용해 서버의 UserTransaction 오브젝트를 가져온다 
		
		tx.begin();
		
		Connection c = dataSource.getConnection(); //JNDI로 가져온 dataSource를 사용해야한다.
		try {
			//데이터 액세스 코드
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}finally {
			c.close();
		}
	}
}
