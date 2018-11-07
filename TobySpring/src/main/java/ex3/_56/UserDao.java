package ex3._56;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ex3._52.User;

public class UserDao {
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 리스트 3-56 재사용 가능하도록 독립시킨 RowMapper
	 */
	private RowMapper<User> userMapper = new RowMapper<User>() { //ResultSet
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	
	};
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void deleteAll() {
		this.jdbcTemplate.update("delete from users");
	}
	
	
	/**
	 * 리스트 3-57 공유 userMapper를 사용하도록 수정한 get(), getAll()
	 * @param id
	 * @return
	 */
	public User get(String id) {
		return this.jdbcTemplate.queryForObject("select * from users where id = ?", 
			new Object[] {id}, 
			this.userMapper
		);
	}

	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
	}

	public void add(final User user) {
		this.jdbcTemplate.update("insert into users(id, name, password) values(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
	}
	
	public int getCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
	}
}
