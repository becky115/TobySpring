package ex3._36;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ex3._35.Calculator;

/**
 * 리스트 3-36 새로운 테스트 메소드를 추가한 CalcSumTest
 * @author eunji
 *
 */
public class CalcSumTest {
	
	Calculator calculator;
	String numFilePath;
	
	@Before public void setUp() {
		this.calculator = new Calculator();
		this.numFilePath = getClass().getResource("numbers.txt").getPath();
	}
	
	@Test public void sumOfNumbers() throws IOException {
		assertThat(this.calculator.calcSum(this.numFilePath), is(10));
	}
	
	@Test public void multiplyOfNumbers() throws IOException {
		assertThat(this.calculator.calcMultiply(this.numFilePath), is(24));
	}
}
