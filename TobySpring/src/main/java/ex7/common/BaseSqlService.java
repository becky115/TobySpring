package ex7.common;

import javax.annotation.PostConstruct;

public class BaseSqlService implements SqlService{
	
	protected SqlReader sqlReader;
	protected SqlRegistry sqlRegistry;
	
	
	
	public void setSqlReader(SqlReader sqlReader) {
		System.out.println("set sqlReader");
		this.sqlReader = sqlReader;

	}

	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		System.out.println("set setSqlRegistry: " + sqlRegistry);
		this.sqlRegistry = sqlRegistry;
		System.out.println(this.sqlRegistry);
	}
	
	@PostConstruct
	public void loadSql(){
		System.out.println("loadSql");
		if(this.sqlReader != null)
		this.sqlReader.read(this.sqlRegistry);
	}

	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		try{
			return this.sqlRegistry.findSql(key);
		}catch(SqlNotFoundException e){
			throw new SqlRetrievalFailureException(e.getMessage());
		}
	}

}
