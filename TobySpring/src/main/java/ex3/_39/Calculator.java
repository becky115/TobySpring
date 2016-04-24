package ex3._39;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import ex3._38.LineCallback;

/**
 * 리스트 3-39 LineCallback을 사용하는 템플릿
 * @author eunji
 *
 */
public class Calculator {
	/**
	 * 
	 * @param filePath
	 * @param callback
	 * @param initVal: 계산 결과를 저장할 변수의 초깃값 
	 * @return
	 * @throws IOException
	 */
	public Integer lineReadTemplate(String filePath, LineCallback callback, int initVal) throws IOException{
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(filePath)); 
			Integer res = initVal;
			String line = null;
			while((line = br.readLine()) != null){//파일의 각 라인을 루프를 돌면서 가져오는것도 템플릿이 담당한다.
				res = callback.doSomthingWithLine(line, res);
				//line: 각 라인의 내용을 가지고 계산하는 작업만 콜백에게 맡긴다.
				//res: 콜백이 계산한 값을 저장해뒀다가 다음 라인 계산에 다시 사용한다.
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
}
