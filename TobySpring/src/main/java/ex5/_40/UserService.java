package ex5._40;

import java.sql.Connection;

import ex5._26.User;

public class UserService {
	
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECCOMEND_FOR_GOLD = 30;
	
	UserDao userDao;
	
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	/*
	public void upgradeLevels() {
		Connection c = ...;
		try{
			upgradeLevel(c, user);
		}catch(Exception e){
			
		}finally {
			
		}
	}

	protected void upgradeLevel(Connection c, User user) {
		user.upgradeLevel();
		userDao.update(c, user);
	}


	
	*/
	
	interface UserDao{
		public void update(Connection c, User user);
	}

}
