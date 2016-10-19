package ex7._63;

import java.util.Map;

import ex7.common.SqlNotFoundException;
import ex7.common.SqlUpdateFailureException;
import ex7.common.UpdatableSqlRegistry;

public class MyUpdatableSqlRegistry implements UpdatableSqlRegistry{

	@Override
	public void registerSql(String key, String sql) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findSql(String key) throws SqlNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSql(String key, String sql) throws SqlUpdateFailureException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSql(Map<String, String> sqlmap) throws SqlUpdateFailureException {
		// TODO Auto-generated method stub
		
	}



}
