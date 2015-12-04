package ex1.dao;

/**
 * DAO 생성 메소드의 추가로 인해 발생되는 중복
 * {@link DaoFactory14}
 * @author ejlee
 *
 */
public class DaoFactory16 {
	
	public UserDao11 userDao(){
		ConnectionMaker8 connectionMaker8 = new DConnctionMaker9();
		
		UserDao11 userDao = new UserDao11(connectionMaker8);
		return userDao;
	}
	
	public AccountDao accountDao(){
		ConnectionMaker8 connectionMaker8 = new DConnctionMaker9();
		
		AccountDao accountDao = new AccountDao(connectionMaker8);
		return accountDao;
	}
	
	public MessageDao messageDao(){
		ConnectionMaker8 connectionMaker8 = new DConnctionMaker9();
		
		MessageDao messageDao = new MessageDao(connectionMaker8);
		return messageDao;
	}
	
	
}
