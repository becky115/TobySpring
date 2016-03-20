package ex3._42;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ex3._41.LineCallback;


public class Calculator {
	
	/**
	 * 리스트 3-42 타입 파라미터를 추가해서 제네릭 메소드로 만든 lineReadTemplate()
	 * @param filePath
	 * @param callback
	 * @param initVal
	 * @return
	 * @throws IOException
	 */
	public <T> T lineReadTemplate(String filePath, LineCallback<T> callback, T initVal) throws IOException{
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(filePath)); 
			T res = initVal;
			String line = null;
			while((line = br.readLine()) != null){
				res = callback.doSomthingWithLine(line, res);
			}
			return res;
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
	
	/**
	 * lineReadTemplate()를 사용하도록 수정한 calcSum(), calcMultiply()메소드
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public Integer calcSum(String filePath) throws IOException{
		LineCallback<Integer> sumCallback = new LineCallback<Integer>() {
			public Integer doSomthingWithLine(String line, Integer value) throws IOException {
				return value += Integer.valueOf(line);
			}
		};
		
		return lineReadTemplate(filePath, sumCallback, 0);
	}
	
	public Integer calcMultiply(String filePath) throws IOException{
		LineCallback<Integer> multiCallback = new LineCallback<Integer>(){
			public Integer doSomthingWithLine(String line, Integer value) throws IOException {
				return value *= Integer.valueOf(line);
			}
		};
		
		return lineReadTemplate(filePath, multiCallback, 1);
	}
}
