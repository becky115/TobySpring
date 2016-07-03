package ex7._41;

public interface SqlService {
	String getSql(String key) throws SqlRetrievalFailureException;
}
