package ex7._4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ex7.Level;
import ex7.User;
/**
 * 리스트 7-4
 * @author eunji
 *
 */
public class UserDaoJdbc implements UserDao {
	
	private JdbcTemplate jdbcTemplate;
	
	private Map<String, String> sqlMap;
	
	private RowMapper<User> userMapper = new RowMapper<User>() { //ResultSet
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setLevel(Level.valueOf(rs.getInt("level")));
			user.setLogin(rs.getInt("login"));
			user.setRecommend(rs.getInt("recommend"));
			user.setEmail(rs.getString("email"));
			return user;
		}
	
	};
	
	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	@Override
	public void add(User user) {
		this.jdbcTemplate.update(this.sqlMap.get("add"), user.getId(), user.getName(), user.getPassword(), user.getEmail(),
				user.getLevel().intValue(), user.getLogin(), user.getRecommend());
	}

	@Override
	public User get(String id) {
		return this.jdbcTemplate.queryForObject(this.sqlMap.get("get"), 
				new Object[] {id}, 
				this.userMapper
		);
	}

	@Override
	public List<User> getAll() {
		return this.jdbcTemplate.query(this.sqlMap.get("getAll"), this.userMapper);
	}

	@Override
	public void deleteAll() {
		this.jdbcTemplate.update(this.sqlMap.get("deleteAll"));
	}

	@Override
	public int getCount() {
		return this.jdbcTemplate.queryForObject(this.sqlMap.get("getCount"), Integer.class);
	}

	@Override
	public int update(User user) {
		return this.jdbcTemplate.update(this.sqlMap.get("update"),
				user.getName(), user.getPassword(), user.getEmail(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getId());
	}
}
