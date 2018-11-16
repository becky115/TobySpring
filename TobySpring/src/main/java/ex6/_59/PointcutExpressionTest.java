package ex6._59;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

public class PointcutExpressionTest {

	@Test
	public void methodSignaturePointcut() throws NoSuchMethodException, SecurityException {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(public int ex6._59.Target.minus(int,int) throws java.lang.RuntimeException)");
		
		//public int ex6._59.Target.minus(int, int) throws java.lang.RuntimeException
		//public int ex6._59.Target.minus(int, int) throws java.lang.RuntimeException
		
		// Target.minus();
		System.out.println(pointcut.getClassFilter());
		assertThat(pointcut.getClassFilter().matches(Target.class) &&
				pointcut.getMethodMatcher().matches(
						Target.class.getMethod("minus", int.class, int.class), null), is(true));
		
		assertThat(pointcut.getClassFilter().matches(Target.class) &&
				pointcut.getMethodMatcher().matches(
						Target.class.getMethod("plus", int.class, int.class), null), is(false));
		
		assertThat(pointcut.getClassFilter().matches(Bean.class) &&
				pointcut.getMethodMatcher() .matches(Target .class.getMethod("method") ,
				null), is(false));
	}
	
	@Test
	public void pointcut() throws Exception {
		targetClassPointcutMatches("execution(* *(..))", true, true, true, true, true, true);
	}
	
	public void targetClassPointcutMatches(String expression, boolean...expected) throws Exception {
		pointMatches(expression, expected[0], Target.class, "hello");
		pointMatches(expression, expected[1], Target.class, "hello", String.class);
		pointMatches(expression, expected[2], Target.class, "plus", int.class, int.class);
		pointMatches(expression, expected[3], Target.class, "minus", int.class, int.class);
		pointMatches(expression, expected[4], Target.class, "method");
		pointMatches(expression, expected[5], Target.class, "method");
	}
	
	/**
	 * 6-62 포인트컷과 메소드를 비교해주는 테스트 헬퍼 메소드
	 * @param expression
	 * @param expected
	 * @param clazz
	 * @param methodName
	 * @param args
	 * @throws Exception
	 */
	public void pointMatches(String expression, Boolean expected, Class<?> clazz, String methodName, Class<?>...args) throws Exception {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(expression);
		
		// 포인트컷의 클래스 필터와 메소드 매처 두가지를 동시에 만족하는지 확인
		assertThat(pointcut.getClassFilter().matches(clazz)
				&& pointcut.getMethodMatcher().matches(clazz.getMethod(methodName, args), null), is(expected));
		
	}
}
