package ex6._23;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import ex6._18.Hello;

public class UppercaseHandler implements InvocationHandler {
	
	Hello target;
	
	public UppercaseHandler(Hello target) {
		this.target = target;
	}	

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String res = (String) method.invoke(target, args);
		return res.toUpperCase();
	}
	

}
