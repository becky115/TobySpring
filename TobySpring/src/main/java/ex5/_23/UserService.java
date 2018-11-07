package ex5._23;

import java.util.List;

import ex5._3.Level;
import ex5._3.User;

public class UserService {
	
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void upgradeLevels() {
		List<User> users = userDao.getAll();
		for(User user: users) {
			if(canUpgradeLevel(user)) {
				upgradeLevel(user);
			}
		}
	}

	private void upgradeLevel(User user) {
		if(user.getLevel() == Level.BASIC) user.setLevel(Level.SILVER);
		else if(user.getLevel() == Level.SILVER) user.setLevel(Level.GOLD);
		
		userDao.update(user);
	}

	private boolean canUpgradeLevel(User user)  {
		Level currentLevel = user.getLevel();
		switch(currentLevel) {
			//레벨별로 구분해서 조건을 판단한다.ㄴ
			case BASIC: return (user.getLogin() >= 50);
			case SILVER: return (user.getRecommend() >= 30);
			case GOLD: return false;
			//현재 로직에서 다룰 수 없는 레벨이 주어지면 예외를 발생시킨다.
			//새로운 레벨이 추가되고 로직을 수정하지 않으면 에러가 나서 확인할 수 있다.
			default: throw new IllegalArgumentException("Unknown level: "+ currentLevel);
		}
	}

	public void add(User user) {
		if(user.getLevel() == null) user.setLevel(Level.BASIC);
		userDao.add(user);
		
	}
}
