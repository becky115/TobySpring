package ex1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ex1.domain.User;



public class UserDao2 {
	
	public void add(User user) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "ejlee");
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public void delete(String id) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "ejlee");
		PreparedStatement ps = c.prepareStatement("delete from users where id = ?");
		ps.setString(1, id);
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "ejlee");
		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);

		ResultSet rs = ps.executeQuery();
		rs.next();
	
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao2 dao = new UserDao2();
		dao.delete("id1");
		
		User user = new User();
		user.setId("id1");
		user.setName("name1");
		user.setPassword("pwd1");
		
		dao.add(user);
		
		System.out.println("등록성공:: "+ user.getId());
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.toString());
	}

}
