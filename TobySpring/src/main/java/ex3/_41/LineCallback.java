package ex3._41;

import java.io.IOException;

/**
 * 리스트 3-41 타입 파라미터를 적용한 LineCallback
 * @author eunji
 *
 * @param <T>
 */
public interface LineCallback<T> {
	T doSomthingWithLine(String line, T value) throws IOException;
}
