package ex3._32;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 리스트 3-32 try/catch/finally를 적용한 calcSum() 메소드
 * @author eunji
 *
 */
public class Calculator {

	public Integer calcSum(String filePath) throws IOException{
		
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(filePath)); 
			Integer sum = 0;
			String line = null;
			while((line = br.readLine()) != null){
				sum += Integer.valueOf(line);
			}
			return sum;
		}catch(IOException e){
			System.out.println(e.getMessage());
			throw e;
		}finally {
			if(br != null){//BufferedReader 오브젝트가 생성되기 전에 예외가 발생할 수도 있으므로 반드시 null체크를 먼저해야한다.
				try{
					br.close();
				}catch (Exception e2) {
					
				}
			}
		}
		
	
	}
}
