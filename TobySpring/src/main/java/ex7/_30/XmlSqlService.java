package ex7._30;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import ex7._13.jaxb.SqlType;
import ex7._13.jaxb.Sqlmap;

public class XmlSqlService implements SqlService, SqlRegistry, SqlReader{
	
	private SqlReader sqlReader;
	private SqlRegistry sqlRegistry;
	
	
//	
	private String sqlmapFile; //SqlReader
	
	public void setSqlmapFile(String sqlmapFile) {
		this.sqlmapFile = sqlmapFile;
	}
//
	private Map<String, String> sqlMap = new HashMap<String, String>(); //SqlRegistry

	public void setSqlReader(SqlReader sqlReader) {
		this.sqlReader = sqlReader;
	}

	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		this.sqlRegistry = sqlRegistry;
	}
	
	@PostConstruct
	public void loadSql() {
		this.sqlReader.read(this.sqlRegistry);
		
	}

	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		try {
			return this.sqlRegistry.findSql(key);
		} catch (SqlNotFoundException e) {
			throw new SqlRetrievalFailureException(e.getMessage());
		}
	}

	@Override
	public void registerSql(String key, String sql) {
		sqlMap.put(key, sql);
		
	}

	@Override
	public String findSql(String key) throws SqlNotFoundException {
		String sql = sqlMap.get(key);
		if(sql == null) throw new SqlRetrievalFailureException(key +"에 대한 SQL을 찾을 수 없습니다.");
		else return sql;
	}

	@Override
	public void read(SqlRegistry sqlRegistry) {
		String contextPath = Sqlmap.class.getPackage().getName();
		
		try {
			JAXBContext context = JAXBContext.newInstance(contextPath);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			InputStream is = UserDao.class.getResourceAsStream(this.sqlmapFile);
			Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(is);
			
			for(SqlType sql: sqlmap.getSql()) {
				sqlRegistry.registerSql(sql.getKey(),  sql.getValue());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
