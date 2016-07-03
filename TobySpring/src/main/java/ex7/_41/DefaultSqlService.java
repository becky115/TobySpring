package ex7._41;


public class DefaultSqlService extends BaseSqlService{

	public DefaultSqlService() {
		setSqlReader(new JaxbXmlSqlReader());
		setSqlRegistry(new HashMapSqlRegistry());
	
	}
	
}
