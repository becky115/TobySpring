package ex1.factory;

import ex1.dao.AccountDao;
import ex1.dao.MessageDao;
import ex1.dao.UserDao11;
import ex1.db.ConnectionMaker8;
import ex1.db.DConnctionMaker9;

/**
 * {@link DaoFactory16 } 리스트 1-17 생성 오브젝트 코드 수정
 * @author ejlee
 *
 */
public class DaoFactory17 {
	public UserDao11 userDao() {
		UserDao11 userDao = new UserDao11(connectionMaker());
		return userDao;
	}
	
	public AccountDao accountDao() {
		AccountDao accountDao = new AccountDao(connectionMaker());
		return accountDao;
	}
	
	public MessageDao messageDao() {
		MessageDao messageDao = new MessageDao(connectionMaker());
		return messageDao;
	}
	
	public ConnectionMaker8 connectionMaker() {
		return new DConnctionMaker9();
	}
}
