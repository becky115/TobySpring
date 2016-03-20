package ex3._34;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ex3._33.BufferedReaderCallback;

/**
 * BufferedREaderCallback을 사용하는 템플릿메소드
 * @author eunji
 *
 */
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
}
