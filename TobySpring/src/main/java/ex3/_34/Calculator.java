package ex3._34;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ex3._33.BufferedReaderCallback;

/**
 * 리스트 3-34  BufferedREaderCallback을 사용하는 템플릿메소드
 * @author eunji
 *
 */
public class Calculator {
	
	public Integer fildReaderTemlate(String filePath, BufferedReaderCallback callback) throws IOException{
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(filePath)); 
			return callback.doSomthingWithReader(br); //콜백 오브젝트 호출. 템플릿에서 만든 컨텍스트 정보인 BufferedReader를 전달해주고 콜백의 작업 결과를 받아둠
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
