package ex3._44;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ex3._43.Calculator;

/**
 * 리스트3-44 concatenate()메소드에 대한 테스트
 * @author eunji
 *
 */
public class CalcTest {
	
	Calculator calculator;
	String numFilePath;
	
	@Before public void setUp() {
		this.calculator = new Calculator();
		this.numFilePath = getClass().getResource("numbers.txt").getPath();
	}

	@Test
	public void concatenateStrings() throws IOException {
		assertThat(this.calculator.concatenate(this.numFilePath), is("1234"));
	}
}
