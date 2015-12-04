package ex1.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 스프링 빈 팩토리가 사용할 설정정보를 담은 DaoFactoory 클래스
 * @author ejlee
 *
 */
@Configuration
public class DaoFactory18 {

	@Bean
	public UserDao11 userDao(){
		return new UserDao11(connectionMaker());
	}
	
	@Bean
	public ConnectionMaker8 connectionMaker(){
		return new DConnctionMaker9();
	}
}
