package ex7._50;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Unmarshaller;

import ex7._46.jaxb.SqlType;
import ex7._46.jaxb.Sqlmap;
import ex7.common.HashMapSqlRegistry;
import ex7.common.SqlReader;
import ex7.common.SqlRegistry;
import ex7.common.SqlRetrievalFailureException;
import ex7.common.SqlService;
import ex7.common.UserDao;


public class OxmSqlServiceOld implements SqlService {
	private final OxmSqlReader oxmSqlReader = new OxmSqlReader();
	private SqlRegistry sqlRegistry = new HashMapSqlRegistry();
	
	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		this.sqlRegistry = sqlRegistry;
	}
	
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.oxmSqlReader.setUnmarshaller(unmarshaller);
	}
	
	public void setSqlmapFile(String sqlmapFile) {
		this.oxmSqlReader.setSqlmapFile(sqlmapFile);
	}

	@PostConstruct
	public void loadSql() {
		this.oxmSqlReader.read(this.sqlRegistry);
	}

	public String getSql(String key) throws SqlRetrievalFailureException {
		try {
			return this.sqlRegistry.findSql(key);
		} catch (Exception e) {
			throw new SqlRetrievalFailureException(e);
		}
	}
	
	private class OxmSqlReader implements SqlReader {
		private Unmarshaller unmarshaller;
		private final static String DEFAULT_SQLMAP_FILE = "sqlmap.xml";
		private String sqlmapFile = DEFAULT_SQLMAP_FILE;

		public void setUnmarshaller(Unmarshaller unmarshaller) {
			this.unmarshaller = unmarshaller;
		}

		public void setSqlmapFile(String sqlmapFile) {
			this.sqlmapFile = sqlmapFile;
		}

		public void read(SqlRegistry sqlRegistry) {
			try {
				Source source = new StreamSource(UserDao.class.getResourceAsStream(this.sqlmapFile));
				Sqlmap sqlmap = (Sqlmap)this.unmarshaller.unmarshal(source);
				for(SqlType sql : sqlmap.getSql()) {
					sqlRegistry.registerSql(sql.getKey(), sql.getValue());
				}
			} catch (IOException e) {
				throw new IllegalArgumentException(this.sqlmapFile + "을 가져올 수 없습니다", e);
			}
		}
	}
}
