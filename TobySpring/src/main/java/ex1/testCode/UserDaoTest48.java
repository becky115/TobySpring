package ex1.testCode;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import ex1.dao.UserDao44;

public class UserDaoTest48 {

	public static void main(String[] args) {
		
	
		ApplicationContext context = new GenericXmlApplicationContext();//classpath:**/applicationContext.xml
	
		 DataSource dataSource = context.getBean("dataSource", SimpleDriverDataSource.class);
		 System.out.println(dataSource);
		 
		 UserDao44 userDao2 = context.getBean("userDao2", UserDao44.class);
		 System.out.println(userDao2);
	}
}
