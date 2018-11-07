package ex1.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ex1.dao.UserDao11;
import ex1.db.ConnectionMaker8;
import ex1.db.DConnctionMaker9;

/**
 * 스프링 빈 팩토리가 사용할 설정정보를 담은 DaoFactoory 클래스
 * @author ejlee
 *
 */
@Configuration
public class DaoFactory18 {

	@Bean
	public UserDao11 userDao() {
		return new UserDao11(connectionMaker());
	}
	

	@Bean
	public ConnectionMaker8 connectionMaker() {
		return new DConnctionMaker9();
	}
	
	@Bean
	public String a() {
		return new String("a");
	}
}
