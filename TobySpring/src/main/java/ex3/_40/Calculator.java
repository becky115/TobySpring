package ex3._40;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ex3._38.LineCallback;

public class Calculator {
	public Integer lineReadTemplate(String filePath, LineCallback callback, int initVal) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath)); 
			Integer res = initVal;
			String line = null;
			while((line = br.readLine()) != null) {
				res = callback.doSomthingWithLine(line, initVal);
			}
			return res;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw e;
		}finally {
			if(br != null) {//BufferedReader 오브젝트가 생성되기 전에 예외가 발생할 수도 있으므로 반드시 null체크를 먼저해야한다.
				try {
					br.close();
				}catch (Exception e2) {
					
				}
			}
		}
	}
	
	/**
	 * 리스트 3-40 lineReadTemplate()를 사용하도록 수정한 calcSum(), calcMultiply()메소드
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public Integer calcSum(String filePath) throws IOException {
		LineCallback sumCallback = new LineCallback() {
			public Integer doSomthingWithLine(String line, Integer value) throws IOException {
				return value += Integer.valueOf(line);
			}
		};
		
		return lineReadTemplate(filePath, sumCallback, 0);
	}
	
	public Integer calcMultiply(String filePath) throws IOException {
		LineCallback multiCallback = new LineCallback() {
			public Integer doSomthingWithLine(String line, Integer value) throws IOException {
				return value *= Integer.valueOf(line);
			}
		};
		
		return lineReadTemplate(filePath, multiCallback, 1);
	}
}
