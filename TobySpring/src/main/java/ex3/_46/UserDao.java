package ex3._46;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import ex3._25.User;

public class UserDao {
	
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 리스트 3-45 JdbcTemplate의 초기화를 위한 코드
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public void add(final User user){
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				/**
				 * 리스트 3-48 add() 메소드의 콜백 내부
				 */
				PreparedStatement ps = con.prepareStatement("inserst into users(id, name, password) values(?, ?, ?)");
				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());
				return ps;
			}
		});
		
	}
	
	public void add2(final User user){
		this.jdbcTemplate.update("inserst into users(id, name, password) values(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
	}
	
}
