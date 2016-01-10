package ex1.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory31 {
	
	@Bean
	public UserDao11 userDao(){
		//return new UserDao11(getConnectionMaker());
		return new UserDao11(connectionMaker());
	}
	
	@Bean
	public ConnectionMaker8 connectionMaker(){
		return new CountingConnectionMaker30(connectionMaker());
	}
	
	@Bean 
	public ConnectionMaker8 realConnectionMaker(){
		return new DConnctionMaker9();
	}
}
