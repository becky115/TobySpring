package ex3._9;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ex3._8.StatementStrategy;

/**
 * 리스트 3-9 deleteAll() 메소드의 기능을 구현한 StatementStrategy 전략 클래스
 * @author eunji
 *
 */
public class DeleteAllStatement implements StatementStrategy {

	public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
		PreparedStatement ps = c.prepareStatement("delete from users");
		return ps;
	}

}
