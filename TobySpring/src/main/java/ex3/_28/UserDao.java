package ex3._28;

import java.sql.SQLException;

import javax.sql.DataSource;

import ex1.domain.User;


public class UserDao {
	
	private JdbcContext jdbcContext;
	public void setDataSource(DataSource dataSource) {
		this.jdbcContext = new JdbcContext(); //JdbcContext생성(Ioc)
		this.jdbcContext.setDataSource(dataSource); //의존 오브젝트 주입(DI
	}
	

	/**
	 * 리스트 3-29 JdbcContext로 옮긴 executeSql()을 사용하는 deleteAll메소드
	 * @throws SQLException
	 */
	public void deleteAll() throws SQLException {
		this.jdbcContext.executeSql("delete from users");
	}
	
	
	public void add(final User user) throws SQLException {
		this.jdbcContext.executeSql2("insert into users(id, name, password) values(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
	}
}
