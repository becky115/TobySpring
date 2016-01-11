package ex1.dao;

import ex1.db.ConnectionMaker8;
import ex1.factory.DaoFactory17;

/**
 * 
 * 리스트 1-26 DaoFactory를 이용하는 생성자
 * @author eunji
 *
 */
public class UserDao26 {
	private ConnectionMaker8 connectionMaker;
	
	public UserDao26() {
		DaoFactory17 daoFactory = new DaoFactory17();
		this.connectionMaker = daoFactory.connectionMaker();
	}
	
	
}
