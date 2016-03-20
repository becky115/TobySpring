package ex3._27;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import ex3._25.JdbcContext;
import ex3._8.StatementStrategy;

/**
 * 
 * 리스트 3-27 변하지 않는 부분을 분리시킨 deleteAll 메소드
 * @author eunji
 *
 */
public class UserDao {
	
	private JdbcContext jdbcContext;
	public void setDataSource(DataSource dataSource) {
		this.jdbcContext = new JdbcContext(); //JdbcContext생성(Ioc)
		this.jdbcContext.setDataSource(dataSource); //의존 오브젝트 주입(DI
	}
	

	
	public void deleteAll() throws SQLException {
		executeSql("delete from users");
	}



	private void executeSql(final String query) throws SQLException {
		this.jdbcContext.workWithStatementStrategy(
			new StatementStrategy() {//변하지 않는 콜백 클래스 정의와 오브젝트 생성
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					return c.prepareStatement(query); 
				}
			}
		);
	}
	
	
}
