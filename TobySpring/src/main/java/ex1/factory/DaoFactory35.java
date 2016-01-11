package ex1.factory;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import ex1.dao.UserDao44;

/**
 * 리스트 1-43 DataSource타입의 dataSource 빈 정의 메소드
 * 리스트 1-44 DataSource타입의 빈을 DI받는 userDao() 빈 정의 메소드
 * @author ejlee
 *
 */
@Configuration
public class DaoFactory35 {
	
	@Bean
	public UserDao44 userDao(){
		UserDao44 userDao = new UserDao44();
		userDao.setDataSource(dataSource());
		return userDao;
	}
	
	@Bean
	public DataSource dataSource(){
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/test");
		dataSource.setUsername("root");
		dataSource.setPassword("eunji");
		
		return dataSource;
	}
}
