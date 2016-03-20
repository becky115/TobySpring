package ex3._38;

import java.io.IOException;

/**
 * 라인별 작업을 정의한 콜백 인터페이스
 * @author eunji
 *
 */
public interface LineCallback {
	Integer doSomthingWithLine(String line, Integer value) throws IOException;
}
