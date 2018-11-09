package ex6._12;

import java.util.List;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import ex5._50.Level;
import ex6._1.User;

public class UserServiceImpl implements UserService {
	
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECCOMEND_FOR_GOLD = 30;
	
	private MailSender mailSender;
	
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void upgradeLevels() {
		List<User> users = userDao.getAll();
		
		for(User user: users) {
			
			if(canUpgradeLevel(user)) {
				System.out.println(user.getId());
				upgradeLevel(user);
			}
		}
	}
	
	protected void upgradeLevel(User user) {
		//System.out.println("UserService upgradeLevel......");
		user.upgradeLevel();
		userDao.update(user);
		sendUpgradeEmail(user);
	}

	private void sendUpgradeEmail(User user) {
		//props.put("mail.smtp.starttls.enable","true");  이 부분을
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setTo(user.getEmail());
		mailMessage.setFrom("navy@mnd.mil");
		mailMessage.setSubject("Upgrade 안내");
		mailMessage.setText("사용자님의 등급이 "+user.getLevel().name() + "로 업그레이드 되었습니다.");

		this.mailSender.send(mailMessage);
		
	}

	private boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevel();
		switch(currentLevel) {
			case BASIC: return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
			case SILVER: return (user.getRecommend() >= MIN_RECCOMEND_FOR_GOLD);
			case GOLD: return false;
			default: throw new IllegalArgumentException("Unknown level: "+ currentLevel);
		}
	}
	
	public void add(User user) {
		if(user.getLevel() == null) user.setLevel(Level.BASIC);
		userDao.add(user);
		
	}
	
}
