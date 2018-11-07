package ex1.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ex1.dao.UserDao22;
import ex1.db.ConnectionMaker8;
import ex1.db.DConnctionMaker9;


@Configuration
public class DaoFactory22 {

	@Bean
	public UserDao22 userDao() {
		//return new UserDao22(connectionMaker());
		return UserDao22.getInstance();
	}
	
	@Bean
	public ConnectionMaker8 connectionMaker() {
		return new DConnctionMaker9();
	}
}
