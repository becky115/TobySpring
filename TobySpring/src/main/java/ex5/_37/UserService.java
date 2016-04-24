package ex5._37;

import ex5._26.UserDao;

public class UserService {
	
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECCOMEND_FOR_GOLD = 30;
	
	UserDao userDao;
	
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	
	
	public void upgradeLevels() throws Exception{
		// (1) DB Connection 생성
		// (2) 트랜잭션 시작 
		try{
			//(3) DAO 메소드 호출 
			//(4)) 트랜잭션 커밋
		}catch(Exception e){
			//(5) 트랜잭션 롤백 
			throw e;
		}finally {
			//(6) DB Connection 종료
		}
	}
}
