package test;

public class Equ {
	
	public static void main(String[] args) {
		String a = "b";
		String b = "b";
		
		String c = a;
		
		System.out.println(a.hashCode()+"-"+ b.hashCode());
		System.out.println( a == b);
		System.out.println(a.equals(b));
		
		System.out.println("------");
		System.out.println(a.hashCode()+"-"+ c.hashCode());
		System.out.println( a == c);
		System.out.println(a.equals(c));
		
		
		
		String d = new String("a");
		String e = new String("a");
		
		System.out.println("------");
		System.out.println(d.hashCode()+"-"+ e.hashCode());
		System.out.println( d == e);
		System.out.println(d.equals(e));
	
		
	}
}
