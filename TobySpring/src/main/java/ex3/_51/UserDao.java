package ex3._51;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ex3._25.User;

/**
 * 리스트 3-51 queryForObject()와 RowMapper를 적용한 get() 메소드
 * @author eunji
 *
 */
public class UserDao {
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public User get(String id) {
		return this.jdbcTemplate.queryForObject("select * from users where id = ?", 
			new Object[] {id}, //sql에 바인딩할 파라미터 값, 가변인자 대신 배열을 사용한다. 
			new RowMapper<User>() { //ResultSet
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setId(rs.getString("id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					return user;
				}
			
			}
		);
	}

}
