package ex1.dao;

import ex1.db.ConnectionMaker8;

/**
 * 리스트1-33 수정자 메소드 DI방식을 사용한 UserDao
 * @author ejlee
 *
 */
public class UserDao33 {
	
	private ConnectionMaker8 connectionMaker;

	public void setConnectionMaker(ConnectionMaker8 connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
}
