package ex1.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ex1.dao.UserDao33;
import ex1.db.ConnectionMaker8;
import ex1.db.DConnctionMaker9;

/**
 * 리스트 1-34 수정자 메소드 DI를 사용하는 팩토리 메소드
 * @author ejlee
 *
 */
@Configuration
public class DaoFactory34 {
	
	@Bean
	public UserDao33 userDao() {
		UserDao33 userDao = new UserDao33();
		userDao.setConnectionMaker(connectionMaker());
		return userDao;
	}
	
	@Bean
	public ConnectionMaker8 connectionMaker() {
		return new DConnctionMaker9();
	}
}
