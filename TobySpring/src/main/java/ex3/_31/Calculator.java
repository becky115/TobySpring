package ex3._31;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 리스트 3-31 처음 만든 Calculator 클래스 코드
 * @author eunji
 *
 */
public class Calculator {
	
	public Integer calcSum(String filePath) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filePath)); //한줄씩 읽기 편하게 BufferedReader로 파일을 가져온다.
		Integer sum = 0;
		String line = null;
		while((line = br.readLine()) != null){//마지막 라인까지 한줄 씩 읽어가면서 숫자를 더한다.
			sum += Integer.valueOf(line);
		}
		br.close(); //한번 연 파일은 반드시 닫아준다.
		
		return sum;
	}
}
