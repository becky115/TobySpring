package ex7._41;

public class JaxbXmlSqlReader implements SqlReader{
	
	private static final String DEFAULT_SQLMAP_FILE= "sqlmap.xml";
	
	
	private String sqlmapFile = DEFAULT_SQLMAP_FILE; //SqlReader
	
	public void setSqlmapFile(String sqlmapFile) {
		this.sqlmapFile = sqlmapFile;
	}
	
	@Override
	public void read(SqlRegistry sqlRegistry) {
		// TODO Auto-generated method stub
		
	}

}
