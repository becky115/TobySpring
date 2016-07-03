package ex7._30;

public interface SqlService {
	String getSql(String key) throws SqlRetrievalFailureException;
}
