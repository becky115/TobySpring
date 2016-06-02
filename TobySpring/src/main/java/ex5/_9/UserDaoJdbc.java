package ex5._9;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ex5._3.Level;
import ex5._3.User;

public class UserDaoJdbc implements UserDao{
	private JdbcTemplate jdbcTemplate;

	private RowMapper<User> userMapper = new RowMapper<User>(){ //ResultSet
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setLevel(Level.valueOf(rs.getInt("level")));
			user.setLogin(rs.getInt("loign")); //--> TODO 
			user.setRecommend(rs.getInt("recommend"));
			return user;
		}
	
	};
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void deleteAll(){
		this.jdbcTemplate.update("delete from users");
	}
	
	public User get(String id){
		return this.jdbcTemplate.queryForObject("select * from users where id = ?", 
			new Object[] {id}, 
			this.userMapper
		);
	}

	public List<User> getAll(){
		return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
	}

	public void add(final User user) {
		System.out.println(user.getId()+","+ user.getName()+","+ user.getPassword()+","+ user.getLevel().intValue()+","+ user.getLogin()+","+ user.getRecommend());
		this.jdbcTemplate.update("insert into users(id, name, password, level, login, recommend) values(?, ?, ?, ?, ?, ?) "
				, user.getId(), user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend());
	}
	
	public int getCount(){
		return this.jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
	}


}
