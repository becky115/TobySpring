package ex3._35;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ex3._33.BufferedReaderCallback;


public class Calculator {
	
	public Integer fildReaderTemlate(String filePath, BufferedReaderCallback callback) throws IOException{
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(filePath)); 
			return callback.doSomthingWithReader(br);
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
	 * 리스트 3-35 템플릿/콜백을 적용한 calcSum() 메소드
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public Integer calcSum(String filePath) throws IOException{
		BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
			public Integer doSomthingWithReader(BufferedReader br) throws IOException {
				Integer sum = 0;
				String line = null;
				while((line = br.readLine()) != null){
					sum += Integer.valueOf(line);
				}
				return sum;
			}
		};
		
		return fildReaderTemlate(filePath, sumCallback);
	}

	
	/**
	 * 리스트 3-37 곱을 계산하는 콜백을 가진 calcMultiply메소드
	 * @param filePath
	 * @return
	 * @throws IOException 
	 */
	public Integer calcMultiply(String filePath) throws IOException {
		BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
			public Integer doSomthingWithReader(BufferedReader br) throws IOException {
				Integer multiply = 1;
				String line = null;
				while((line = br.readLine()) != null){
					multiply *= Integer.valueOf(line);
				}
				return multiply;
			}
		};
		
		return fildReaderTemlate(filePath, sumCallback);
	}
}
