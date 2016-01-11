package ex1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao5{

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "ejlee");

		return c;
	}
	
//	@Override
//	public User get(String id) throws ClassNotFoundException, SQLException {
//		System.out.println("getId: "+ id);
//		return super.get(id);
//	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		NUserDao userDao = new NUserDao();
		userDao.get("id1");
		//userDao.add(new User())
	}
	
}
