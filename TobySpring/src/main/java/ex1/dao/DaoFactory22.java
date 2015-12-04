package ex1.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DaoFactory22 {

	@Bean
	public UserDao22 userDao(){
		//return new UserDao22(connectionMaker());
		return UserDao22.getInstance();
	}
	
	@Bean
	public ConnectionMaker8 connectionMaker(){
		return new DConnctionMaker9();
	}
}
