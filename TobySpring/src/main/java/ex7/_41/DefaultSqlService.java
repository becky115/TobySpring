package ex7._41;

import javax.annotation.PostConstruct;

public class DefaultSqlService extends BaseSqlService{
//
	public DefaultSqlService() {
		System.out.println("default sql service");
		
			
		
	}
	
	@PostConstruct
	public void test() {
		System.out.println("default sql service postconstruct");
		
		setDefault();
		
		loadSql();
		
	}
	
	public void setDefault() {
//		System.out.println(this.sqlReader);
		System.out.println(this.sqlReader);
		if(this.sqlRegistry == null) {
			//System.out.println("set hashmap");
			setSqlRegistry(new HashMapSqlRegistry());
		}
		if(this.sqlReader == null) {
			//System.out.println("set jaxb");
			setSqlReader(new JaxbXmlSqlReader());
		}
	}
	
	
	
}
