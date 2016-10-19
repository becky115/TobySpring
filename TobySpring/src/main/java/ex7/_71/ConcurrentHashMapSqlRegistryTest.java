package ex7._71;

import ex7._65.ConcurrentHashMapSqlRegistry;
import ex7.common.UpdatableSqlRegistry;

public class ConcurrentHashMapSqlRegistryTest extends AbstractUpdatableSqlRegistryTest {
	protected UpdatableSqlRegistry createUpdatableSqlRegistry() {
		return new ConcurrentHashMapSqlRegistry();
	}
}
