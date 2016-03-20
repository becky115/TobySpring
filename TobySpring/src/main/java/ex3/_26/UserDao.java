package ex3._26;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import ex3._25.JdbcContext;
import ex3._8.StatementStrategy;

/**
 * 
 * 리스트 3-26 익명 내부 클래스를 사용한 클라이언트 코드
 * @author eunji
 *
 */
public class UserDao {
	
	private JdbcContext jdbcContext;

	//수정자 메소드이면서 JdbcContext에 대한 생성, DI작업을 동시에 수행한다.
	public void setDataSource(DataSource dataSource) {
		this.jdbcContext = new JdbcContext(); //JdbcContext생성(Ioc)
		
		this.jdbcContext.setDataSource(dataSource); //의존 오브젝트 주입(DI
	}
	

	
	public void deleteAll() throws SQLException {
		this.jdbcContext.workWithStatementStrategy(
			new StatementStrategy() {//변하지 않는 콜백 클래스 정의와 오브젝트 생성
				public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
					return c.prepareStatement("delete from users"); //변하는 SQL 문장
				}
			}
		);
	}
	
	
}
