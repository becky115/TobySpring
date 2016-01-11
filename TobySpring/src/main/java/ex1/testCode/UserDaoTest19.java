package ex1.testCode;


import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ex1.dao.UserDao11;
import ex1.db.ConnectionMaker8;
import ex1.domain.User;
import ex1.factory.DaoFactory18;

public class UserDaoTest19 {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory18.class);
//		System.out.println("name: "+ context.getApplicationName());
//		System.out.println("count: "+ context.getBeanDefinitionCount());
	
//		System.out.println(context.getBean(UserDao11.class));
		
//		for(int i=0; i<context.getBeanDefinitionNames().length; i++){
//			String  a= context.getBeanDefinitionNames()[i];
//			System.out.println("beanName: "+ a);
//		}
//		
		System.out.println(context.containsBean("userDao"));

		UserDao11 dao = context.getBean("userDao", UserDao11.class);
		
		User user = dao.get("id1");
		System.out.println(user);
		

		ConnectionMaker8 a = context.getBean("connectionMaker", ConnectionMaker8.class);
		System.out.println(a.makeConnection());
		System.out.println(a);
	}
}
