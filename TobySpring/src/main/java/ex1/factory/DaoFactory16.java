package ex1.factory;

import ex1.dao.AccountDao;
import ex1.dao.MessageDao;
import ex1.dao.UserDao11;
import ex1.db.ConnectionMaker8;
import ex1.db.DConnctionMaker9;

/**
 * DAO 생성 메소드의 추가로 인해 발생되는 중복
 * {@link DaoFactory14}
 * @author ejlee
 *
 */
public class DaoFactory16 {
	
	public UserDao11 userDao() {
		ConnectionMaker8 connectionMaker8 = new DConnctionMaker9();
		
		UserDao11 userDao = new UserDao11(connectionMaker8);
		return userDao;
	}
	
	public AccountDao accountDao() {
		ConnectionMaker8 connectionMaker8 = new DConnctionMaker9();
		
		AccountDao accountDao = new AccountDao(connectionMaker8);
		return accountDao;
	}
	
	public MessageDao messageDao() {
		ConnectionMaker8 connectionMaker8 = new DConnctionMaker9();
		
		MessageDao messageDao = new MessageDao(connectionMaker8);
		return messageDao;
	}
	
	
}
