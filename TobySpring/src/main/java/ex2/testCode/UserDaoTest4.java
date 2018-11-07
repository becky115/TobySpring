package ex2.testCode;

import java.sql.SQLException;


//import static com.xlgames.support.test.ReflectionInjectorUtils.*;
//import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
//import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;



import ex1.dao.UserDao33;


public class UserDaoTest4 {
	public   int a = 0;
	
	@Test
	public void addAndGet() throws ClassNotFoundException, SQLException {
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		System.out.println(context);
		System.out.println("asdas");
//	/	try {
			UserDao33 userDao = context.getBean("userDao", UserDao33.class);

			userDao.get("id1");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
			
			
			//UserDao11 userDao2 = context.getBean("userDao", UserDao11.class);
			//assertThat(userDao2.get("id1"), is("id1"));
			
			assertThat("1", is("1"));
			assertThat("1", is("1"));
			assertThat("1", is("1"));
			assertThat("1", is("1"));
			System.out.println("12");
			//assertThat("1", is("2"));
			a++;
			System.out.println("a1 "+a);
	}
	
	@Test
	public void test() throws InterruptedException {
		System.out.println("test");
		Thread.sleep(5000);
		a++;
		System.out.println("a2 "+a);
	}
	
	@Test
	public void test2() {
		System.out.println("test2");
		a++;
		System.out.println("a3 "+a);
	}
	
	public static void main(String[] args) {
		JUnitCore.main("ex2.testCode.UserDaoTest4");
		
		
	}
}
