package ex7._37;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import ex7._13.jaxb.SqlType;
import ex7._13.jaxb.Sqlmap;
import ex7._30.UserDao;

public class JaxbXmlSqlReader implements SqlReader{
	
	private String sqlmapFile; //SqlReader
	
	public void setSqlmapFile(String sqlmapFile) {
		this.sqlmapFile = sqlmapFile;
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
