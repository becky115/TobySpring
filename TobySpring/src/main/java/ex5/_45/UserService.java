package ex5._45;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ex5._26.Level;
import ex5._26.User;
import ex5._26.UserDao;

public class UserService {
	
	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECCOMEND_FOR_GOLD = 30;
	
	private DataSource dataSource;
	
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setDataSource(DataSource dataSource) {//Connection을 생성할때 사용할 DataSource를 DI받도록 한다. 
		this.dataSource = dataSource;
	}
	
	
	public void upgradeLevels() {
		//Jdbc 트랜잭션 추상 오브젝트 생성
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		
		//트랜잭션 시작
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			//트랜잭션 안에서 진행되는 작업 
			List<User> users = userDao.getAll();
			for(User user: users) {
				if(canUpgradeLevel(user)) {
					upgradeLevel(user);
				}
			}
			transactionManager.commit(status);//트랜잭션 커밋
		} catch (RuntimeException e) {//예외가 발생하면 롤백한다.
			System.out.println("예외 발생 roll back ");
			transactionManager.rollback(status);//트랜잭션 커밋
			throw e;
		}

	}
	
	protected void upgradeLevel(User user) {
		user.upgradeLevel();
		userDao.update(user);
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
