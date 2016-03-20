package ex3._33;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 리스트 3-33  BufferedReader를 전달받는 콜백 인터페이스
 * @author eunji
 *
 */
public interface BufferedReaderCallback {
	Integer doSomthingWithReader(BufferedReader br) throws IOException;
}
