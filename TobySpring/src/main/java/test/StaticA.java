package test;

public class StaticA {
	public static String a = "test";

	
	public void test() {
		StaticA test = new StaticA();
		String q = StaticA.a;
		System.out.println(q);
		StaticA.a = "b";
		
		
		StaticA test2 = new StaticA();
		System.out.println(test2.a);
		
	}

}
