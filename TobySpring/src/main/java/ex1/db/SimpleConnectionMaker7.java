package ex1.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 독립시킨 DB연결 기능인 SimpleConnectionMaker7
 * @author ejlee
 *
 */
public class SimpleConnectionMaker7 {
	
	public Connection makeNewConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "ejlee");
		
		return c;
		
	}
}
