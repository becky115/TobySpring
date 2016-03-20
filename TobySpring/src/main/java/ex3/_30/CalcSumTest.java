package ex3._30;




import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;


import java.io.IOException;

import org.junit.Test;

import ex3._31.Calculator;

/**
 * 리스트3-30 파일의 숫자 합을 계산하는 코드의 테스트
 * @author eunji
 *
 */
public class CalcSumTest {

	@Test
	public void sumOfNumbers() throws IOException{
		Calculator calculator = new Calculator();
		
		int sum = calculator.calcSum(getClass().getResource("numbers.txt").getPath());
		assertThat(sum, is(10));
	}
}
