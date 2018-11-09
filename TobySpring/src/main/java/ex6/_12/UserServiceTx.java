package ex6._12;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ex6._1.User;

public class UserServiceTx implements UserService {
	
	UserService userService;
	
	private PlatformTransactionManager transactionManager;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public void add(User user) {
		this.userService.add(user);
	}

	public void upgradeLevels() {
		TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try { 
			this.userService.upgradeLevels();
			
			this.transactionManager.commit(status);//트랜잭션 커밋
		} catch (RuntimeException e) {//예외가 발생하면 롤백한다.
			System.out.println("예외 발생 roll back ");
			this.transactionManager.rollback(status);//트랜잭션 롤백
			throw e;
		}
	}

}
