package ex5._33;

import java.util.List;

import ex5._26.Level;
import ex5._26.User;
import ex5._26.UserDao;

public class UserService {
	
		
	UserDao userDao;
	UserLevelUpgradePolicy userLevelUpgradePolicy;
	
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	
	public void setUserLevelUpgradePolicy(UserLevelUpgradePolicy userLevelUpgradePolicy){
		this.userLevelUpgradePolicy = userLevelUpgradePolicy;
	}
	
	public void upgradeLevels(){
		List<User> users = userDao.getAll();
		for(User user: users){
			if(canUpgradeLevel(user)){
				upgradeLevel(user);
			}
		}
	}

	private void upgradeLevel(User user) {
		this.userLevelUpgradePolicy.upgradeLevel(user);
		userDao.update(user);
	}

	private boolean canUpgradeLevel(User user) {
		
		return this.userLevelUpgradePolicy.canUpgradeLevel(user);
	}

	public void add(User user) {
		if(user.getLevel() == null) user.setLevel(Level.BASIC);
		userDao.add(user);
		
	}
}
