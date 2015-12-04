package ex1.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoTest22 {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		
		System.out.println(UserDao22.getInstance());
		System.out.println(UserDao22.getInstance());
		System.out.println(UserDao22.getInstance().get("id1").toString());
		
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory22.class);
		UserDao22 userDao1 = context.getBean("userDao", UserDao22.class);
		UserDao22 userDao2 = context.getBean("userDao", UserDao22.class);
		
		System.out.println(userDao1);
		System.out.println(userDao2);
		
	}
}
