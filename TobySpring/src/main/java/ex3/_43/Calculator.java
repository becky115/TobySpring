package ex3._43;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ex3._41.LineCallback;

public class Calculator {
	public <T> T lineReadTemplate(String filePath, LineCallback<T> callback, T initVal) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath)); 
			T res = initVal;
			String line = null;
			while((line = br.readLine()) != null) {
				res = callback.doSomthingWithLine(line, res);
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
	 * 리스트 3-43 문자열 연결 기능 콜백을 이용해 만든 concatenate()메소드
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public String concatenate(String filePath) throws IOException {
		LineCallback<String> concatenateCallback = new LineCallback<String>() {
			public String doSomthingWithLine(String line, String value) throws IOException {
				return value + line;
			}
		};
		
		return lineReadTemplate(filePath, concatenateCallback, "");
	}
	
	
	
}
