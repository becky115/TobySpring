package ex5._41;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

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
	
	
	public void upgradeLevels() throws Exception {
		//트랜잭션 동기화 관리자를 이용해 동기화 작업을 초기화한다. 
		TransactionSynchronizationManager.initSynchronization(); 
		//DB커넥션을 생성하고 트랜잭션을 시작한다. 이후의 DAO작업은 모두 여기서 시작한 트랜잭션 안에서 진행된다.
		//DB 커넥션 생성과 동기화를 함께 해주는 유틸리티 메소드
		Connection c = DataSourceUtils.getConnection(dataSource);
		c.setAutoCommit(false);
		
		try {
			List<User> users = userDao.getAll();
			for(User user: users) {
				if(canUpgradeLevel(user)) {
					upgradeLevel(user);
				}
			}
			c.commit();//정상적으로 작업을 마치면 트랜잭션 커밋
		} catch (Exception e) {//예외가 발생하면 롤백한다.
			System.out.println("예외 발생 roll back ");
			c.rollback();
			throw e;
		}finally {
			//스프링 유틸리티 메소드를 이용해 DB커넥션을 안전하게 닫는다.
			DataSourceUtils.releaseConnection(c, dataSource);
			//동기화 작업 종료 및 정리
			TransactionSynchronizationManager.unbindResource(this.dataSource);
			TransactionSynchronizationManager.clearSynchronization();
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
