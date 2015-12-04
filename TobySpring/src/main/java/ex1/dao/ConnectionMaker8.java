package ex1.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker8 {
	public Connection makeConnection() throws ClassNotFoundException, SQLException;
	
}
