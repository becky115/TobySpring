package ex7._63;

import ex7.common.UpdatableSqlRegistry;

public class SqlAdminService implements AdminEventListener{
	private UpdatableSqlRegistry updatableSqlRegistry;

	public void setUpdatableSqlRegistry(UpdatableSqlRegistry updatableSqlRegistry) {
		this.updatableSqlRegistry = updatableSqlRegistry;
	}

	@Override
	public void updateEventListener(UpdateEvent event) {
		//this.updatableSqlRegistry.updateSql(event.get(KEY_ID), event.get(SQL_ID));
	}
	
	

}
