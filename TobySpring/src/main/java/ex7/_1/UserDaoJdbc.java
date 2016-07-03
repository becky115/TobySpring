package ex7._1;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ex7.User;

/**
 * 리스트 7-1
 * @author eunji
 *
 */
public class UserDaoJdbc implements UserDao{
	
	private JdbcTemplate jdbcTemplate;
	
	private String sqlAdd;
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void setSqlAdd(String sqlAdd){
		this.sqlAdd = sqlAdd;
	}

	@Override
	public void add(User user) {
		this.jdbcTemplate.update(this.sqlAdd, user.getId(), user.getName(), user.getPassword(), user.getEmail(),
				user.getLevel().intValue(), user.getLogin(), user.getRecommend());
		
	}

	@Override
	public User get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return 0;
	}
}
