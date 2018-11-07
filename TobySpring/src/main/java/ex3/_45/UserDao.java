package ex3._45;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

public class UserDao {
	
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 리스트 3-45 JdbcTemplate의 초기화를 위한 코드
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * 리스트 3-46 JdbcTemplate을 적용한 deleteAll()메소드
	 */
	public void deleteAll() {
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement("delete from users");
			}
		});
	}
	
	/**
	 * 리스트 3-47 내장 콜백을 사용하는 update()로 변경한 deleteAll()메소드
	 */
	public void deleteAll2() {
		this.jdbcTemplate.update("delete from users");
	}
	
}
