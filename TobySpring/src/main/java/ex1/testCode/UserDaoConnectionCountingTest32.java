package ex1.testCode;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ex1.dao.UserDao11;
import ex1.db.CountingConnectionMaker30;
import ex1.factory.CountingDaoFactory31;

/**
 * 리스트 1-32 CountingConnectionMaker에 대한 테스트 클래스
 * @author eunji
 *
 */
public class UserDaoConnectionCountingTest32 {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory31.class);
		
		
		UserDao11 userDao = context.getBean("userDao", UserDao11.class);
		
		userDao.get("id1");
		userDao.get("id1");
		//
		//DAO 사용 코드
		//
		CountingConnectionMaker30 ccm = context.getBean("connectionMaker", CountingConnectionMaker30.class);
		System.out.println(ccm.getCounter());
		
	}
}
