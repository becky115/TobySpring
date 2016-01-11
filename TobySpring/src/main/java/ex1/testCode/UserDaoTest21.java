package ex1.testCode;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ex1.dao.UserDao11;
import ex1.factory.DaoFactory18;

/**
 * 스프링 컨텍스트로부터 가져온 오브젝트 출력 코드
 * @author ejlee
 *
 */
public class UserDaoTest21 {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory18.class);
		
		UserDao11 userDao1 = context.getBean("userDao", UserDao11.class);
		UserDao11 userDao2 = context.getBean("userDao", UserDao11.class);
		
		System.out.println(context.getBean(UserDao11.class));
		System.out.println("userDao1: "+userDao1);
		System.out.println("userDao2: "+userDao2);
	}
}
