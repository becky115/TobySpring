package ex1.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ex1.db.ConnectionMaker8;
import ex1.db.LocalDBConnectionMaker;

/**
 * 리스트 1-28 개발용 ConnectionMaker 생성코드
 * @author ejlee
 *
 */
@Configuration
public class DaoFactory28 {
	
	@Bean
	public ConnectionMaker8 connectionMaker(){
		return new LocalDBConnectionMaker();
	}
}
