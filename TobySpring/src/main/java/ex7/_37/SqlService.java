package ex7._37;

public interface SqlService {
	String getSql(String key) throws SqlRetrievalFailureException;
}
