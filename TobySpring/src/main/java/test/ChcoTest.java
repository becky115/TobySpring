package test;

import java.util.ArrayList;
import java.util.List;

public class ChcoTest {
	public static void main(String[] args) {
		
		
	
		List<? extends Choco> test = new ArrayList<Choco>();
		
		WhiteChoco a = new WhiteChoco();
		List<WhiteChoco> w = new ArrayList<>();
		w.add(a);
		test = w;
		
		System.out.println(test);
		System.out.println(test.size());
		
		
		
		List<? super Number> test2 = new ArrayList<>();
		test2.add(new Integer(0));
		test2.add(new Float(0.5));
		
		
		
		
		List<Choco> cho = new ArrayList<>();
		List<WhiteChoco> wcho = new ArrayList<>();
		
		
		aa(cho);
		aa(wcho);
		
		WhiteChoco wc = new WhiteChoco();
		System.out.println(wc.b);
		System.out.println(wc.c);
	
	}
	
	
	public static void aa(List<? extends Choco> chocos) {
		System.out.println(chocos);
	}
}
