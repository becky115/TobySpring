package ex3._52;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class UserDao {
	private JdbcTemplate jdbcTemplate;
	
	
	public UserDao() {
		System.out.println("new UserDao");
		ApplicationContext context = new GenericXmlApplicationContext("/ex3/_52/applicationContext.xml");
		setDataSource(context.getBean("dataSource", DataSource.class));
			
	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void deleteAll() {
		this.jdbcTemplate.update("delete from users");
	}

	/**
	 * 리스트 3-53  getAll메소드
	 * @return
	 */
	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from users order by id", 
			new RowMapper<User>() {
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

	public void add(final User user) {
		this.jdbcTemplate.update("insert into users(id, name, password) values(?, ?, ?)", user.getId(), user.getName(), user.getPassword());
	}
}
