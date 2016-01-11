package ex1.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ex1.dao.UserDao11;
import ex1.db.ConnectionMaker8;
import ex1.db.CountingConnectionMaker30;
import ex1.db.DConnctionMaker9;

/**
 * CountingConnectionMaker 의존관계가 추가된 DI설정용 클래스
 * @author ejlee
 *
 */
@Configuration
public class CountingDaoFactory31 {
	
	@Bean
	public UserDao11 userDao(){
		return new UserDao11(connectionMaker());
	}
	
	@Bean
	public ConnectionMaker8 connectionMaker(){
		return new CountingConnectionMaker30(realConnectionMaker());
	}
	
	@Bean 
	public ConnectionMaker8 realConnectionMaker(){
		return new DConnctionMaker9();
	}
}
