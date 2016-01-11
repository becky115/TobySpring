package ex1.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ex1.db.ConnectionMaker8;
import ex1.db.DConnctionMaker9;

/**
 * 리스트 1-35 connectionMaker()메소드의 <bean>태그 전환
 * @author ejlee
 *
 */
@Configuration
public class DaoFactory34 {

	
	@Bean //-----------------------------------------> <bean
	public ConnectionMaker8 connectionMaker(){ //----> id="connectionMaker"
		return new DConnctionMaker9(); //------------> class"ex1.db.DConnectionMaker"/>
	}
}
