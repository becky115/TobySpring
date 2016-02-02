package ex1.testCode;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import ex1.dao.UserDao33;
import ex1.db.ConnectionMaker8;

/**
 * applicationContext.xml (리스트 1-40) 테스트
 * @author ejlee
 *
 */
public class UserDaoTest40 {
	
	 public static void main(String[] args) {
		
		 //ApplicationContext context = new GenericXmlApplicationContext("");//ex1/testCode/applicationContext.xml
		 ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml"); //classpath:**/applicationContext.xml

		 System.out.println(context);
		 UserDao33 userDao = context.getBean("userDao", UserDao33.class);
		 System.out.println(userDao);
		 
		 ConnectionMaker8 connectionMaker = context.getBean("connectionMaker", ConnectionMaker8.class);
		 System.out.println(connectionMaker);
		 
		 /*
		 ApplicationContext context1 = new ClassPathXmlApplicationContext("applicationContext.xml", UserDaoTest40.class);
		 System.out.println(context1);
		 UserDao33 userDao2 = context1.getBean("userDao", UserDao33.class);
		 System.out.println(userDao2);
		 
		 ConnectionMaker8 connectionMaker2 = context1.getBean("connectionMaker", ConnectionMaker8.class);
		 System.out.println(connectionMaker2);*/
//
	}
}
