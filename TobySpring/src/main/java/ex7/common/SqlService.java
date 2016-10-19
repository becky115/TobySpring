package ex7.common;

public interface SqlService {
	String getSql(String key) throws SqlRetrievalFailureException;
}
