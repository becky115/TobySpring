package ex1.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ex1.dao.UserDao23;
import ex1.db.ConnectionMaker8;
import ex1.db.DConnctionMaker9;

@Configuration
public class DaoFactory23 {
	
	@Bean
	public UserDao23 userDao(){
		return new UserDao23(connectionMaker());
	}
	
	@Bean
	public ConnectionMaker8 connectionMaker(){
		return new DConnctionMaker9();
	}
}
