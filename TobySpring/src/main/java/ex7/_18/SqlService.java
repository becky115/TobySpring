package ex7._18;

public interface SqlService {
	String getSql(String key) throws SqlRetrievalFailureException;
}
