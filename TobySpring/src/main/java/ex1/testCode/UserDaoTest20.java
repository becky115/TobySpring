package ex1.testCode;

import org.junit.Test;

import ex1.dao.UserDao11;
import ex1.factory.DaoFactory18;

/**
 * 직접 생성한 {@link DaoFactory18} 오브젝트 출력 코드
 * @author ejlee
 *
 */
public class UserDaoTest20 {
	
	public static void main(String[] args) {
		
		DaoFactory18 daoFactory = new DaoFactory18();
		
		UserDao11 userDao1 = daoFactory.userDao();

		System.out.println("userDao1: "+userDao1);
		UserDao11 userDao2 = daoFactory.userDao();
	
		System.out.println("userDao2: "+userDao2);
	}
	
	@Test
	public void b(){
		
	}
}
