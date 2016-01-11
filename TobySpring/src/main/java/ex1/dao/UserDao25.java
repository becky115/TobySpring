package ex1.dao;

import ex1.db.ConnectionMaker8;

/**
 * 리스트1-25 의존관계 주입을 위한 코드
 * @author eunji
 *
 */
public class UserDao25 {
	
	private ConnectionMaker8 connectionMaker;

	public UserDao25(ConnectionMaker8 connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
}
