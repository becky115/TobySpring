package ex4;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class UserDaoTest {
	
	@Test
	public void addAndGet() throws ClassNotFoundException, SQLException {
		
		ApplicationContext context = new GenericXmlApplicationContext("/ex4/applicationContext.xml");
		
		UserDao userDao = context.getBean("userDao", UserDao.class);
		
		userDao.deleteAll();
		
		User user = new User();
		user.setId("gyumee");
		user.setName("박성철");
		user.setPassword("springno1");
		
		userDao.add(user);
		
		User user2 = userDao.get(user.getId());
		
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
		
		
	}

	
	public static void main(String[] args) {
		System.out.println("test");
		JUnitCore.main("ex4.UserDaoTest");
		
		
	}
}
