package ex6._43;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 6-43 트랜잭션 어드바이스 
 * @author ejlee
 *
 */
public class TransactionAdvice implements MethodInterceptor {
	
	PlatformTransactionManager transactionManager;
	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			Object ret = invocation.proceed();
			this.transactionManager.commit(status);
			return ret;
		} catch (RuntimeException e) {
			this.transactionManager.rollback(status);
			throw e;
		}
	}

}
