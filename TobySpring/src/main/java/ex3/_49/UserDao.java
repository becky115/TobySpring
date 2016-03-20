package ex3._49;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;

public class UserDao {
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * 리스트 3-49 JdbcTemplate를 이용해 만든 getCount();
	 * @param dataSource
	 */
	public int getCount(){
		return this.jdbcTemplate.query(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement("select count(*) from users");
			}
		}, new ResultSetExtractor<Integer>() {
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				return rs.getInt(1);
			}
			
		});
	}
	
	/**
	 * 리스트 3-49 JdbcTemplate를 이용해 만든 getCount();
	 * @param dataSource
	 */
	public int getCount2(){
		//return this.jdbcTemplate.queryForInt("select count(*) from users");
		return this.jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
	}
}
