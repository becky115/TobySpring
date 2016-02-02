package ex3._23;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoTest {
	
	 public static void main(String[] args) throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("/ex3/_23/applicationContext.xml"); 

		System.out.println(context);
		UserDao userDao = context.getBean("userDao", UserDao.class);
		//System.out.println(userDao);
		
		int i = 2;
		User user = new User();
		user.setId("id"+i);
		user.setName("name"+i);
		user.setPassword("password"+i);
		userDao.add(user);
		
		user = userDao.get("id"+i);
		System.out.println(user);
	}
}
