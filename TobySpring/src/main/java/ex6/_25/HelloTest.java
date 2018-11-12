package ex6._25;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Proxy;

import org.junit.Test;

import ex6._18.Hello;
import ex6._18.HelloTarget;

public class HelloTest {
	
	@Test
	public void test() {
		Hello proxiedHello = (Hello) Proxy.newProxyInstance(
				getClass().getClassLoader(), // 동적으로 생성되는 다이내믹 프록시 클래스의 로딩에 사용될 클래스 로더  
				new Class[] { Hello.class }, // 구현할인터페이스 
				new UppercaseHandler(new HelloTarget())); // 부가기능과 위임 코드를 담은 InvocationHandler
		
		assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
		
	}
}
