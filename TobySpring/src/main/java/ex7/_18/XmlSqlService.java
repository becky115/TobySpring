package ex7._18;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import ex7._13.jaxb.SqlType;
import ex7._13.jaxb.Sqlmap;
import ex7._21.SqlRetrievalFailureException;

public class XmlSqlService implements SqlService{
	
	private Map<String, String> sqlMap = new HashMap<String, String>();

	public XmlSqlService() {
		String contextPath = Sqlmap.class.getPackage().getName();
		
		try {
			JAXBContext context = JAXBContext.newInstance(contextPath);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			InputStream is = UserDao.class.getResourceAsStream("sqlmap.xml");
			Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(is);
			
			for(SqlType sql: sqlmap.getSql()) {
				sqlMap.put(sql.getKey(),  sql.getValue());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public String getSql(String key) throws SqlRetrievalFailureException {
		String sql = sqlMap.get(key);
		if(sql == null) throw new SqlRetrievalFailureException(key +"에 대한 SQL을 찾을 수 없습니다.");
		else return sql;
	}

}
