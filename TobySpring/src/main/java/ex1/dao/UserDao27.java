package ex1.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ex1.db.ConnectionMaker8;
import ex1.factory.DaoFactory23;

/**
 * 리스트 1-27 의존관계 검색을 이용하는 UserDao생성자
 * @author ejlee
 *
 */
public class UserDao27 {
	
	private ConnectionMaker8 connectionMaker;
	
	public UserDao27() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory23.class);
		this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker8.class);
		System.out.println(this.connectionMaker );
	}
	
	public static void main(String[] args) {
		UserDao27 dao = new UserDao27();
		
	}
}
