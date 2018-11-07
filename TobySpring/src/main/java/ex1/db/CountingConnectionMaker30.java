package ex1.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 리스트 1-30 연결횟수 카운팅이 있는 클래스
 * @author eunji
 *
 */
public class CountingConnectionMaker30 implements ConnectionMaker8{
	
	int counter = 0;
	private ConnectionMaker8 realConnectionMaker;
	
	public CountingConnectionMaker30(ConnectionMaker8 realConnectionMaker) {
		this.realConnectionMaker = realConnectionMaker;
	}
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.counter ++;
		return realConnectionMaker.makeConnection();
	}
	
	public int getCounter() {
		return this.counter;
	}

}
