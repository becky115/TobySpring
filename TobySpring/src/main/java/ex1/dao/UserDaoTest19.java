package ex1.dao;


import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ex1.domain.User;

public class UserDaoTest19 {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory18.class);
		System.out.println(context);
		UserDao11 dao = context.getBean("userDao", UserDao11.class);
		
		User user = dao.get("id1");
		System.out.println(user);
	}
}
