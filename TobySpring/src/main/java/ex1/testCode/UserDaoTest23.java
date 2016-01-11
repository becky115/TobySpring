package ex1.testCode;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ex1.dao.UserDao23;
import ex1.factory.DaoFactory23;

public class UserDaoTest23 {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory23.class);
		
		
		UserDao23 userDao = context.getBean("userDao", UserDao23.class);
		
		System.out.println(userDao.get("id1"));
		
	}
}
