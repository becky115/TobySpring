package ex5._33;

import ex5._26.User;

public interface UserLevelUpgradePolicy {

	boolean canUpgradeLevel(User user);
	void upgradeLevel(User user);
}
