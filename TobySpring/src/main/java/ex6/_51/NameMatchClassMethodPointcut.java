package ex6._51;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.util.PatternMatchUtils;

/**
 * 6-51클래스 필터가 포함된 포인트컷 
 * @author ejlee
 *
 */
public class NameMatchClassMethodPointcut extends NameMatchMethodPointcut {
	
	public void setMappedClassName(String mappedClassName) {
		System.out.println("mappedClassName: " + mappedClassName);
		this.setClassFilter(new SimpleClassFilter(mappedClassName));
	}
	
	static class SimpleClassFilter implements ClassFilter {
		String mappedName;

		public SimpleClassFilter(String mappedName) {
			this.mappedName = mappedName;
		}

		@Override
		public boolean matches(Class<?> clazz) {
			return PatternMatchUtils.simpleMatch(mappedName, clazz.getSimpleName());
		}
		
	}
}
